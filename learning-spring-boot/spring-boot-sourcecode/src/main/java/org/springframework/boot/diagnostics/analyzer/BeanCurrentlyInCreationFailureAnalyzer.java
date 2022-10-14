/*
 * Copyright 2012-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.diagnostics.analyzer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.BeanCurrentlyInCreationException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;
import org.springframework.util.StringUtils;

/**
 * An {@link AbstractFailureAnalyzer} that performs analysis of failures caused by a
 * {@link BeanCurrentlyInCreationException}.
 *
 * @author Andy Wilkinson
 * @author Scott Frederick
 */
class BeanCurrentlyInCreationFailureAnalyzer extends AbstractFailureAnalyzer<BeanCurrentlyInCreationException> {

	private final AbstractAutowireCapableBeanFactory beanFactory;

	BeanCurrentlyInCreationFailureAnalyzer(BeanFactory beanFactory) {
		if (beanFactory != null && beanFactory instanceof AbstractAutowireCapableBeanFactory) {
			this.beanFactory = (AbstractAutowireCapableBeanFactory) beanFactory;
		}
		else {
			this.beanFactory = null;
		}
	}

	@Override
	protected FailureAnalysis analyze(Throwable rootFailure, BeanCurrentlyInCreationException cause) {
		DependencyCycle dependencyCycle = findCycle(rootFailure);
		if (dependencyCycle == null) {
			return null;
		}
		return new FailureAnalysis(buildMessage(dependencyCycle), action(), cause);
	}

	private String action() {
		if (this.beanFactory != null && this.beanFactory.isAllowCircularReferences()) {
			return "Despite circular references being allowed, the dependency cycle between beans could not be "
					+ "broken. Update your application to remove the dependency cycle.";
		}
		return "Relying upon circular references is discouraged and they are prohibited by default. "
				+ "Update your application to remove the dependency cycle between beans. "
				+ "As a last resort, it may be possible to break the cycle automatically by setting "
				+ "spring.main.allow-circular-references to true.";
	}

	private DependencyCycle findCycle(Throwable rootFailure) {
		List<BeanInCycle> beansInCycle = new ArrayList<>();
		Throwable candidate = rootFailure;
		int cycleStart = -1;
		while (candidate != null) {
			BeanInCycle beanInCycle = BeanInCycle.get(candidate);
			if (beanInCycle != null) {
				int index = beansInCycle.indexOf(beanInCycle);
				if (index == -1) {
					beansInCycle.add(beanInCycle);
				}
				cycleStart = (cycleStart != -1) ? cycleStart : index;
			}
			candidate = candidate.getCause();
		}
		if (cycleStart == -1) {
			return null;
		}
		return new DependencyCycle(beansInCycle, cycleStart);
	}

	private String buildMessage(DependencyCycle dependencyCycle) {
		StringBuilder message = new StringBuilder();
		message.append(
				String.format("The dependencies of some of the beans in the application context form a cycle:%n%n"));
		List<BeanInCycle> beansInCycle = dependencyCycle.getBeansInCycle();
		boolean singleBean = beansInCycle.size() == 1;
		int cycleStart = dependencyCycle.getCycleStart();
		for (int i = 0; i < beansInCycle.size(); i++) {
			BeanInCycle beanInCycle = beansInCycle.get(i);
			if (i == cycleStart) {
				message.append(String.format(singleBean ? "┌──->──┐%n" : "┌─────┐%n"));
			}
			else if (i > 0) {
				String leftSide = (i < cycleStart) ? " " : "↑";
				message.append(String.format("%s     ↓%n", leftSide));
			}
			String leftSide = (i < cycleStart) ? " " : "|";
			message.append(String.format("%s  %s%n", leftSide, beanInCycle));
		}
		message.append(String.format(singleBean ? "└──<-──┘%n" : "└─────┘%n"));
		return message.toString();
	}

	private static final class DependencyCycle {

		private final List<BeanInCycle> beansInCycle;

		private final int cycleStart;

		private DependencyCycle(List<BeanInCycle> beansInCycle, int cycleStart) {
			this.beansInCycle = beansInCycle;
			this.cycleStart = cycleStart;
		}

		List<BeanInCycle> getBeansInCycle() {
			return this.beansInCycle;
		}

		int getCycleStart() {
			return this.cycleStart;
		}

	}

	private static final class BeanInCycle {

		private final String name;

		private final String description;

		private BeanInCycle(BeanCreationException ex) {
			this.name = ex.getBeanName();
			this.description = determineDescription(ex);
		}

		private String determineDescription(BeanCreationException ex) {
			if (StringUtils.hasText(ex.getResourceDescription())) {
				return String.format(" defined in %s", ex.getResourceDescription());
			}
			InjectionPoint failedInjectionPoint = findFailedInjectionPoint(ex);
			if (failedInjectionPoint != null && failedInjectionPoint.getField() != null) {
				return String.format(" (field %s)", failedInjectionPoint.getField());
			}
			return "";
		}

		private InjectionPoint findFailedInjectionPoint(BeanCreationException ex) {
			if (!(ex instanceof UnsatisfiedDependencyException)) {
				return null;
			}
			return ((UnsatisfiedDependencyException) ex).getInjectionPoint();
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null || getClass() != obj.getClass()) {
				return false;
			}
			return this.name.equals(((BeanInCycle) obj).name);
		}

		@Override
		public int hashCode() {
			return this.name.hashCode();
		}

		@Override
		public String toString() {
			return this.name + this.description;
		}

		static BeanInCycle get(Throwable ex) {
			if (ex instanceof BeanCreationException) {
				return get((BeanCreationException) ex);
			}
			return null;
		}

		private static BeanInCycle get(BeanCreationException ex) {
			if (StringUtils.hasText(ex.getBeanName())) {
				return new BeanInCycle(ex);
			}
			return null;
		}

	}

}

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

package org.springframework.boot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.core.annotation.Order;
import org.springframework.util.Assert;

/**
 * Maintains an ordered collection of {@link ExitCodeGenerator} instances and allows the
 * final exit code to be calculated. Generators are ordered by {@link Order @Order} and
 * {@link Ordered}.
 *
 * @author Dave Syer
 * @author Phillip Webb
 * @author GenKui Du
 * @see #getExitCode()
 * @see ExitCodeGenerator
 */
class ExitCodeGenerators implements Iterable<ExitCodeGenerator> {

	private List<ExitCodeGenerator> generators = new ArrayList<>();

	void addAll(Throwable exception, ExitCodeExceptionMapper... mappers) {
		Assert.notNull(exception, "Exception must not be null");
		Assert.notNull(mappers, "Mappers must not be null");
		addAll(exception, Arrays.asList(mappers));
	}

	void addAll(Throwable exception, Iterable<? extends ExitCodeExceptionMapper> mappers) {
		Assert.notNull(exception, "Exception must not be null");
		Assert.notNull(mappers, "Mappers must not be null");
		for (ExitCodeExceptionMapper mapper : mappers) {
			add(exception, mapper);
		}
	}

	void add(Throwable exception, ExitCodeExceptionMapper mapper) {
		Assert.notNull(exception, "Exception must not be null");
		Assert.notNull(mapper, "Mapper must not be null");
		add(new MappedExitCodeGenerator(exception, mapper));
	}

	void addAll(ExitCodeGenerator... generators) {
		Assert.notNull(generators, "Generators must not be null");
		addAll(Arrays.asList(generators));
	}

	void addAll(Iterable<? extends ExitCodeGenerator> generators) {
		Assert.notNull(generators, "Generators must not be null");
		for (ExitCodeGenerator generator : generators) {
			add(generator);
		}
	}

	void add(ExitCodeGenerator generator) {
		Assert.notNull(generator, "Generator must not be null");
		this.generators.add(generator);
		AnnotationAwareOrderComparator.sort(this.generators);
	}

	@Override
	public Iterator<ExitCodeGenerator> iterator() {
		return this.generators.iterator();
	}

	/**
	 * Get the final exit code that should be returned. The final exit code is the first
	 * non-zero exit code that is {@link ExitCodeGenerator#getExitCode generated}.
	 * @return the final exit code.
	 */
	int getExitCode() {
		int exitCode = 0;
		for (ExitCodeGenerator generator : this.generators) {
			try {
				int value = generator.getExitCode();
				if (value != 0) {
					exitCode = value;
					break;
				}
			}
			catch (Exception ex) {
				exitCode = 1;
				ex.printStackTrace();
			}
		}
		return exitCode;
	}

	/**
	 * Adapts an {@link ExitCodeExceptionMapper} to an {@link ExitCodeGenerator}.
	 */
	private static class MappedExitCodeGenerator implements ExitCodeGenerator {

		private final Throwable exception;

		private final ExitCodeExceptionMapper mapper;

		MappedExitCodeGenerator(Throwable exception, ExitCodeExceptionMapper mapper) {
			this.exception = exception;
			this.mapper = mapper;
		}

		@Override
		public int getExitCode() {
			return this.mapper.getExitCode(this.exception);
		}

	}

}

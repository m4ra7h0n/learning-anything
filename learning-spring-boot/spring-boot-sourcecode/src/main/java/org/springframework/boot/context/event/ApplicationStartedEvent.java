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

package org.springframework.boot.context.event;

import java.time.Duration;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Event published once the application context has been refreshed but before any
 * {@link ApplicationRunner application} and {@link CommandLineRunner command line}
 * runners have been called.
 *
 * @author Andy Wilkinson
 * @since 2.0.0
 */
@SuppressWarnings("serial")
public class ApplicationStartedEvent extends SpringApplicationEvent {

	private final ConfigurableApplicationContext context;

	private final Duration timeTaken;

	/**
	 * Create a new {@link ApplicationStartedEvent} instance.
	 * @param application the current application
	 * @param args the arguments the application is running with
	 * @param context the context that was being created
	 * @deprecated since 2.6.0 for removal in 3.0.0 in favor of
	 * {@link #ApplicationStartedEvent(SpringApplication, String[], ConfigurableApplicationContext, Duration)}
	 */
	@Deprecated
	public ApplicationStartedEvent(SpringApplication application, String[] args,
			ConfigurableApplicationContext context) {
		this(application, args, context, null);
	}

	/**
	 * Create a new {@link ApplicationStartedEvent} instance.
	 * @param application the current application
	 * @param args the arguments the application is running with
	 * @param context the context that was being created
	 * @param timeTaken the time taken to start the application
	 * @since 2.6.0
	 */
	public ApplicationStartedEvent(SpringApplication application, String[] args, ConfigurableApplicationContext context,
			Duration timeTaken) {
		super(application, args);
		this.context = context;
		this.timeTaken = timeTaken;
	}

	/**
	 * Return the application context.
	 * @return the context
	 */
	public ConfigurableApplicationContext getApplicationContext() {
		return this.context;
	}

	/**
	 * Return the time taken to start the application, or {@code null} if unknown.
	 * @return the startup time
	 * @since 2.6.0
	 */
	public Duration getTimeTaken() {
		return this.timeTaken;
	}

}

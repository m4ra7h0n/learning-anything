/*
 * Copyright 2012-2019 the original author or authors.
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

/**
 * Strategy interface that can be used to provide a mapping between exceptions and exit
 * codes.
 *
 * @author Phillip Webb
 * @since 1.3.2
 */
@FunctionalInterface
public interface ExitCodeExceptionMapper {

	/**
	 * Returns the exit code that should be returned from the application.
	 * @param exception the exception causing the application to exit
	 * @return the exit code or {@code 0}.
	 */
	int getExitCode(Throwable exception);

}

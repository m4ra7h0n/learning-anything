/*
 * Copyright 2012-2020 the original author or authors.
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

package org.springframework.boot.logging;

import java.io.File;
import java.util.Properties;

import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertyResolver;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * A reference to a log output file. Log output files are specified using
 * {@code logging.file.name} or {@code logging.file.path} {@link Environment} properties.
 * If the {@code logging.file.name} property is not specified {@code "spring.log"} will be
 * written in the {@code logging.file.path} directory.
 *
 * @author Phillip Webb
 * @author Christian Carriere-Tisseur
 * @since 1.2.1
 * @see #get(PropertyResolver)
 */
public class LogFile {

	/**
	 * The name of the Spring property that contains the name of the log file. Names can
	 * be an exact location or relative to the current directory.
	 * @since 2.2.0
	 */
	public static final String FILE_NAME_PROPERTY = "logging.file.name";

	/**
	 * The name of the Spring property that contains the directory where log files are
	 * written.
	 * @since 2.2.0
	 */
	public static final String FILE_PATH_PROPERTY = "logging.file.path";

	private final String file;

	private final String path;

	/**
	 * Create a new {@link LogFile} instance.
	 * @param file a reference to the file to write
	 */
	LogFile(String file) {
		this(file, null);
	}

	/**
	 * Create a new {@link LogFile} instance.
	 * @param file a reference to the file to write
	 * @param path a reference to the logging path to use if {@code file} is not specified
	 */
	LogFile(String file, String path) {
		Assert.isTrue(StringUtils.hasLength(file) || StringUtils.hasLength(path), "File or Path must not be empty");
		this.file = file;
		this.path = path;
	}

	/**
	 * Apply log file details to {@code LOG_PATH} and {@code LOG_FILE} system properties.
	 */
	public void applyToSystemProperties() {
		applyTo(System.getProperties());
	}

	/**
	 * Apply log file details to {@code LOG_PATH} and {@code LOG_FILE} map entries.
	 * @param properties the properties to apply to
	 */
	public void applyTo(Properties properties) {
		put(properties, LoggingSystemProperties.LOG_PATH, this.path);
		put(properties, LoggingSystemProperties.LOG_FILE, toString());
	}

	private void put(Properties properties, String key, String value) {
		if (StringUtils.hasLength(value)) {
			properties.put(key, value);
		}
	}

	@Override
	public String toString() {
		if (StringUtils.hasLength(this.file)) {
			return this.file;
		}
		return new File(this.path, "spring.log").getPath();
	}

	/**
	 * Get a {@link LogFile} from the given Spring {@link Environment}.
	 * @param propertyResolver the {@link PropertyResolver} used to obtain the logging
	 * properties
	 * @return a {@link LogFile} or {@code null} if the environment didn't contain any
	 * suitable properties
	 */
	public static LogFile get(PropertyResolver propertyResolver) {
		String file = propertyResolver.getProperty(FILE_NAME_PROPERTY);
		String path = propertyResolver.getProperty(FILE_PATH_PROPERTY);
		if (StringUtils.hasLength(file) || StringUtils.hasLength(path)) {
			return new LogFile(file, path);
		}
		return null;
	}

}

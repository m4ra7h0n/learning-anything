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

package org.springframework.boot.web.server;

import java.security.KeyStore;
import java.security.KeyStoreException;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * Provides utilities around SSL.
 *
 * @author Chris Bono
 * @since 2.1.13
 */
public final class SslConfigurationValidator {

	private SslConfigurationValidator() {
	}

	public static void validateKeyAlias(KeyStore keyStore, String keyAlias) {
		if (StringUtils.hasLength(keyAlias)) {
			try {
				Assert.state(keyStore.containsAlias(keyAlias),
						() -> String.format("Keystore does not contain specified alias '%s'", keyAlias));
			}
			catch (KeyStoreException ex) {
				throw new IllegalStateException(
						String.format("Could not determine if keystore contains alias '%s'", keyAlias), ex);
			}
		}
	}

}

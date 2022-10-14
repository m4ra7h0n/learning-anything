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

package org.springframework.boot.web.server;

import java.security.KeyStore;

/**
 * Interface to provide SSL key stores for an {@link WebServer} to use. Can be used when
 * file based key stores cannot be used.
 *
 * @author Phillip Webb
 * @since 2.0.0
 */
public interface SslStoreProvider {

	/**
	 * Return the key store that should be used.
	 * @return the key store to use
	 * @throws Exception on load error
	 */
	KeyStore getKeyStore() throws Exception;

	/**
	 * Return the trust store that should be used.
	 * @return the trust store to use
	 * @throws Exception on load error
	 */
	KeyStore getTrustStore() throws Exception;

	/**
	 * Return the password of the private key in the key store.
	 * @return the key password
	 * @since 2.7.2
	 */
	default String getKeyPassword() {
		return null;
	}

}

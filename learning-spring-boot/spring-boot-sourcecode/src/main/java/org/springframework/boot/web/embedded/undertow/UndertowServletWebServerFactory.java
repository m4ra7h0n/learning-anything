/*
 * Copyright 2012-2021 the original author or authors.
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

package org.springframework.boot.web.embedded.undertow;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EventListener;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import io.undertow.Undertow.Builder;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.Cookie;
import io.undertow.server.handlers.resource.FileResourceManager;
import io.undertow.server.handlers.resource.Resource;
import io.undertow.server.handlers.resource.ResourceChangeListener;
import io.undertow.server.handlers.resource.ResourceManager;
import io.undertow.server.handlers.resource.URLResource;
import io.undertow.server.session.SessionManager;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.Deployment;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import io.undertow.servlet.api.ListenerInfo;
import io.undertow.servlet.api.MimeMapping;
import io.undertow.servlet.api.ServletContainerInitializerInfo;
import io.undertow.servlet.api.ServletStackTraces;
import io.undertow.servlet.core.DeploymentImpl;
import io.undertow.servlet.handlers.DefaultServlet;
import io.undertow.servlet.util.ImmediateInstanceFactory;

import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.boot.web.server.Cookie.SameSite;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.MimeMappings.Mapping;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.AbstractServletWebServerFactory;
import org.springframework.boot.web.servlet.server.CookieSameSiteSupplier;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

/**
 * {@link ServletWebServerFactory} that can be used to create
 * {@link UndertowServletWebServer}s.
 * <p>
 * Unless explicitly configured otherwise, the factory will create servers that listen for
 * HTTP requests on port 8080.
 *
 * @author Ivan Sopov
 * @author Andy Wilkinson
 * @author Marcos Barbero
 * @author Eddú Meléndez
 * @since 2.0.0
 * @see UndertowServletWebServer
 */
public class UndertowServletWebServerFactory extends AbstractServletWebServerFactory
		implements ConfigurableUndertowWebServerFactory, ResourceLoaderAware {

	private static final Pattern ENCODED_SLASH = Pattern.compile("%2F", Pattern.LITERAL);

	private static final Set<Class<?>> NO_CLASSES = Collections.emptySet();

	private UndertowWebServerFactoryDelegate delegate = new UndertowWebServerFactoryDelegate();

	private Set<UndertowDeploymentInfoCustomizer> deploymentInfoCustomizers = new LinkedHashSet<>();

	private ResourceLoader resourceLoader;

	private boolean eagerFilterInit = true;

	private boolean preservePathOnForward = false;

	/**
	 * Create a new {@link UndertowServletWebServerFactory} instance.
	 */
	public UndertowServletWebServerFactory() {
		getJsp().setRegistered(false);
	}

	/**
	 * Create a new {@link UndertowServletWebServerFactory} that listens for requests
	 * using the specified port.
	 * @param port the port to listen on
	 */
	public UndertowServletWebServerFactory(int port) {
		super(port);
		getJsp().setRegistered(false);
	}

	/**
	 * Create a new {@link UndertowServletWebServerFactory} with the specified context
	 * path and port.
	 * @param contextPath the root context path
	 * @param port the port to listen on
	 */
	public UndertowServletWebServerFactory(String contextPath, int port) {
		super(contextPath, port);
		getJsp().setRegistered(false);
	}

	@Override
	public void setBuilderCustomizers(Collection<? extends UndertowBuilderCustomizer> customizers) {
		this.delegate.setBuilderCustomizers(customizers);
	}

	@Override
	public void addBuilderCustomizers(UndertowBuilderCustomizer... customizers) {
		this.delegate.addBuilderCustomizers(customizers);
	}

	/**
	 * Returns a mutable collection of the {@link UndertowBuilderCustomizer}s that will be
	 * applied to the Undertow {@link Builder}.
	 * @return the customizers that will be applied
	 */
	public Collection<UndertowBuilderCustomizer> getBuilderCustomizers() {
		return this.delegate.getBuilderCustomizers();
	}

	@Override
	public void setBufferSize(Integer bufferSize) {
		this.delegate.setBufferSize(bufferSize);
	}

	@Override
	public void setIoThreads(Integer ioThreads) {
		this.delegate.setIoThreads(ioThreads);
	}

	@Override
	public void setWorkerThreads(Integer workerThreads) {
		this.delegate.setWorkerThreads(workerThreads);
	}

	@Override
	public void setUseDirectBuffers(Boolean directBuffers) {
		this.delegate.setUseDirectBuffers(directBuffers);
	}

	@Override
	public void setAccessLogDirectory(File accessLogDirectory) {
		this.delegate.setAccessLogDirectory(accessLogDirectory);
	}

	@Override
	public void setAccessLogPattern(String accessLogPattern) {
		this.delegate.setAccessLogPattern(accessLogPattern);
	}

	@Override
	public void setAccessLogPrefix(String accessLogPrefix) {
		this.delegate.setAccessLogPrefix(accessLogPrefix);
	}

	public String getAccessLogPrefix() {
		return this.delegate.getAccessLogPrefix();
	}

	@Override
	public void setAccessLogSuffix(String accessLogSuffix) {
		this.delegate.setAccessLogSuffix(accessLogSuffix);
	}

	@Override
	public void setAccessLogEnabled(boolean accessLogEnabled) {
		this.delegate.setAccessLogEnabled(accessLogEnabled);
	}

	public boolean isAccessLogEnabled() {
		return this.delegate.isAccessLogEnabled();
	}

	@Override
	public void setAccessLogRotate(boolean accessLogRotate) {
		this.delegate.setAccessLogRotate(accessLogRotate);
	}

	@Override
	public void setUseForwardHeaders(boolean useForwardHeaders) {
		this.delegate.setUseForwardHeaders(useForwardHeaders);
	}

	protected final boolean isUseForwardHeaders() {
		return this.delegate.isUseForwardHeaders();
	}

	/**
	 * Set {@link UndertowDeploymentInfoCustomizer}s that should be applied to the
	 * Undertow {@link DeploymentInfo}. Calling this method will replace any existing
	 * customizers.
	 * @param customizers the customizers to set
	 */
	public void setDeploymentInfoCustomizers(Collection<? extends UndertowDeploymentInfoCustomizer> customizers) {
		Assert.notNull(customizers, "Customizers must not be null");
		this.deploymentInfoCustomizers = new LinkedHashSet<>(customizers);
	}

	/**
	 * Add {@link UndertowDeploymentInfoCustomizer}s that should be used to customize the
	 * Undertow {@link DeploymentInfo}.
	 * @param customizers the customizers to add
	 */
	public void addDeploymentInfoCustomizers(UndertowDeploymentInfoCustomizer... customizers) {
		Assert.notNull(customizers, "UndertowDeploymentInfoCustomizers must not be null");
		this.deploymentInfoCustomizers.addAll(Arrays.asList(customizers));
	}

	/**
	 * Returns a mutable collection of the {@link UndertowDeploymentInfoCustomizer}s that
	 * will be applied to the Undertow {@link DeploymentInfo}.
	 * @return the customizers that will be applied
	 */
	public Collection<UndertowDeploymentInfoCustomizer> getDeploymentInfoCustomizers() {
		return this.deploymentInfoCustomizers;
	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	/**
	 * Return if filters should be eagerly initialized.
	 * @return {@code true} if filters are eagerly initialized, otherwise {@code false}.
	 * @since 2.4.0
	 */
	public boolean isEagerFilterInit() {
		return this.eagerFilterInit;
	}

	/**
	 * Set whether filters should be eagerly initialized.
	 * @param eagerFilterInit {@code true} if filters are eagerly initialized, otherwise
	 * {@code false}.
	 * @since 2.4.0
	 */
	public void setEagerFilterInit(boolean eagerFilterInit) {
		this.eagerFilterInit = eagerFilterInit;
	}

	/**
	 * Return whether the request path should be preserved on forward.
	 * @return {@code true} if the path should be preserved when a request is forwarded,
	 * otherwise {@code false}.
	 * @since 2.4.0
	 */
	public boolean isPreservePathOnForward() {
		return this.preservePathOnForward;
	}

	/**
	 * Set whether the request path should be preserved on forward.
	 * @param preservePathOnForward {@code true} if the path should be preserved when a
	 * request is forwarded, otherwise {@code false}.
	 * @since 2.4.0
	 */
	public void setPreservePathOnForward(boolean preservePathOnForward) {
		this.preservePathOnForward = preservePathOnForward;
	}

	@Override
	public WebServer getWebServer(ServletContextInitializer... initializers) {
		Builder builder = this.delegate.createBuilder(this);
		DeploymentManager manager = createManager(initializers);
		return getUndertowWebServer(builder, manager, getPort());
	}

	private DeploymentManager createManager(ServletContextInitializer... initializers) {
		DeploymentInfo deployment = Servlets.deployment();
		registerServletContainerInitializerToDriveServletContextInitializers(deployment, initializers);
		deployment.setClassLoader(getServletClassLoader());
		deployment.setContextPath(getContextPath());
		deployment.setDisplayName(getDisplayName());
		deployment.setDeploymentName("spring-boot");
		if (isRegisterDefaultServlet()) {
			deployment.addServlet(Servlets.servlet("default", DefaultServlet.class));
		}
		configureErrorPages(deployment);
		deployment.setServletStackTraces(ServletStackTraces.NONE);
		deployment.setResourceManager(getDocumentRootResourceManager());
		deployment.setTempDir(createTempDir("undertow"));
		deployment.setEagerFilterInit(this.eagerFilterInit);
		deployment.setPreservePathOnForward(this.preservePathOnForward);
		configureMimeMappings(deployment);
		configureWebListeners(deployment);
		for (UndertowDeploymentInfoCustomizer customizer : this.deploymentInfoCustomizers) {
			customizer.customize(deployment);
		}
		if (getSession().isPersistent()) {
			File dir = getValidSessionStoreDir();
			deployment.setSessionPersistenceManager(new FileSessionPersistence(dir));
		}
		addLocaleMappings(deployment);
		DeploymentManager manager = Servlets.newContainer().addDeployment(deployment);
		manager.deploy();
		if (manager.getDeployment() instanceof DeploymentImpl) {
			removeSuperfluousMimeMappings((DeploymentImpl) manager.getDeployment(), deployment);
		}
		SessionManager sessionManager = manager.getDeployment().getSessionManager();
		Duration timeoutDuration = getSession().getTimeout();
		int sessionTimeout = (isZeroOrLess(timeoutDuration) ? -1 : (int) timeoutDuration.getSeconds());
		sessionManager.setDefaultSessionTimeout(sessionTimeout);
		return manager;
	}

	private void configureWebListeners(DeploymentInfo deployment) {
		for (String className : getWebListenerClassNames()) {
			try {
				deployment.addListener(new ListenerInfo(loadWebListenerClass(className)));
			}
			catch (ClassNotFoundException ex) {
				throw new IllegalStateException("Failed to load web listener class '" + className + "'", ex);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private Class<? extends EventListener> loadWebListenerClass(String className) throws ClassNotFoundException {
		return (Class<? extends EventListener>) getServletClassLoader().loadClass(className);
	}

	private boolean isZeroOrLess(Duration timeoutDuration) {
		return timeoutDuration == null || timeoutDuration.isZero() || timeoutDuration.isNegative();
	}

	private void addLocaleMappings(DeploymentInfo deployment) {
		getLocaleCharsetMappings().forEach(
				(locale, charset) -> deployment.addLocaleCharsetMapping(locale.toString(), charset.toString()));
	}

	private void registerServletContainerInitializerToDriveServletContextInitializers(DeploymentInfo deployment,
			ServletContextInitializer... initializers) {
		ServletContextInitializer[] mergedInitializers = mergeInitializers(initializers);
		Initializer initializer = new Initializer(mergedInitializers);
		deployment.addServletContainerInitializer(new ServletContainerInitializerInfo(Initializer.class,
				new ImmediateInstanceFactory<ServletContainerInitializer>(initializer), NO_CLASSES));
	}

	private ClassLoader getServletClassLoader() {
		if (this.resourceLoader != null) {
			return this.resourceLoader.getClassLoader();
		}
		return getClass().getClassLoader();
	}

	private ResourceManager getDocumentRootResourceManager() {
		File root = getValidDocumentRoot();
		File docBase = getCanonicalDocumentRoot(root);
		List<URL> metaInfResourceUrls = getUrlsOfJarsWithMetaInfResources();
		List<URL> resourceJarUrls = new ArrayList<>();
		List<ResourceManager> managers = new ArrayList<>();
		ResourceManager rootManager = (docBase.isDirectory() ? new FileResourceManager(docBase, 0)
				: new JarResourceManager(docBase));
		if (root != null) {
			rootManager = new LoaderHidingResourceManager(rootManager);
		}
		managers.add(rootManager);
		for (URL url : metaInfResourceUrls) {
			if ("file".equals(url.getProtocol())) {
				try {
					File file = new File(url.toURI());
					if (file.isFile()) {
						resourceJarUrls.add(new URL("jar:" + url + "!/"));
					}
					else {
						managers.add(new FileResourceManager(new File(file, "META-INF/resources"), 0));
					}
				}
				catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			}
			else {
				resourceJarUrls.add(url);
			}
		}
		managers.add(new MetaInfResourcesResourceManager(resourceJarUrls));
		return new CompositeResourceManager(managers.toArray(new ResourceManager[0]));
	}

	private File getCanonicalDocumentRoot(File docBase) {
		try {
			File root = (docBase != null) ? docBase : createTempDir("undertow-docbase");
			return root.getCanonicalFile();
		}
		catch (IOException ex) {
			throw new IllegalStateException("Cannot get canonical document root", ex);
		}
	}

	private void configureErrorPages(DeploymentInfo deployment) {
		for (ErrorPage errorPage : getErrorPages()) {
			deployment.addErrorPage(getUndertowErrorPage(errorPage));
		}
	}

	private io.undertow.servlet.api.ErrorPage getUndertowErrorPage(ErrorPage errorPage) {
		if (errorPage.getStatus() != null) {
			return new io.undertow.servlet.api.ErrorPage(errorPage.getPath(), errorPage.getStatusCode());
		}
		if (errorPage.getException() != null) {
			return new io.undertow.servlet.api.ErrorPage(errorPage.getPath(), errorPage.getException());
		}
		return new io.undertow.servlet.api.ErrorPage(errorPage.getPath());
	}

	private void configureMimeMappings(DeploymentInfo deployment) {
		for (Mapping mimeMapping : getMimeMappings()) {
			deployment.addMimeMapping(new MimeMapping(mimeMapping.getExtension(), mimeMapping.getMimeType()));
		}
	}

	private void removeSuperfluousMimeMappings(DeploymentImpl deployment, DeploymentInfo deploymentInfo) {
		// DeploymentManagerImpl will always add MimeMappings.DEFAULT_MIME_MAPPINGS
		// but we only want ours
		Map<String, String> mappings = new HashMap<>();
		for (MimeMapping mapping : deploymentInfo.getMimeMappings()) {
			mappings.put(mapping.getExtension().toLowerCase(Locale.ENGLISH), mapping.getMimeType());
		}
		deployment.setMimeExtensionMappings(mappings);
	}

	/**
	 * Factory method called to create the {@link UndertowServletWebServer}. Subclasses
	 * can override this method to return a different {@link UndertowServletWebServer} or
	 * apply additional processing to the {@link Builder} and {@link DeploymentManager}
	 * used to bootstrap Undertow
	 * @param builder the builder
	 * @param manager the deployment manager
	 * @param port the port that Undertow should listen on
	 * @return a new {@link UndertowServletWebServer} instance
	 */
	protected UndertowServletWebServer getUndertowWebServer(Builder builder, DeploymentManager manager, int port) {
		List<HttpHandlerFactory> initialHandlerFactories = new ArrayList<>();
		initialHandlerFactories.add(new DeploymentManagerHttpHandlerFactory(manager));
		HttpHandlerFactory cooHandlerFactory = getCookieHandlerFactory(manager.getDeployment());
		if (cooHandlerFactory != null) {
			initialHandlerFactories.add(cooHandlerFactory);
		}
		List<HttpHandlerFactory> httpHandlerFactories = this.delegate.createHttpHandlerFactories(this,
				initialHandlerFactories.toArray(new HttpHandlerFactory[0]));
		return new UndertowServletWebServer(builder, httpHandlerFactories, getContextPath(), port >= 0);
	}

	private HttpHandlerFactory getCookieHandlerFactory(Deployment deployment) {
		SameSite sessionSameSite = getSession().getCookie().getSameSite();
		List<CookieSameSiteSupplier> suppliers = new ArrayList<>();
		if (sessionSameSite != null) {
			String sessionCookieName = deployment.getServletContext().getSessionCookieConfig().getName();
			suppliers.add(CookieSameSiteSupplier.of(sessionSameSite).whenHasName(sessionCookieName));
		}
		if (!CollectionUtils.isEmpty(getCookieSameSiteSuppliers())) {
			suppliers.addAll(getCookieSameSiteSuppliers());
		}
		return (!suppliers.isEmpty()) ? (next) -> new SuppliedSameSiteCookieHandler(next, suppliers) : null;
	}

	/**
	 * {@link ServletContainerInitializer} to initialize {@link ServletContextInitializer
	 * ServletContextInitializers}.
	 */
	private static class Initializer implements ServletContainerInitializer {

		private final ServletContextInitializer[] initializers;

		Initializer(ServletContextInitializer[] initializers) {
			this.initializers = initializers;
		}

		@Override
		public void onStartup(Set<Class<?>> classes, ServletContext servletContext) throws ServletException {
			for (ServletContextInitializer initializer : this.initializers) {
				initializer.onStartup(servletContext);
			}
		}

	}

	/**
	 * {@link ResourceManager} that exposes resource in {@code META-INF/resources}
	 * directory of nested (in {@code BOOT-INF/lib} or {@code WEB-INF/lib}) jars.
	 */
	private static final class MetaInfResourcesResourceManager implements ResourceManager {

		private final List<URL> metaInfResourceJarUrls;

		private MetaInfResourcesResourceManager(List<URL> metaInfResourceJarUrls) {
			this.metaInfResourceJarUrls = metaInfResourceJarUrls;
		}

		@Override
		public void close() throws IOException {
		}

		@Override
		public Resource getResource(String path) {
			for (URL url : this.metaInfResourceJarUrls) {
				URLResource resource = getMetaInfResource(url, path);
				if (resource != null) {
					return resource;
				}
			}
			return null;
		}

		@Override
		public boolean isResourceChangeListenerSupported() {
			return false;
		}

		@Override
		public void registerResourceChangeListener(ResourceChangeListener listener) {
		}

		@Override
		public void removeResourceChangeListener(ResourceChangeListener listener) {

		}

		private URLResource getMetaInfResource(URL resourceJar, String path) {
			try {
				String urlPath = URLEncoder.encode(ENCODED_SLASH.matcher(path).replaceAll("/"), "UTF-8");
				URL resourceUrl = new URL(resourceJar + "META-INF/resources" + urlPath);
				URLResource resource = new URLResource(resourceUrl, path);
				if (resource.getContentLength() < 0) {
					return null;
				}
				return resource;
			}
			catch (Exception ex) {
				return null;
			}
		}

	}

	/**
	 * {@link ResourceManager} to hide Spring Boot loader classes.
	 */
	private static final class LoaderHidingResourceManager implements ResourceManager {

		private final ResourceManager delegate;

		private LoaderHidingResourceManager(ResourceManager delegate) {
			this.delegate = delegate;
		}

		@Override
		public Resource getResource(String path) throws IOException {
			if (path.startsWith("/org/springframework/boot")) {
				return null;
			}
			return this.delegate.getResource(path);
		}

		@Override
		public boolean isResourceChangeListenerSupported() {
			return this.delegate.isResourceChangeListenerSupported();
		}

		@Override
		public void registerResourceChangeListener(ResourceChangeListener listener) {
			this.delegate.registerResourceChangeListener(listener);
		}

		@Override
		public void removeResourceChangeListener(ResourceChangeListener listener) {
			this.delegate.removeResourceChangeListener(listener);
		}

		@Override
		public void close() throws IOException {
			this.delegate.close();
		}

	}

	/**
	 * {@link HttpHandler} to apply {@link CookieSameSiteSupplier supplied}
	 * {@link SameSite} cookie values.
	 */
	private static class SuppliedSameSiteCookieHandler implements HttpHandler {

		private final HttpHandler next;

		private final List<CookieSameSiteSupplier> suppliers;

		SuppliedSameSiteCookieHandler(HttpHandler next, List<CookieSameSiteSupplier> suppliers) {
			this.next = next;
			this.suppliers = suppliers;
		}

		@Override
		public void handleRequest(HttpServerExchange exchange) throws Exception {
			exchange.addResponseCommitListener(this::beforeCommit);
			this.next.handleRequest(exchange);
		}

		private void beforeCommit(HttpServerExchange exchange) {
			for (Cookie cookie : exchange.responseCookies()) {
				SameSite sameSite = getSameSite(asServletCookie(cookie));
				if (sameSite != null) {
					cookie.setSameSiteMode(sameSite.attributeValue());
				}
			}
		}

		private javax.servlet.http.Cookie asServletCookie(Cookie cookie) {
			PropertyMapper map = PropertyMapper.get().alwaysApplyingWhenNonNull();
			javax.servlet.http.Cookie result = new javax.servlet.http.Cookie(cookie.getName(), cookie.getValue());
			map.from(cookie::getComment).to(result::setComment);
			map.from(cookie::getDomain).to(result::setDomain);
			map.from(cookie::getMaxAge).to(result::setMaxAge);
			map.from(cookie::getPath).to(result::setPath);
			result.setSecure(cookie.isSecure());
			result.setVersion(cookie.getVersion());
			result.setHttpOnly(cookie.isHttpOnly());
			return result;
		}

		private SameSite getSameSite(javax.servlet.http.Cookie cookie) {
			for (CookieSameSiteSupplier supplier : this.suppliers) {
				SameSite sameSite = supplier.getSameSite(cookie);
				if (sameSite != null) {
					return sameSite;
				}
			}
			return null;
		}

	}

}

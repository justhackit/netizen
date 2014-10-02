package org.codemahal.netizen.common;

import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class NetizenFactory {
	private static RequestConfig globalConfig = null;
	private static CookieStore cookieStore = null;
	private static HttpClientContext clientContext = null;
	private static CloseableHttpClient httpClient = null;

	public static HttpClient getTheClient() {
		// Creating MultiThreadedHttpConnectionManager

		// Passing it to the HttpClient.
		if (httpClient == null) {
			globalConfig = RequestConfig.custom()
					.setCookieSpec(CookieSpecs.BEST_MATCH).build();
			cookieStore = new BasicCookieStore();
			clientContext = HttpClientContext.create();
			clientContext.setCookieStore(cookieStore);
			httpClient = HttpClients.custom()
					.setDefaultRequestConfig(globalConfig)
					.setDefaultCookieStore(cookieStore).build();
		}
		return httpClient;
	}

	public static RequestConfig getGlobalConfig() {
		return globalConfig;
	}

	public static void setGlobalConfig(RequestConfig globalConfig) {
		NetizenFactory.globalConfig = globalConfig;
	}

	public static CookieStore getCookieStore() {
		return cookieStore;
	}

	public static void setCookieStore(CookieStore cookieStore) {
		NetizenFactory.cookieStore = cookieStore;
	}

	public static HttpClientContext getClientContext() {
		return clientContext;
	}

	public static void setClientContext(HttpClientContext clientContext) {
		NetizenFactory.clientContext = clientContext;
	}

}

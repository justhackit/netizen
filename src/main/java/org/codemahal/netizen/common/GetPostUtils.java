package org.codemahal.netizen.common;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;

public class GetPostUtils {
	static Logger LOG = Logger.getLogger(GetPostUtils.class);
	
	private static HttpResponse getGenericPage(HttpRequestBase request) throws ClientProtocolException, IOException{
		LOG.info("Sending request to "+request.getURI().getHost()+" to get "+request.getURI().getPath());
		HttpResponse resp = NetizenFactory.getTheClient().execute(request);
		LOG.info("Response Received:"+resp.getStatusLine());
		int statusCode = resp.getStatusLine().getStatusCode();
		if(statusCode >= 400 && statusCode <= 599 ){
			LOG.error("Error occured in communication.");
		}
		return resp;
	}
	
	/*
	 * This method is to execute GET method. 
	 */
	public static HttpResponse getPage(URI uri) throws ClientProtocolException, IOException{
		HttpGet getRequest = new HttpGet(uri);
		return getGenericPage(getRequest);
	}
	
	/*
	 * This method is to execute POST method. The post parameters are expected in "entity" parameter. 
	 */
	public static HttpResponse getPage(URI uri,UrlEncodedFormEntity entity) throws ClientProtocolException, IOException{
		HttpPost postRequest = new HttpPost(uri);
		postRequest.setEntity(entity);
		return getGenericPage(postRequest);
	}

	/*
	 * This method directly returns the content of the GET URI.
	 */
	public static String getContentAsText(URI uri) throws ClientProtocolException, IOException{
		HttpResponse resp = getPage(uri);
		HttpEntity entities = resp.getEntity();
		LOG.debug("Response content Length#"+CommonUtils.bytesToHigher(entities.getContentLength()));
		InputStream in = entities.getContent();
		String theContent = CommonUtils.getContentAsText(in);
		return theContent;
	}
	
	/*
	 * This method directly returns the content of the POST URI.
	 */
	public static String getContentAsText(URI uri,UrlEncodedFormEntity entity) throws ClientProtocolException, IOException{
		HttpResponse resp = getPage(uri, entity);
		HttpEntity entities = resp.getEntity();
		LOG.debug("Response content Length#"+CommonUtils.bytesToHigher(entities.getContentLength()));
		InputStream in = entities.getContent();
		String theContent = CommonUtils.getContentAsText(in);
		return theContent;
	}
	
	/*
	 * This method directly returns the content of the GET URL string.
	 */
	public static String getContentAsText(String uri) throws ClientProtocolException, IOException, URISyntaxException{
		return getContentAsText(new URI(uri));
	}
	
	/*
	 * This method directly returns the content of the POST URL string.
	 */
	public static String getContentAsText(String uri,UrlEncodedFormEntity entity) throws ClientProtocolException, IOException, URISyntaxException{
		return getContentAsText(new URI(uri),entity);
	}

}

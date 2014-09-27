package org.codemahal.netizen.common;

import java.io.IOException;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.log4j.Logger;

public class POSTUtils {
	static Logger LOG = Logger.getLogger(POSTUtils.class);
	
	public static HttpResponse getPage(URI uri,UrlEncodedFormEntity entity,HttpClient client) throws ClientProtocolException, IOException{
		HttpPost getRequest = new HttpPost(uri);
		getRequest.setEntity(entity);
		LOG.info("Sending request to "+uri.getHost()+" to get "+uri.getPath());
		HttpResponse resp = client.execute(getRequest);
		LOG.info("Response Received:"+resp.getStatusLine());
		int statusCode = resp.getStatusLine().getStatusCode();
		if(statusCode >= 400 && statusCode <= 599 ){
			LOG.error("Error occured in communication.");
		}
		return resp;
	}

}

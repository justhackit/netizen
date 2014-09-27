package org.codemahal.netizen.common;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class NetizenFactory {
	private static HttpClient httpClient=null;
	
	public static HttpClient getTheClient(){
		// Creating MultiThreadedHttpConnectionManager  
  
        // Passing it to the HttpClient.  
		if(httpClient==null){
			httpClient = HttpClientBuilder.create().build();
		}
        return httpClient;
	}

}

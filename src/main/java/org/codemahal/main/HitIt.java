package org.codemahal.main;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import learn.ing.getting.MyFirstGet;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.codemahal.dto.BasicNetizenDTO;
import org.codemahal.netizen.churner.jsoupimpl.ChurnItByJsoup;
import org.codemahal.netizen.common.GetPostUtils;
import org.codemahal.netizen.common.ReadUtils;

public class HitIt {
	static Logger LOG = Logger.getLogger(HitIt.class);
	
	public void knockKnock(BasicNetizenDTO dto) throws ClientProtocolException, IOException{
		if(dto.getRequest().getMethod().equalsIgnoreCase(HttpGet.METHOD_NAME)){
			HttpResponse resp = GetPostUtils.getPage(dto.getUri());
			HttpEntity entity = resp.getEntity();
			dto.setStatusCode(resp.getStatusLine().getStatusCode());
			dto.setRespStatusLine(resp.getStatusLine().toString());
			dto.setContentWeight(ReadUtils.bytesToHigher(entity.getContentLength()));
			dto.setResponseHeaders(resp.getAllHeaders());
			dto.setTheContent(ReadUtils.getContentAsText(entity.getContent()));
		}
	}
	public static void main(String[] args) throws Exception {
		Logger.getLogger("httpclient.wire.header").setLevel(Level.DEBUG);
		BasicNetizenDTO dto = new BasicNetizenDTO();
		URIBuilder uriBuilder = null;
		uriBuilder = new URIBuilder("http://www.redbus.in");
		//uriBuilder.setParameter("email", "ajay.edap@gmail.com");
		//uriBuilder.setParameter("pass","constraint02");
		dto.setUri(uriBuilder.build());
		dto.setRequest(new HttpGet());
		HitIt hit = new HitIt();
		hit.knockKnock(dto);
		System.out.println(dto);
		System.out.println("---------------------");
		ChurnItByJsoup churn = new ChurnItByJsoup();
		churn.getTag(dto.getTheContent(), "");
		
	}
}

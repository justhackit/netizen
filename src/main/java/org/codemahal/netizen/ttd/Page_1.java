package org.codemahal.netizen.ttd;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import learn.ing.getting.MyFirstGet;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.codemahal.dto.BasicNetizenDTO;
//import org.codemahal.netizen.churner.jsoupimpl.ChurnItByJsoup;
import org.codemahal.netizen.common.GetPostUtils;
import org.codemahal.netizen.common.ReadUtils;

public class Page_1 {
	static Logger LOG = Logger.getLogger(Page_1.class);
	
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
}

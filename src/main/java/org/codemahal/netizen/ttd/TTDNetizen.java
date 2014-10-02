package org.codemahal.netizen.ttd;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.codemahal.dto.BasicNetizenDTO;

public class TTDNetizen {
	
	public static void main(String[] args) throws Exception {
		Logger.getLogger("httpclient.wire.header").setLevel(Level.DEBUG);
		BasicNetizenDTO page1Dto = new BasicNetizenDTO();
		URIBuilder page1_uriBuilder = null;
		page1_uriBuilder = new URIBuilder("https://www.ttdsevaonline.com/");
		page1Dto.setUri(page1_uriBuilder.build());
		page1Dto.setRequest(new HttpGet());
		Page_1 hit = new Page_1();
		hit.knockKnock(page1Dto);
		System.out.println(page1Dto);
		System.out.println("---------------------");		
	}


}

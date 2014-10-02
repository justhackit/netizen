package org.codemahal.netizen.ttd;

import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.codemahal.dto.BasicNetizenDTO;
import org.codemahal.netizen.churner.jsoupimpl.ChurnItByJsoup;

public class TTDNetizen {

	public static void main(String[] args) throws Exception {
		Logger.getLogger("httpclient.wire.header").setLevel(Level.DEBUG);
		BasicNetizenDTO page1Dto = new BasicNetizenDTO();
		URIBuilder page1_uriBuilder = null;
		page1_uriBuilder = new URIBuilder("https://www.ttdsevaonline.com/");
		page1Dto.setUri(page1_uriBuilder.build());
		page1Dto.setRequest(new HttpGet());
		Page_1 hiSweety = new Page_1();
		hiSweety.knockKnock(page1Dto);
		/*
		 * List<Cookie> cookies = NetizenFactory.getCookieStore().getCookies();
		 * for(Cookie cookie:cookies){ System.out.println(cookie); }
		 */
		BasicNetizenDTO page2Dto = new BasicNetizenDTO();
		URIBuilder page2_uriBuilder = null;
		page2_uriBuilder = new URIBuilder(
				"https://www.ttdsevaonline.com/eSeva/AvailabilityChart.aspx");
		List<NameValuePair> formParameters = ChurnItByJsoup.getFormParameters(
				page1Dto.getTheContent(), "input");
		formParameters.addAll(ChurnItByJsoup.getFormParameters(
				page1Dto.getTheContent(), "select"));
		for (int i = 0; i < formParameters.size(); i++) {
			BasicNameValuePair aParam = (BasicNameValuePair) formParameters
					.get(i);
			if (!aParam.getName().contains("VIEWSTATE")
					&& !aParam.getName().contains("EVENTVALIDATION")) {
				formParameters.remove(i);
			}
		}

		formParameters.add(new BasicNameValuePair("__EVENTTARGET", "cmbSeva"));
		formParameters.add(new BasicNameValuePair("__EVENTARGUMENT", ""));
		formParameters.add(new BasicNameValuePair("__LASTFOCUS", ""));
		formParameters.add(new BasicNameValuePair("cmbSeva", "3"));
		formParameters.add(new BasicNameValuePair("cmdDate", "11/01/2014"));
		page2_uriBuilder.setParameters(formParameters);
		page2Dto.setUri(page2_uriBuilder.build());
		page2Dto.setFormParms(formParameters);
		page2Dto.setRequest(new HttpPost());
		hiSweety.knockKnock(page2Dto);
		System.out.println(page2Dto);
	}

}

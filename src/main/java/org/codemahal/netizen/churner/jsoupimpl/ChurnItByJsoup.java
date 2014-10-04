package org.codemahal.netizen.churner.jsoupimpl;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.codemahal.dto.FormTagDTO;
import org.codemahal.netizen.common.CommonUtils;
import org.codemahal.netizen.common.GetPostUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ChurnItByJsoup {

	// TODO start from here. Task is: Get the form url with query parameters
	public static List<NameValuePair> getFormParameters(String theContent,
			String tagName) {
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		Document doc = Jsoup.parse(theContent);
		Elements form = doc.getElementsByTag(tagName);
		for (Element aTag : form) {
			NameValuePair temp = new BasicNameValuePair(aTag.attr("name"),
					aTag.attr("value"));
			formParams.add(temp);
		}
		return formParams;
	}

	public static String getTitle(String theContent) {
		return Jsoup.parse(theContent).title();
	}

	/*
	 * This method takes html code and extracts information related to form
	 * submission like destination url, post or get, form parameters etc.
	 */
	public static FormTagDTO setFormTagDTO(String theContent) {
		FormTagDTO formTagDTO = new FormTagDTO();
		Document doc = Jsoup.parse(theContent);
		Elements form = doc.getElementsByTag("form");
		if (form.size() > 0) {
			Element aFormTag = form.get(0);
			formTagDTO.setFormId(aFormTag.attr("id"));
			try {
				formTagDTO.setDestURL(new URI(aFormTag.attr("action")));
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			if(aFormTag.attr("method").equalsIgnoreCase("post")){
				formTagDTO.setRequestType(new HttpPost());
			}else{
				formTagDTO.setRequestType(new HttpGet());
			}
			formTagDTO.setFormParameters(getFormParameters(aFormTag.toString(), "input"));
		}
		return formTagDTO;
	}

	public static void main(String[] args) throws ClientProtocolException,
			IOException, URISyntaxException {
/*		String theContent = CommonUtils.getContentAsText(new File(
				"C:/Users/chinna/git/netizen/resources/htmls/facebook_login.html"));
*/	
		String theContent = GetPostUtils.getContentAsText("http://www.irctc.co.in/");
		FormTagDTO test = setFormTagDTO(theContent);
		System.out.println(test);
	}
}

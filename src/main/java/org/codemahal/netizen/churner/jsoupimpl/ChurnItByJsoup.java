package org.codemahal.netizen.churner.jsoupimpl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.codemahal.netizen.common.ReadUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ChurnItByJsoup {

	//TODO start from here. Task is: Get the form url with query parameters
	public static List<NameValuePair> getFormParameters(String theContent, String tagName) {
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		Document doc = Jsoup.parse(theContent);
		Elements form = doc.getElementsByTag(tagName);
		for(Element aTag:form){
			NameValuePair temp = new BasicNameValuePair(aTag.attr("name"), aTag.attr("value"));
			formParams.add(temp);
		}
		return formParams;
	}

	public static String getTitle(String theContent) {
		return Jsoup.parse(theContent).title();
	}

	public static void main(String[] args) {
		String theContent = ReadUtils.getContentAsText(new File("C:/Users/chinna/git/netizen/resources/htmls/TTDPage_2.html"));
		List<NameValuePair> test = ChurnItByJsoup.getFormParameters(theContent, "input");
		test.addAll(ChurnItByJsoup.getFormParameters(theContent, "select"));
		System.out.println(test.size());
		
	}
}

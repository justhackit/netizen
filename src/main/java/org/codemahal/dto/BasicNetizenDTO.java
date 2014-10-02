package org.codemahal.dto;
import java.net.URI;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpRequestBase;
import org.codemahal.netizen.common.ReadUtils;
import org.jsoup.Jsoup;
/*
 * The basic DTO that holds some info 
 * 
*/
public class BasicNetizenDTO {

	private URI uri;
	private HttpRequestBase request;
	private List<NameValuePair> formParms;
	private HttpResponse response;
	private int statusCode;
	private String respStatusLine;
	private String contentWeight;
	private Header[] responseHeaders;
	private String theContent;

	public URI getUri() {
		return uri;
	}

	public void setUri(URI uri) {
		this.uri = uri;
	}

	public HttpRequestBase getRequest() {
		return request;
	}

	public void setRequest(HttpRequestBase request) {
		this.request = request;
		this.request.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		this.request.addHeader("Accept-Encoding", "gzip,deflate,sdch");
		this.request.addHeader("Accept-Language", "en-US,en;q=0.8,ms;q=0.6");
		this.request.addHeader("Connection","keep-alive");
		this.request.addHeader("User-Agent","Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.94 Safari/537.36");
		this.request.addHeader("Host",this.uri.toString());
	}

	public List<NameValuePair> getFormParms() {
		return formParms;
	}

	public void setFormParms(List<NameValuePair> formParms) {
		this.formParms = formParms;
	}

	public HttpResponse getResponse() {
		return response;
	}

	public void setResponse(HttpResponse response) {
		this.response = response;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getRespStatusLine() {
		return respStatusLine;
	}

	public void setRespStatusLine(String respStatusLine) {
		this.respStatusLine = respStatusLine;
	}

	public String getContentWeight() {
		return contentWeight;
	}

	public void setContentWeight(String contentWeight) {
		this.contentWeight = contentWeight;
	}

	public void setResponseHeaders(Header[] headers) {
		this.responseHeaders = headers;
	}

	public Header[] getResponseHeaders() {
		return responseHeaders;
	}

	public String getTheContent() {
		return theContent;
	}

	public void setTheContent(String theContent) {
		this.theContent = theContent;
	}
	

	public String toString() {
		StringBuilder theString = new StringBuilder();
		theString.append("URL:" + this.uri + "\n");
		theString.append("Method Type:" + this.request.getMethod() + "\n");
		if(this.formParms!=null){
			theString.append("Form parameters:" + this.formParms + "\n");
		}
		if(this.request.getAllHeaders().length>0){
			theString.append("Request Headers:\n");
			for(Header reqHdr : this.request.getAllHeaders()){
				theString.append("\t"+reqHdr.getName()+"->"+reqHdr.getValue()+"\n");
			}
		}
		theString.append("Response Status Line:" + this.respStatusLine + "\n");
		if (this.responseHeaders.length > 0) {
			theString.append("Response Headers:\n");
			for (Header header : this.responseHeaders) {
				theString.append("\t"+header.getName() + "->" + header.getValue()+"\n");
			}
		}
		theString.append("The Content (of weight# "+ReadUtils.bytesToHigher(theContent.length())+"):\n");
		theString.append("Page Title: "+Jsoup.parse(this.theContent.toString()).title()+"\n");
		theString.append(this.theContent.substring(0, 100)
				+ "\n.......\n"
				+ this.theContent.substring(this.theContent.length() - 100)+"\n");
		return theString.toString();
	}


}

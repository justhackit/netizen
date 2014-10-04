package org.codemahal.dto;

import java.net.URI;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpRequestBase;

public class FormTagDTO {
	private URI destURL;
	private HttpRequestBase requestType;
	private String formId;
	private List<NameValuePair> formParameters;
	public URI getDestURL() {
		return destURL;
	}
	public void setDestURL(URI destURL) {
		this.destURL = destURL;
	}
	public HttpRequestBase getRequestType() {
		return requestType;
	}
	public void setRequestType(HttpRequestBase requestType) {
		this.requestType = requestType;
	}
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}
	public List<NameValuePair> getFormParameters() {
		return formParameters;
	}
	public void setFormParameters(List<NameValuePair> formParameters) {
		this.formParameters = formParameters;
	}
	
	public String toString(){
		if(this.destURL==null){
			return "Couldn't find any <form> tag in the html";
		}
		StringBuilder buildIt = new StringBuilder();
		buildIt.append("Target URL: "+this.destURL+"\n");
		buildIt.append("Method Type: "+requestType.getMethod()+"\n");
		buildIt.append("Form Parameters:\n");
		for(NameValuePair aParam:formParameters){
			buildIt.append("\t"+aParam.getName()+":"+aParam.getValue()+"\n");
		}
		return buildIt.toString();
	}

}

package com.eptd.dminer.core;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

public class Authorization {
	private Header header;
	private String OAuthToken;
	
	public Authorization(String OAuthToken){
		 setHeader(new BasicHeader("Authorization", "token " + OAuthToken));
	}

	public String getOAuthToken() {
		return OAuthToken;
	}

	public void setOAuthToken(String oAuthToken) {
		OAuthToken = oAuthToken;
		updateHeader();
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}
	
	private void updateHeader(){
		//update the header attribute of this object
		setHeader(new BasicHeader("Authorization", "token " + OAuthToken));
	}
}

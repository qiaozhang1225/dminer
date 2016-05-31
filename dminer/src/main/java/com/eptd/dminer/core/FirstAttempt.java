package com.eptd.dminer.core;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class FirstAttempt {

	public static void main(String[] args) {
		HttpClient httpClient = HttpClientBuilder.create().build();		
		try {
			HttpGet request = new HttpGet("https://api.github.com/repos/code4craft/webmagic/stargazers");
			request.setHeader("Authorization", "token 48e0313cdf36c082d12ccb193ffa12f17186f171");
			HttpResponse response = httpClient.execute(request);
			String resp_str = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
			//JsonObject obj = new JsonParser().parse(resp_str).getAsJsonObject();
			/*If the returned data is an array of json object*/
			//System.out.println("Response:"+ response);
			System.out.println("Response:"+ resp_str);
			
			try {
		        Runtime.getRuntime().exec("cmd.exe /c start");
		        System.out.println("ok");
		    } catch (IOException ex) {
		        ex.printStackTrace();
		    }
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

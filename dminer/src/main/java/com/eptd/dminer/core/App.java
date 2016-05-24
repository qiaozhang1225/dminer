package com.eptd.dminer.core;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Authorization auth = new Authorization("69af016d20ea1dc8ab821097b0828a828c079b57");
    	HttpClient httpClient = HttpClientBuilder.create().build();
    	
    	try {
			HttpGet request = new HttpGet("https://api.github.com/repos/code4craft/webmagic");
			request.addHeader(auth.getHeader());
			HttpResponse response = httpClient.execute(request);
			String resp_str = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
			JsonObject obj = new JsonParser().parse(resp_str).getAsJsonObject();
			/*If the returned data is an array of json object*/
			//System.out.println("Response:"+ response);
			System.out.println("Response:"+ obj.get("id").getAsLong());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

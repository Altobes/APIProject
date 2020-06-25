package com.vogella.maven.quickstart;

import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

//import APIWork.JsonNode;

public class Runner {
	public static void main(String[] args) throws MalformedURLException, IOException {
		// Host url
	      String host = "https://amazon-price1.p.rapidapi.com/search?keywords=GPU&marketplace=US";
	      String charset = "UTF-8";
	      // Headers for a request
	      String x_rapidapi_host = "amazon-price1.p.rapidapi.com";
	      String x_rapidapi_key = "d973d073a9mshe301352d689bfbap12b83ajsnf2d451ad74be";
	      // Params
	     // String s = "Pen";
	  // Format query for preventing encoding problems
	      //String query = String.format("s=%s",
	      // URLEncoder.encode(s, charset));
	      
	      HttpResponse<JsonNode> response = null;
		try {
			response = Unirest.get(host)
				      .header("x-rapidapi-host", x_rapidapi_host)
				      .header("x-rapidapi-key", x_rapidapi_key)
				      .asJson();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    System.out.println(response.getStatus());
	    System.out.println(response.getHeaders().get("Content-Type"));
	   /* Gson gson = new GsonBuilder().setPrettyPrinting().create();
	    JsonParser jp = new JsonParser();
	    JsonElement je = jp.parse(response.getBody().toString());
	    String prettyJsonString = gson.toJson(je); */
	    JsonNode body = response.getBody();
	    JSONArray arr = body.getArray();
	    JSONObject json = (JSONObject) arr.get(0);
	    System.out.println(json.keySet());
	}
}

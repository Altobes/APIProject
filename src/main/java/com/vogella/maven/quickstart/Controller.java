package com.vogella.maven.quickstart;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Controller {
	
	public static JSONObject retrieveAmazon(String host) {
		 HttpResponse<JsonNode> response = null;
		  try {
			  response = Unirest.get(host)
				      .header("x-rapidapi-host", Variables.cont.amazonHost)
				      .header("x-rapidapi-key", Variables.cont.rapidKey)
				      .asJson();
		  } catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }
		  
		 
		  JsonNode body = response.getBody();
		  JSONArray arr = body.getArray();
		  JSONObject json = (JSONObject) arr.get(0);
		  return json;
	}
	
	public static JSONObject retrieveTarget(String host) {
	      HttpResponse<JsonNode> response = null;
	      try {
	    	  response = Unirest.get(host)
				      .header("x-rapidapi-host", Variables.cont.targetHost)
				      .header("x-rapidapi-key", Variables.cont.rapidKey)
				      .asJson();
	      } catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	      }
	      
	      JsonNode body = response.getBody();
		    JSONArray arr = body.getArray();
		    JSONObject json = (JSONObject) arr.get(0);
		    JSONArray arr2 = (JSONArray) json.get("products");
		    JSONObject json2 = (JSONObject) arr2.get(0);
		    return json2;
	}

}

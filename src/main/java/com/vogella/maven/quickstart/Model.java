package com.vogella.maven.quickstart;

import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class Model {
	
	public static ArrayList<String> amazonSearch(String query) throws JSONException, IOException {
		query = query.replace(" ", "+");
  	  	String host = Variables.model.amazonURL1 + query + Variables.model.amazonURL2;
  	  	JSONObject json = Controller.retrieveAmazon(host);
  	  	
  	  	ArrayList<String> list = new ArrayList<String>();
	    list.add(json.getString("title"));
	    list.add(json.getString("price"));
	    list.add(json.getString("rating"));
	    list.add(json.getString("imageUrl"));
	      
	    return list;
  }
	
	public static ArrayList<String> targetSearch(String query) {
		query = query.replace(" ", "%20");
  	  	String host = Variables.cont.targetURL;
	    host = host + query;
	    JSONObject json = Controller.retrieveTarget(host);
		JSONObject json1 = (JSONObject) json.get("price");
	    int price = json1.getInt("current_retail_min");
	    double rating = json.getDouble("average_rating");
	    
	    ArrayList<String> list = new ArrayList<String>();
	    list.add(json.getString("title"));
	    list.add(Integer.toString(price));
	    list.add(Double.toString(rating));
		return list;
      
	}
	
}

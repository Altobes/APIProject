package com.vogella.maven.quickstart;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.body.RequestBodyEntity;

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
		    JSONArray arr2 = null;
		    try {
		    	arr2 = (JSONArray) json.get("products");
		    }
		   	catch(JSONException e) {
		   		return null;
		    	  //  Block of code to handle errors
		   	}

		    
		    
		    JSONObject json2 = (JSONObject) arr2.get(0);
		    return json2;
	}
	
	public static double getShipping(ArrayList<String> location) {
		String getHost = "https://api.shipengine.com/v1/labels";
		String Address = location.get(0);
		String city = location.get(1);
		String state = location.get(2);
		String postal = location.get(3);
		double weight = isDouble(location.get(4), true);
		double length = isDouble(location.get(5), false);
		double width = isDouble(location.get(6), false);
		double height = isDouble(location.get(7), false);
		
		String bodyFormat = String.format("{\n  \"shipment\": {\n    \"packages\": [\n      {\n        \"weight\": {\n          \"value\": %s,\n          \"unit\": \"ounce\"\n        },\n        \"dimensions\": {\n          \"unit\": \"inch\",\n          \"length\": %s,\n          \"width\": %s,\n          \"height\": %s\n        }\n      }\n    ],\n    \"service_code\": \"usps_priority_mail\",\n    \"ship_to\": {\n      \"name\": \"Amanda Miller\",\n      \"phone\": \"555-555-5555\",\n      \"address_line1\": \"%s\",\n      \"city_locality\": \"%s\",\n      \"state_province\": \"%s\",\n      \"postal_code\": \"%s\",\n      \"country_code\": \"US\",\n      \"address_residential_indicator\": \"yes\"\n    },\n    \"ship_from\": {\n      \"company_name\": \"Example Corp.\",\n      \"name\": \"John Doe\",\n      \"phone\": \"111-111-1111\",\n      \"address_line1\": \"4009 Marathon Blvd\",\n      \"address_line2\": \"Suite 300\",\n      \"city_locality\": \"Austin\",\n      \"state_province\": \"TX\",\n      \"postal_code\": \"78756\",\n      \"country_code\": \"US\",\n      \"address_residential_indicator\": \"no\"\n    }\n  }\n}",weight,length,width,height,Address,city, state,postal);
		HttpResponse<JsonNode> response1 = null;
		try {
			  response1 = Unirest.post(getHost)
					  .header("Content-Type", "application/json")
					  .header("API-Key", "TEST_P+woI7sMYPYdupkgrjsM/vmAZL6Z4MymboAxwCtVMRA")
					  .header("Host", "api.shipengine.com")
					  //.body("{\n  \"shipment\": {\n    \"packages\": [\n      {\n        \"weight\": {\n          \"value\": 9.6,\n          \"unit\": \"ounce\"\n        },\n        \"dimensions\": {\n          \"unit\": \"inch\",\n          \"length\": 12.0,\n          \"width\": 7.1,\n          \"height\": 6.0\n        }\n      }\n    ],\n    \"service_code\": \"usps_priority_mail\",\n    \"ship_to\": {\n      \"name\": \"Amanda Miller\",\n      \"phone\": \"555-555-5555\",\n      \"address_line1\": \"525 S Winchester Blvd\",\n      \"city_locality\": \"San Jose\",\n      \"state_province\": \"CA\",\n      \"postal_code\": \"95128\",\n      \"country_code\": \"US\",\n      \"address_residential_indicator\": \"yes\"\n    },\n    \"ship_from\": {\n      \"company_name\": \"Example Corp.\",\n      \"name\": \"John Doe\",\n      \"phone\": \"111-111-1111\",\n      \"address_line1\": \"4009 Marathon Blvd\",\n      \"address_line2\": \"Suite 300\",\n      \"city_locality\": \"Austin\",\n      \"state_province\": \"TX\",\n      \"postal_code\": \"78756\",\n      \"country_code\": \"US\",\n      \"address_residential_indicator\": \"no\"\n    }\n  }\n}")
			  		  .body(bodyFormat)
					  .asJson();
  } catch (UnirestException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
  }

		JsonNode shipment = response1.getBody();
		JSONArray arr = shipment.getArray();
		JSONObject obj = (JSONObject) arr.get(0);
		System.out.println(obj.toString());
		JSONObject amount = (JSONObject) obj.get("shipment_cost");
		return (double) (amount.get("amount"));
	}
	
	public static double getNumbers(boolean weight) {
		double num;
		if (weight) {
			num = ThreadLocalRandom.current().nextDouble(1, 25);
		}
		else {
			num = ThreadLocalRandom.current().nextDouble(1, 49);
		}
		return num;
	}
	
	static double isDouble(String str, boolean weight) {
        try {
            Double dub = Double.parseDouble(str);
            return dub;
        } catch (NumberFormatException e) {
            return getNumbers(weight);
        }
    }

}

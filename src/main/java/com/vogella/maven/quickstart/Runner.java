package com.vogella.maven.quickstart;

import java.awt.Component;
import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.body.RequestBodyEntity;

//import APIWork.JsonNode;

public class Runner {
	public static void main(String[] args) throws MalformedURLException, IOException {
		
	
				String getHost = "https://api.shipengine.com/v1/labels";
				HttpResponse<JsonNode> response1 = null;
				try {
					  response1 = Unirest.post(getHost)
							  .header("Content-Type", "application/json")
							  .header("API-Key", "TEST_P+woI7sMYPYdupkgrjsM/vmAZL6Z4MymboAxwCtVMRA")
							  .header("Host", "api.shipengine.com")
							  .body("{\n  \"shipment\": {\n    \"packages\": [\n      {\n        \"weight\": {\n          \"value\": 9.6,\n          \"unit\": \"ounce\"\n        },\n        \"dimensions\": {\n          \"unit\": \"inch\",\n          \"length\": 12.0,\n          \"width\": 7.1,\n          \"height\": 6.0\n        }\n      }\n    ],\n    \"service_code\": \"usps_priority_mail\",\n    \"ship_to\": {\n      \"name\": \"Amanda Miller\",\n      \"phone\": \"555-555-5555\",\n      \"address_line1\": \"525 S Winchester Blvd\",\n      \"city_locality\": \"San Jose\",\n      \"state_province\": \"\",\n      \"postal_code\": \"95128\",\n      \"country_code\": \"US\",\n      \"address_residential_indicator\": \"yes\"\n    },\n    \"ship_from\": {\n      \"company_name\": \"Example Corp.\",\n      \"name\": \"John Doe\",\n      \"phone\": \"111-111-1111\",\n      \"address_line1\": \"4009 Marathon Blvd\",\n      \"address_line2\": \"Suite 300\",\n      \"city_locality\": \"Austin\",\n      \"state_province\": \"TX\",\n      \"postal_code\": \"78756\",\n      \"country_code\": \"US\",\n      \"address_residential_indicator\": \"no\"\n    }\n  }\n}")
					  		  .asJson();
		  } catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }
		
		JsonNode shipment = response1.getBody();
		JSONArray arr = shipment.getArray();
		JSONObject obj = (JSONObject) arr.get(0);
		System.out.println(obj.keySet());
		JSONObject amount = (JSONObject) obj.get("shipment_cost");
		System.out.println(amount.get("amount"));
		
		
		System.out.println(shipment.toString());
		
		
		String host = "https://api.shipengine.com/v1/rates";
		HttpResponse<JsonNode> response = null;
		  try {
			  response = Unirest.post(host)
					  .header("Content-Type", "application/json")
					  .header("API-Key", "TEST_P+woI7sMYPYdupkgrjsM/vmAZL6Z4MymboAxwCtVMRA")
					  .header("Host", "api.shipengine.com")
					  .body("{\n  \"shipment_id\": \"se-123\",\n  \"rate_options\": {\n    \"carrier_ids\": [\n      \"se-289523\"\n    ]\n  }\n}")
				      .asJson();
		  } catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }
		  
		 
		  JsonNode body = response.getBody();
		  System.out.println(body.toString());
		  //JSONObject json = (JSONObject) body.;
		  
		 
		
		/*
		OkHttpClient client = new OkHttpClient().newBuilder()
		  .build();
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{\n  \"rate_options\": {\n    \"carrier_ids\": [\n      \"se-123890\"\n    ]\n  },\n  \"shipment\": {\n    \"validate_address\": \"no_validation\",\n    \"ship_to\": {\n      \"name\": \"Amanda Miller\",\n      \"phone\": \"555-555-5555\",\n      \"address_line1\": \"525 S Winchester Blvd\",\n      \"city_locality\": \"San Jose\",\n      \"state_province\": \"CA\",\n      \"postal_code\": \"95128\",\n      \"country_code\": \"US\",\n      \"address_residential_indicator\": \"yes\"\n    },\n    \"ship_from\": {\n      \"company_name\": \"Example Corp.\",\n      \"name\": \"John Doe\",\n      \"phone\": \"111-111-1111\",\n      \"address_line1\": \"4009 Marathon Blvd\",\n      \"address_line2\": \"Suite 300\",\n      \"city_locality\": \"Austin\",\n      \"state_province\": \"TX\",\n      \"postal_code\": \"78756\",\n      \"country_code\": \"US\",\n      \"address_residential_indicator\": \"no\"\n    },\n    \"packages\": [\n      {\n        \"weight\": {\n          \"value\": 1.0,\n          \"unit\": \"ounce\"\n        }\n      }\n    ]\n  }\n}");
		Request request = new Request.Builder()
		  .url("https://api.shipengine.com/v1/rates")
		  .method("POST", body)
		  .addHeader("Host", "api.shipengine.com")
		  .add
		  .addHeader("Content-Type", "application/json")
		  .build();
		Response response = client.newCall(request).execute();
		*/
	}
}

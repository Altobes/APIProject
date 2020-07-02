package com.vogella.maven.quickstart;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;




public class GUI {
	private static Variables var = new Variables();
	//title, rating, imageUrl, price, listPrice?
	private static JFrame frame;
	private static JTextField searchField;
	private static JButton searchButton;
	private static JPanel amazonPanel;
	private static JPanel targetPanel;
	private static Displayer dis;
	
	
	
    public static void main(String args[]) throws IOException{
	       frame = new JFrame("Product Searcher");
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       frame.setBounds(500, 100, 1000, 500);
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       frame.getContentPane().setLayout(null);
	       
	       dis = new Displayer();
	       
	       amazonPanel = dis.display(30);
	       amazonPanel.setBounds(30, 200, 100, 100);
	       
	      // frame.setLayout(new BorderLayout());
	       frame.add(amazonPanel);
	      // frame.setRootPane(BorderLayout.CENTER);
	      ///Oth.add(OB,BorderLayout.CENTER);
	      //Oth.setContentPane( BorderLayout.CENTER);
	       
	       
	       frame.add(amazonPanel);
	       amazonPanel.setVisible(true);
	       targetPanel = new JPanel();
		
	       searchField = new JTextField();
	       searchField.setBounds(10, 10, 229, 30);
	       frame.getContentPane().add(searchField);
	       
	       searchButton = new JButton("Search");
	       searchButton.setBounds(230, 10, 100, 29);
	       frame.getContentPane().add(searchButton);
	       searchButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String query = new String(searchField.getText());
					try {
						amazonSearch(query);
						targetSearch(query);
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
			});
	       
	       URL url = null;
	       try {
	    	   url = new URL("https://steamuserimages-a.akamaihd.net/ugc/138879070086400249/5182552889AF62A2AE66B8C79CD41D1FF66B03AD/");
	       } catch (MalformedURLException e) {
	    	   // TODO Auto-generated catch block
	    	   e.printStackTrace();
	       }
	      
	       
	       BufferedImage Image = ImageIO.read(url);
	       JLabel image1 = new JLabel(new ImageIcon(Image));
	       image1.setBounds(30, 40, 250, 250);
	       frame.getContentPane().add(image1);
	       /*
	       title1 = new JLabel("Title");
	       title1.setBounds(30, 290, 250, 30);
	       frame.getContentPane().add(title1);
	       
	       price1 = new JLabel("Price");
	       price1.setBounds(30, 320, 250, 30);
	       frame.getContentPane().add(price1);
	       
	       rating1 = new JLabel("Rating");
	       rating1.setBounds(30, 350, 250, 30);
	       frame.getContentPane().add(rating1);
	       */
	       /*
	       BufferedImage Image1 = ImageIO.read(url);
	       image2 = new JLabel(new ImageIcon(Image1));
	       image2.setBounds(280, 40, 250, 250);
	       frame.getContentPane().add(image2);
	       
	       title2 = new JLabel("Title");
	       title2.setBounds(280, 290, 250, 30);
	       frame.getContentPane().add(title2);
	       
	       price2 = new JLabel("Price");
	       price2.setBounds(280, 320, 250, 30);
	       frame.getContentPane().add(price2);
	       
	       rating2 = new JLabel("Rating");
	       rating2.setBounds(280, 350, 250, 30);
	       frame.getContentPane().add(rating2);
	       */
	       
	       frame.setVisible(true);
    }
    
    public static void amazonSearch(String query) throws JSONException, IOException {
    	  query = query.replace(" ", "+");
    	  String host = String.format("https://amazon-price1.p.rapidapi.com/search?keywords=%s&marketplace=US", query);
    	  String host1 = "https://target-com-store-product-reviews-locations-data.p.rapidapi.com/product/search?sponsored=1&limit=50&offset=0&store_id=2473&keyword=";
	      host1 = host1 + query;
	      
	      HttpResponse<JsonNode> response = null;
	      try {
	    	  response = Unirest.get(host)
				      .header("x-rapidapi-host", var.amazonHost)
				      .header("x-rapidapi-key", var.rapidKey)
				      .asJson();
	      } catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	      }
	      
	     
	      JsonNode body = response.getBody();
	      JSONArray arr = body.getArray();
	      JSONObject json = (JSONObject) arr.get(0);
	      
	      
	      
	     // price1.setText(json.getString("price"));
	      //title1.setText(json.getString("title"));
	      //eating1.setText(json.getString("rating"));
	      URL url = null;
	      url = new URL(json.getString("imageUrl"));
	      BufferedImage image = ImageIO.read(url);
	      //image1.setIcon(new ImageIcon(image));
	      //frame = dis.update(frame, json.getString("title"), json.getString("price"), json.getString("rating"), image);
	      
	      return;
    }
    
    public static void targetSearch(String query) throws JSONException, IOException {
    	query = query.replace(" ", "%20");
  	  	String host = "https://target-com-store-product-reviews-locations-data.p.rapidapi.com/product/search?sponsored=1&limit=50&offset=0&store_id=2473&keyword=";
	    host = host + query;
	      
	      HttpResponse<JsonNode> response = null;
	      try {
	    	  response = Unirest.get(host)
				      .header("x-rapidapi-host", var.targetHost)
				      .header("x-rapidapi-key", var.rapidKey)
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
		    JSONObject json3 = (JSONObject) json2.get("price");
		    int price = json3.getInt("current_retail_min");
		    double rating = json2.getDouble("average_rating");
	        //frame = dis.update(frame, json2.getString("title"), Integer.toString(price), Double.toString(rating));
	      
	      return;
    }
}

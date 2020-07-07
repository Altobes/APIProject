package com.vogella.maven.quickstart;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
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
    		initilize();
	       
	       dis = new Displayer();
	       
	       amazonPanel = dis.display(30);
	       amazonPanel.setBounds(30, 200, 100, 100);
	       frame.getContentPane().add(amazonPanel);
	       
	      // frame.setLayout(new BorderLayout());
	      
	      // frame.setRootPane(BorderLayout.CENTER);
	      ///Oth.add(OB,BorderLayout.CENTER);
	      //Oth.setContentPane( BorderLayout.CENTER);
	       
	       //targetPanel = new JPanel();
		
	      
	       
	       URL url = null;
	       try {
	    	   url = new URL(var.defaultURL);
	       } catch (MalformedURLException e) {
	    	   // TODO Auto-generated catch block
	    	   e.printStackTrace();
	       }
	      
	       
	       BufferedImage Image = ImageIO.read(url);
	       JLabel image1 = new JLabel(new ImageIcon(Image));
	       image1.setBounds(30, 40, 250, 250);
	       //frame.getContentPane().add(image1);
	       
	       frame.setVisible(true);
    }
    
    public static void amazonSearch(String query) throws JSONException, IOException {
    	  query = query.replace(" ", "+");
    	  String host = var.amazonURL1 + query + var.amazonURL2;
	      
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
	      
	      URL url = null;
	      url = new URL(json.getString("imageUrl"));
	      amazonPanel = dis.display(json.getString("title"), json.getString("price"), json.getString("rating"), url, 30);
	      amazonPanel.setBounds(30, 100, 250, 400);
	     // amazonPanel.setSize(amazonPanel.getPreferredSize());
	      Dimension dime = new Dimension(250, 400);
	      amazonPanel.setMaximumSize(dime);
	      frame.getContentPane().add(amazonPanel);
	      frame.repaint();
	      frame.setVisible(true);
	      
	      return;
    }
    
    public static void targetSearch(String query) throws JSONException, IOException {
    	query = query.replace(" ", "%20");
  	  	String host = var.targetURL;
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
    
    public static void initilize() {
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
					frame.getContentPane().removeAll();
					frame.repaint();
					amazonSearch(query);
					//targetSearch(query);
					initilize();
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
    }
}

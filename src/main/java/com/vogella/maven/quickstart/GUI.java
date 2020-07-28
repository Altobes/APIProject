package com.vogella.maven.quickstart;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.JSONException;


public class GUI {
	private static JFrame shipping;
	private static JButton shipButton;
	private static ShipPanel weightPanel;
	private static ShipPanel widthPanel;
	private static ShipPanel lengthPanel;
	private static ShipPanel heightPanel;
	private static ShipPanel costPanel;
	private static JTextField zipcode;

	private static JFrame frame;
	private static JTextField searchField;
	private static JButton searchButton;
	private static Displayer targetPanel;
	private static Displayer amazonPanel;

	
	
	
    public static void main(String args[]) throws IOException{
    	frame = new JFrame("Product Searcher");
    	//frame.getContentPane().setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(500, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		initilize();
	       
	    amazonPanel = new Displayer();
	    amazonPanel.panel.setBounds(30, 200, 100, 100);
	    targetPanel = new Displayer();
	    targetPanel.panel.setBounds(250, 200, 100, 100);
	    
	    frame.getContentPane().add(amazonPanel.panel, BorderLayout.CENTER);
	    frame.getContentPane().add(targetPanel.panel, BorderLayout.CENTER);
		
	      
	       
	    URL url = null;
        try {
    	   url = new URL(Variables.gui.defaultURL);
        } catch (MalformedURLException e) {
    	   // TODO Auto-generated catch block
    	   e.printStackTrace();
        }
	      
	       
	    BufferedImage Image = ImageIO.read(url);
	    JLabel image1 = new JLabel(new ImageIcon(Image));
	    image1.setBounds(30, 40, 250, 250);
	    frame.setVisible(true);
    }
    
    public static void updateAmazon(String query) throws JSONException, IOException {
    	  ArrayList<String> list = Model.amazonSearch(query);
	      URL url = null;
	      url = new URL(list.get(3));
	      Dimension dime = new Dimension(250, 600);
	      amazonPanel.update(list.get(0), list.get(1), list.get(2), url);
	      amazonPanel.panel.setBounds(30, 100, 250, 400);
	      amazonPanel.panel.setMaximumSize(dime);
	      amazonPanel.panel.setSize(dime);
	      
	      return;
    }
    
    public static void updateTarget(String query) throws JSONException, IOException {
    		ArrayList<String> list = Model.targetSearch(query);
    		if (list == null) {
    			targetPanel.panel.setBounds(400, 260, 250, 400);
    			return;
    		}
    		targetPanel.update(list.get(0), list.get(1), list.get(2));
    		targetPanel.panel.setBounds(400, 260, 250, 400);
    		return;
    }
    
    public static void initilize() {
	   searchField = new JTextField();
       searchField.setBounds(10, 10, 229, 30);
       frame.getContentPane().add(searchField, BorderLayout.PAGE_START);
       
       searchButton = new JButton("Search");
       searchButton.setBounds(230, 10, 100, 29);
       frame.getContentPane().add(searchButton, BorderLayout.PAGE_START);
       searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String query = new String(searchField.getText());
				try {
					updateAmazon(query);
					updateTarget(query);
					openShipping();
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
    
    public static void openShipping() {
    	shipping = new JFrame("Shipping Costs");
    	shipping.setBounds(100, 100, 1500, 600);
    	
    	shipButton = new JButton("Calculate Cost");
    	shipButton.setBounds(410, 200, 150, 30);
    	shipping.add(shipButton);
    	zipcode = new JTextField("Adress, city, state, postal");
    	zipcode.setBounds(10, 200, 400, 30);
    	shipping.getContentPane().add(zipcode);
    	
    	shipButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doShipping();
				try {
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
    	
    	weightPanel = new ShipPanel("Weigth");
    	weightPanel.panel.setBounds(0, 0, 200, 400);
    	widthPanel = new ShipPanel("Width");
    	widthPanel.panel.setBounds(300, 0, 200, 400);
    	lengthPanel = new ShipPanel("Length");
    	lengthPanel.panel.setBounds(600, 0, 200, 400);
    	heightPanel = new ShipPanel("Height");
    	heightPanel.panel.setBounds(900, 0, 200, 400);
    	costPanel = new ShipPanel("Cost");
    	costPanel.panel.setBounds(-10, 300, 200, 400);
    	
      	shipping.getContentPane().add(costPanel.panel);
    	shipping.getContentPane().add(weightPanel.panel);
    	shipping.getContentPane().add(widthPanel.panel);
    	shipping.getContentPane().add(heightPanel.panel);
    	shipping.getContentPane().add(lengthPanel.panel);
    	
    	shipping.setVisible(true);
    }
    
    public static void doShipping() {
    	ArrayList<String> list = new ArrayList<String>();
    	String whole = zipcode.getText();
    	String weight = weightPanel.getText();
    	String width = widthPanel.getText();
    	String length = lengthPanel.getText();
    	String height = heightPanel.getText();
    	
    	String[] values = whole.split(",");
    	list.add(values[0].trim());
    	list.add(values[1].trim());
    	list.add(values[2].trim());
    	list.add(values[3].trim());
    	list.add(weight.trim());
    	list.add(width.trim());
    	list.add(length.trim());
    	list.add(height.trim());
    	
    	double cost = Controller.getShipping(list);
    	costPanel.setText(Double.toString(cost));
    }
}

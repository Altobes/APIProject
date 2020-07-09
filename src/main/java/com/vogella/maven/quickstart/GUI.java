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
import javax.swing.JTextField;

import org.json.JSONException;


public class GUI {
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

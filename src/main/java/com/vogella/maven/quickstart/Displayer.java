package com.vogella.maven.quickstart;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Displayer {
	private static JLabel image;
	private static JLabel title;
	private static JLabel rating;
	private static JLabel price;
	
	Displayer() {
		
	}
	
	public JPanel display(int leftx) {
	       
	       JPanel panel = new JPanel(new GridLayout(4,1));
			image = new JLabel();
	        image.setBounds(leftx, 40, 250, 250);
	       
	        title = new JLabel("Title");
	        title.setBounds(leftx, 290, 250, 30);
	       
	        price = new JLabel("Price");
	        price.setBounds(leftx, 320, 250, 30);
	       
	        rating = new JLabel("Rating");
	        rating.setBounds(leftx, 350, 250, 30);
	        panel.add(image);
	        panel.add(title);
	        panel.add(price);
	        panel.add(rating);
	        return panel;
	}
	
	public JPanel display(String Title, String Price, String Rating, URL url, int leftx) throws IOException {
	       JPanel panel = new JPanel();
	       panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
	       Dimension dime = new Dimension(250, 30);
	       
	        BufferedImage image1 = ImageIO.read(url);
			image = new JLabel();
			image.setIcon(new ImageIcon(image1));
	        //image.setBounds(leftx, 40, 250, 250);
	        
	       
	        title = new JLabel(Title);
	        title.setBounds(leftx, 290, 250, 30);
	        title.setMaximumSize(dime);
	        title.setSize(dime);
	       
	        price = new JLabel(Price);
	        price.setBounds(leftx, 320, 250, 30);
	        price.setMaximumSize(dime);
	       
	        rating = new JLabel(Rating);
	        rating.setBounds(leftx, 350, 250, 30);
	        rating.setMaximumSize(dime);
	        
	        panel.add(image);
	        panel.add(title);
	        panel.add(price);
	        panel.add(rating);
	        return panel;
	}
	
	
	

}



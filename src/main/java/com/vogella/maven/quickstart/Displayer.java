package com.vogella.maven.quickstart;

import java.awt.GridLayout;
import java.awt.List;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
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
	
	

}



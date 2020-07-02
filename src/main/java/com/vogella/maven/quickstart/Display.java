package com.vogella.maven.quickstart;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class Display extends JComponent {
	private static JLabel image;
	private static JLabel title;
	private static JLabel rating;
	private static JLabel price;
	  
	public Display() { 
	}
	  
	public Display(int leftx) {
		image = new JLabel();
	    image.setBounds(leftx, 40, 250, 250);
	       
	    title = new JLabel("Title");
	    title.setBounds(leftx, 290, 250, 30);
	       
	    price = new JLabel("Price");
	    price.setBounds(leftx, 320, 250, 30);
	       
	    rating = new JLabel("Rating");
	    rating.setBounds(leftx, 350, 250, 30);
	}
}
	  
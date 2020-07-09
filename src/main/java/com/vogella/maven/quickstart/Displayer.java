package com.vogella.maven.quickstart;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Displayer {
	public JPanel panel;
	private static JLabel image;
	private static JLabel title;
	private static JLabel rating;
	private static JLabel price;
	
	Displayer() {
		//panel = new JPanel(new GridLayout(4,1));
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		image = new JLabel();
        title = new JLabel("Title");
        price = new JLabel("Price");
        rating = new JLabel("Rating");
        
        panel.add(image);
        panel.add(title);
        panel.add(price);
        panel.add(rating);
	}
	
	
	public void update(String Title, String Price, String Rating, URL url) throws IOException {
		Component[] comps = panel.getComponents();
		image = (JLabel) comps[0];
		title = (JLabel) comps[1];
		price = (JLabel) comps[2];
		rating = (JLabel) comps[3];
      
        BufferedImage Image = ImageIO.read(url);
        image.setIcon(new ImageIcon(Image));
		title.setText(Title);
		price.setText(Price);
		rating.setText(Rating);
	}
	
	public void update(String Title, String Price, String Rating) {
		Component[] comps = panel.getComponents();
		title = (JLabel) comps[1];
		price = (JLabel) comps[2];
		rating = (JLabel) comps[3];
		
		title.setText(Title);
		price.setText(Price);
		rating.setText(Rating);
	}
	
	
	

}



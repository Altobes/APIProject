package com.vogella.maven.quickstart;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ShipPanel {
	public JPanel panel;
	private static JLabel label;
	private static JTextField text;
	
	ShipPanel(String string) {
		panel = new JPanel();
		//panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		//panel.setLayout(null);
	    label = new JLabel(string);
	    //label.setBounds(0, 0, 100, 30);
	    text = new JTextField();
	    //text.setBounds(100, 0, 100, 30);
	    
	    Dimension dime = new Dimension(100, 30);
	    
	    text.setPreferredSize(dime);
	    
	    panel.add(label);
	    panel.add(text);
	}
	
	public String getText() {
		return text.getText();
	}
	
	public void setText(String str) {
		text.setText(str);
	}
}

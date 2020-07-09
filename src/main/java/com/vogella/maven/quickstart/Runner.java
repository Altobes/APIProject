package com.vogella.maven.quickstart;

import java.awt.Component;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

//import APIWork.JsonNode;

public class Runner {
	public static void main(String[] args) throws MalformedURLException, IOException {
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 1000, 500);
		JPanel panel = new JPanel();
		
		JButton button = new JButton("Press");
		button.setBounds(100, 100, 30, 50);
		panel.add(button);
		
		frame.getContentPane().add(panel);
		frame.setVisible(true);
		
		Component[] comp = panel.getComponents();
		
		JButton button2 = (JButton) comp[0];
		button2.setText("Push");
	}
}

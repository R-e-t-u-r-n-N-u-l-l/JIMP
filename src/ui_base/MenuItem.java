package ui_base;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import utilities.Constants;

public class MenuItem extends JMenuItem {
	private static final long serialVersionUID = 1L;

	public MenuItem(String title, ActionListener al) {
		this.addActionListener(al);
		this.setText(title);
		this.setBackground(Constants.DARK_GRAY);
		this.setForeground(Color.WHITE);
		this.setBorder(null);
	}
}

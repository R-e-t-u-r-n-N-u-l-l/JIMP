package ui_base;

import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ToolButton extends JButton {
	private static final long serialVersionUID = 1L;

	public ToolButton(int x, int y, int width, int height, String text, ActionListener al) {
		setText(text);
		setSize(width, height);
		setLocation(x, y);
		addActionListener(al);
	}
}

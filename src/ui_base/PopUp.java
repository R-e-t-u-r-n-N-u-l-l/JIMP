package ui_base;

import javax.swing.JFrame;

public class PopUp {
	protected JFrame frame;
	
	public PopUp(String title, int width, int height) {
		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(null);;
		frame.setSize(width, height);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}

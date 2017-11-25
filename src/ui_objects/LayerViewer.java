package ui_objects;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import canvas.Layer;
import main.Main;
import utilities.Constants;

public class LayerViewer extends JPanel {
	private static final long serialVersionUID = 1L;
	private int layerAmt;
	private JScrollPane pane;
	
	public LayerViewer(Frame frame, Main main) {
		setLocation(frame.getWidth() - frame.getWidth() / 10, frame.getMenu().getHeight());
		setSize(frame.getWidth() / 10, frame.getHeight() - frame.getMenu().getHeight() - frame.getToolBar().getHeight());
		setLayout(null);
		
		pane = new JScrollPane();
		pane.setLocation(0, 0);
		pane.setSize(getSize());
		pane.setBorder(null);
		
		add(pane);
	}
	
	public void addImage(Layer layer) {
		JPanel panel = new JPanel();
		panel.setSize(pane.getWidth(), pane.getWidth());
		panel.setLocation(0, pane.getWidth() * layerAmt);
		panel.setBackground(Constants.DARK_GRAY);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel.setLayout(null);
		
		layerAmt++;
		add(panel);
	}
}

package ui_objects;

import java.awt.Canvas;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import main.Main;
import utilities.Constants;

public class Frame extends JFrame {
	private static final long serialVersionUID = 1L;
	private Canvas canvas = new Canvas();
	private JPanel panel = new JPanel();
	private int toolBarSize = 80;
	private Menu menu;
	private ToolBar toolBar;
	private LayerViewer layerViewer;
	private ColorPicker colorPicker;

	public Frame(String title, Main main) {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Constants.GRAY);
		setTitle(title);
		setVisible(true);
		setLayout(null);
		
		menu = new Menu(this, main);
		toolBar = new ToolBar(this, toolBarSize);
		layerViewer = new LayerViewer(this, main);
		colorPicker = new ColorPicker();
		
		int size = getWidth() > getHeight() ? (int) (getHeight() * 0.8f) : (int) (getWidth() * 0.8f);
		panel.setLayout(null);
		panel.setSize(size, size);
		panel.setLocation(getWidth() / 2 - size / 2, getHeight() / 2 - size / 2 - toolBarSize / 3);
		
		canvas.setSize(size, size);
		canvas.setFocusable(true);
		canvas.requestFocus();
		
		JSeparator line = new JSeparator();
		line.setSize(getWidth(), 1);
		line.setForeground(Color.BLACK);
		line.setLocation(0, getHeight() - toolBarSize - 1);
		
		setVisible(false);
		
		panel.add(canvas);
		add(panel);
		add(menu);
		add(toolBar);
		add(line);
		add(colorPicker);
		//add(layerViewer);
		
		setVisible(true);
	}
	
	public Menu getMenu() {
		return menu;
	}
	
	public ToolBar getToolBar() {
		return toolBar;
	}
	
	public ColorPicker getColorPicker() {
		return colorPicker;
	}
	
	public Canvas getCanvas() {
		 return canvas;
	}
	
	public LayerViewer getLayerViewer() {
		return layerViewer;
	}
}
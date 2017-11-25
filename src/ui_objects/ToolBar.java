package ui_objects;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ui_base.ToolButton;
import utilities.Constants;
import utilities.Variables;

public class ToolBar extends JPanel {
	private static final long serialVersionUID = 1L;

	public ToolBar(Frame frame, int size) {
		setLayout(null);
		setLocation(0, frame.getHeight() - size);
		setSize(frame.getWidth(), size);
		setBackground(Constants.DARK_GRAY);
		
		JSlider brushSlider = new JSlider(1, 1000, Variables.brushSize);
		JTextField brushField = new JTextField();
		JLabel brushLabel = new JLabel("Brush Size:");
		brushLabel.setSize(80, 20);
		brushLabel.setLocation(frame.getWidth() - 143, 1);
		brushLabel.setForeground(Color.LIGHT_GRAY);
		
		brushSlider.setSize(80, 20);
		brushSlider.setLocation(frame.getWidth() - 150, 20);
		brushSlider.setBackground(Constants.DARK_GRAY);
		
		brushSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent c) {
				if(!((JSlider) (c.getSource())).getValueIsAdjusting())
					Variables.brushSize = brushSlider.getValue();
				
				brushField.setText(Integer.toString(Variables.brushSize));
		}});
		
		brushField.setSize(35, 20);
		brushField.setLocation(frame.getWidth() - 60, 10);
		brushField.setForeground(Color.BLACK);
		brushField.setBorder(null);
		brushField.setText(Integer.toString(Variables.brushSize));
		
		brushField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				int value = Variables.brushSize;
				
				try {
					value = Integer.parseInt(brushField.getText());
				} catch(NumberFormatException e) {
					e.printStackTrace();
				}
				
				Variables.brushSize = value;
				brushSlider.setValue(value);
			}});
		
		add(new ToolButton(10, 10, 70, 20, "Pencil", new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				Variables.currentTool = Variables.tools.PENCIL;
		}}));
		
		add(new ToolButton(90, 10, 80, 20, "Eraser", new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				Variables.currentTool = Variables.tools.ERASER;
		}}));
		
		add(new ToolButton(180, 10, 90, 20, "Pipette", new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				Variables.currentTool = Variables.tools.PIPETTE;
		}}));
		
		add(brushSlider);
		add(brushField);
		add(brushLabel);
	}
}

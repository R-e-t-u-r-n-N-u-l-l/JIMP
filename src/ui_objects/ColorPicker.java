package ui_objects;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import utilities.Constants;
import utilities.Variables;

public class ColorPicker extends JPanel {
	private static final long serialVersionUID = 1L;
	ArrayList<JLabel> labels = new ArrayList<>();
	ArrayList<JSlider> sliders = new ArrayList<>();
	ArrayList<JTextField> fields = new ArrayList<>();
	JPanel colorPane = new JPanel();

	public ColorPicker() {
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		setBackground(Constants.LIGHT_GRAY);
		setLayout(null);
		setLocation(0, 25);
		setSize(190, 200);
		
		colorPane.setLayout(null);
		colorPane.setSize(40, 40);
		colorPane.setLocation(130, 145);
		colorPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		colorPane.setBackground(Color.BLACK);
		
		JButton ok = new JButton("Ok");
		ok.setSize(50, 20);
		ok.setLocation(10, 140);
		
		for(int i = 0; i < 4; i++) {
			JTextField field = new JTextField();
			field.setSize(35, 20);
			field.setLocation(145, 10 + 30 * i);
			field.setForeground(Color.BLACK);
			field.setBorder(null);
			field.setText("0");
			fields.add(field);
			
			JSlider slider = new JSlider(0, 255, 0);
			slider.setSize(80, 20);
			slider.setLocation(55, 10 + 30 * i);
			slider.setBackground(Constants.LIGHT_GRAY);
			slider.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent c) {
					field.setText(Integer.toString(slider.getValue()));
					if(!((JSlider) (c.getSource())).getValueIsAdjusting())
						ok.doClick();
			}});
			sliders.add(slider);
			
			JLabel label = new JLabel();
			label.setSize(40, 20);
			label.setLocation(10, 10 + 30 * i);
			label.setForeground(Color.BLACK);
			
			switch(i) {
				case 0:
					label.setText("Red:");
					break;
				case 1:
					label.setText("Green:");
					break;
				case 2:
					label.setText("Blue:");
					break;
				case 3:
					field.setText("255");
					slider.setValue(255);
					label.setText("Alpha:");
					break;
			}
			
			labels.add(label);
		}
		
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				int num;
				for(int i = 0; i < 4; i++) {
					try {
						num = Integer.parseInt(fields.get(i).getText());
						fields.get(i).setText(num < 0 ? "0" : num > 255 ? "255" : Integer.toString(num));
					} catch(NumberFormatException e) {
						fields.get(i).setText("0");
					} 
					
					Variables.color[i] = Integer.parseInt(fields.get(i).getText());
					}
				
				correctFields();
			}});
		
		JButton advanced = new JButton("Advanced");
		advanced.setSize(90, 20);
		advanced.setLocation(10, 170);
		advanced.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				Color currentColor = new Color(Variables.color[0], Variables.color[1], Variables.color[2], Variables.color[3]);
				Color color = JColorChooser.showDialog(null, "Color Picker", currentColor);
				if(color == null)
					return;
				Variables.color[0] = color.getRed();
				Variables.color[1] = color.getGreen();
				Variables.color[2] = color.getBlue();
				Variables.color[3] = color.getAlpha();
				
				correctFields();
			}});
		
		for(JLabel l : labels)
			add(l);
		for(JSlider s : sliders)
			add(s);
		for(JTextField f : fields)
			add(f);
		add(ok);
		add(advanced);
		add(colorPane);
	}
	
	public void correctFields() {
		for(int i = 0; i < 4; i++)
			fields.get(i).setText(Integer.toString(Variables.color[i]));
		
		for(int i = 0; i < 4; i++)
			sliders.get(i).setValue(Variables.color[i]);
		
		colorPane.setBackground(new Color(Variables.color[0], Variables.color[1], Variables.color[2]));
	}
}

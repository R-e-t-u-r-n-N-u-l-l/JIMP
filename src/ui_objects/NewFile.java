package ui_objects;

import javax.swing.JButton;

import ui_base.PopUp;

public class NewFile extends PopUp {

	public NewFile() {
		super("New File", 450, 300);
		
		frame.add(new JButton("Ok"));
		frame.add(new JButton("Cancel"));
	}
}

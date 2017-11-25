package ui_objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import canvas.Layer;
import main.Main;
import ui_base.MenuItem;
import utilities.Constants;

public class Menu extends JMenuBar {
	private static final long serialVersionUID = 1L;
	private Main main;
	
	public Menu(Frame frame, Main main) {
		this.main = main;
		setSize(frame.getWidth(), 25);
		setBackground(Constants.DARK_GRAY);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		JMenu file = new JMenu(" File ");
		file.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		file.setBackground(Constants.DARK_GRAY);
		file.setForeground(Color.WHITE);
		file.setSize(35, 25);
		
		file.add(new MenuItem(" New ", new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				main.clearLayers();
		}}));
		
		file.add(new MenuItem(" Open ", new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				openFile();
		}}));
		
		file.add(new MenuItem(" Save ", new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				saveFile();
		}}));
		
		file.add(new MenuItem(" Save as ", new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				saveFile();
		}}));
		
		file.add(new MenuItem(" Exit ", new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				System.exit(0);
		}}));
		
		this.add(file);
	}
	
	private void openFile() {
		JFileChooser expl = new JFileChooser();
		if(expl.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File file = expl.getSelectedFile();
			BufferedImage img = null;
			try {
				img = new BufferedImage(main.getFrame().getCanvas().getWidth(), main.getFrame().getCanvas().getHeight(), BufferedImage.TYPE_INT_ARGB);
			    Image tempImage = ImageIO.read(file);
			    Graphics g = img.getGraphics();
				for(Layer l : main.getLayers())
					g.drawImage(l.getLayer(), 0, 0, null);
			    
			    g.drawImage(tempImage, 0, 0, null);
			    g.dispose();
			    main.clearLayers();
			    main.addLayer(new Layer(main.getFrame().getCanvas().getWidth(), main.getFrame().getCanvas().getHeight(), img));
			} catch (Exception e) {
				java.awt.Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, "Can't load file: " + file.getPath(), "Error loading file", 1);
			}
		}
	}
	
	private void saveFile() {
		JFileChooser expl = new JFileChooser();
		expl.showSaveDialog(null);
		try {
			if(expl.getSelectedFile() == null)
				throw new IOException();
			if(expl.getSelectedFile().exists()) {
				java.awt.Toolkit.getDefaultToolkit().beep();
				if(JOptionPane.showConfirmDialog(null, "Do you want to override " + expl.getSelectedFile().getName() + '?', "File already exists", 1) != JOptionPane.YES_OPTION) {
					return;
				}
			}
			
			BufferedImage finalImage = new BufferedImage(main.getFrame().getCanvas().getWidth(), main.getFrame().getCanvas().getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics g = finalImage.getGraphics();
			for(Layer l : main.getLayers())
				g.drawImage(l.getLayer(), 0, 0, null);
			
			ImageIO.write(finalImage, "PNG", expl.getSelectedFile());
		} catch (IOException b) {
			java.awt.Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, "Can't save file: " + expl.getSelectedFile(), "Error", 1);
		}
	}
}

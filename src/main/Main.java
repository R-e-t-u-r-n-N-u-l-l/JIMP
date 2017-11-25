package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.concurrent.CopyOnWriteArrayList;

import canvas.Background;
import canvas.Layer;
import mouse.Draw;
import mouse.MouseInput;
import ui_objects.Frame;

public class Main {
	private Frame frame;
	private Graphics g;
	private Background background;
	private Draw draw;
	private MouseInput mouseInput;
	private CopyOnWriteArrayList<Layer> layers = new CopyOnWriteArrayList<>();
	
	public Main() {
		frame = new Frame("JIMP - Java Image Manipulation Program", this);
		
		draw = new Draw(this);
		mouseInput = new MouseInput(draw);
		addLayer(new Layer(frame.getCanvas().getWidth(), frame.getCanvas().getHeight(), new BufferedImage(frame.getCanvas().getWidth(), frame.getCanvas().getHeight(), BufferedImage.TYPE_INT_ARGB)));
		
		frame.getCanvas().createBufferStrategy(2);
		frame.getCanvas().addMouseListener(mouseInput);
		frame.getCanvas().addMouseMotionListener(mouseInput);

		background = new Background();
		
		while(true)
			run();
	}
	
	private void run() {
		int width = frame.getCanvas().getWidth();
		int height = frame.getCanvas().getHeight();
		
		g = frame.getCanvas().getBufferStrategy().getDrawGraphics();
		
		g.clearRect(0, 0, width, height);
		g.drawRect(0, 0, width - 1, height - 1);
		
		background.render(g, width, height);
		draw.update(layers.get(layers.size() - 1));

		for(Layer l : layers)
			l.render(g);
		
		frame.getCanvas().getBufferStrategy().show();
		g.dispose();
	}
	
	public Frame getFrame() {
		return frame;
	}
	
	public void addLayer(Layer layer) {
		layers.add(layer);
		frame.getLayerViewer().addImage(layer);
	}
	
	public void clearLayers() {
		layers.clear();
		layers.add(new Layer(frame.getCanvas().getWidth(), frame.getCanvas().getHeight(), new BufferedImage(frame.getCanvas().getWidth(), frame.getCanvas().getHeight(), BufferedImage.TYPE_INT_ARGB)));
	}
	
	public CopyOnWriteArrayList<Layer> getLayers() {
		return layers;
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
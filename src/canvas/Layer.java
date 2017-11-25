package canvas;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Layer {
	private BufferedImage layer;
	private int width, height;
	
	public Layer(int width, int height, BufferedImage image) {
		this.width = width;
		this.height = height;
		layer = image;
	}
	
	public void render(Graphics g) {
		g.drawImage(layer, 0, 0, null);
	}
	
	public BufferedImage getLayer() {
		return layer;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}

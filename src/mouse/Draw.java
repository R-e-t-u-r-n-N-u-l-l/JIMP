package mouse;

import java.awt.event.MouseEvent;
import java.awt.image.WritableRaster;

import canvas.Layer;
import main.Main;
import utilities.Constants;
import utilities.Variables;

public class Draw extends MouseState {
	private Layer currentLayer;
	private Main main;
	private boolean drawing;
	private int prevX, prevY;
	
	public Draw(Main main) {
		this.main = main;
	}
	
	public void update(Layer layer) {
		this.currentLayer = layer;
	}
	
	private void draw(int x, int y) {
		int width 		= currentLayer.getWidth();
		int height 		= currentLayer.getHeight();
		
		int imageWidth 	= currentLayer.getLayer().getWidth();
		int imageHeight = currentLayer.getLayer().getHeight();
		
		int w 			= width / imageWidth;
		int h 			= height / imageHeight;
		
		WritableRaster raster = currentLayer.getLayer().getRaster();
		
		try {
			for(int i = 0; i < imageWidth - 1; i++) {
				for(int j = 0; j < imageHeight - 1; j++) {
					if(x >= i * w && x < (i + 1) * w && y >= j * h && y < (j + 1) * h) {
						if(prevX == 0 && prevY == 0) {
						       prevX = i;
						       prevY = j;
						}
						
						float dx = prevX - i;
						float dy = prevY - j;
						float dist = (int) Math.sqrt(Math.pow(Math.abs(dx), 2) + Math.pow(Math.abs(dy), 2));
						if(dist >= Variables.interval * Variables.brushSize) {
							for(int m = (int) Math.ceil((float) dist / (float) (Variables.interval * Variables.brushSize)) - 1; m >= 0; m--) {
								for(int k = 0; k < Variables.brushSize; k++) {
									if((int) (i + k) + (int) (dx / dist * Variables.interval * Variables.brushSize * m) >= width || (int) (i + k) + (int) (dx / dist * Variables.interval * Variables.brushSize * m) < 0)
										continue;
									for(int l = 0; l < Variables.brushSize; l++) {
										if((int) (j + l) + (int) (dy / dist * Variables.interval * Variables.brushSize * m) >= height || (int) (j + l) + (int) (dy / dist * Variables.interval * Variables.brushSize * m) < 0)
											continue;
										switch(Variables.currentTool) {
										case ERASER:
											raster.setPixel((int) (i + k) + (int) (dx / dist * Variables.interval * Variables.brushSize * m), (int) (j + l) + (int) (dy / dist * Variables.interval * Variables.brushSize * m), Constants.emptyColor);
											break;
										case PIPETTE:
											for(int n = 0; n < 4; n++)
												Variables.color = raster.getPixel(i, j, (int[]) null);
											main.getFrame().getColorPicker().correctFields();
											break;
										case FILL_BUCKET:
											break;
										default:
											raster.setPixel((int) (i + k) + (int) (dx / dist * Variables.interval * Variables.brushSize * m), (int) (j + l) + (int) (dy / dist * Variables.interval * Variables.brushSize * m), Variables.color);
											break;
										}
									}
								}
							}
							prevX = i;
							prevY = j;
						}
					}
				}
			}
		} catch(ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			prevX = prevY = 0;
		}
	}
	
	private void dot(int x, int y) {
		int width 		= currentLayer.getWidth();
		int height		= currentLayer.getHeight();
		
		int imageWidth 	= currentLayer.getLayer().getWidth();
		int imageHeight = currentLayer.getLayer().getHeight();
		
		int w 			= width / imageWidth;
		int h 			= height / imageHeight;
		
		WritableRaster raster = currentLayer.getLayer().getRaster();
	
		try { 
			for(int i = 0; i < imageWidth - 1; i++) {
				for(int j = 0; j < imageHeight - 1; j++) {
					if(x >= i * w && x < (i + 1) * w && y >= j * h && y < (j + 1) * h) {
						for(int k = 0; k < Variables.brushSize; k++) {
							if(i + k >= width || i + k < 0)
								continue;
							for(int l = 0; l < Variables.brushSize; l++) {
								if(j + l >= height || j + l < 0)
									continue;
								switch(Variables.currentTool) {
									case ERASER:
										raster.setPixel(i + k, j + l, Constants.emptyColor);
										break;
									case PIPETTE:
										for(int m = 0; m < 4; m++)
											Variables.color = raster.getPixel(i, j, (int[]) null);
										main.getFrame().getColorPicker().correctFields();
										break;
									case FILL_BUCKET:
										//int[] color = raster.getPixel(i, j, (int[]) null);
										
										break;
									default:
										raster.setPixel(i + k, j + l, Variables.color);
										break;
								}
							}
						}
					}
				}
			}
		} catch(ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			prevX = prevY = 0;
		}
	}
	
	public void mouseMoved(MouseEvent e) {
		if(currentLayer != null && drawing)
			draw(e.getX(), e.getY());
	}
	
	public void mousePressed(MouseEvent e) {
		drawing = true;
		
		if(currentLayer != null)
			dot(e.getX(), e.getY());
	}

	public void mouseReleased(MouseEvent e) {
		drawing = false;
		prevX = prevY = 0;
	}

	public Layer getCurrentLayer() {
		return currentLayer;
	}

	public void setCurrentLayer(Layer currentLayer) {
		this.currentLayer = currentLayer;
	}
}

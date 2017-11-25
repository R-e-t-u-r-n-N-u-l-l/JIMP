package canvas;

import java.awt.Color;
import java.awt.Graphics;

public class Background {
	
	public void render(Graphics g, float width, float height) {
		int size = (int) (width / 100);
		
		for(int i = 50; i < 250; i++) {
			if((width / i) == (int) (width / i)) {
				size = (int) (width / i);
				break;
			}
		}
		
		for(int i = 0; i < width / size + 1; i++) {
			for(int j = 0; j < height / size + 1; j++) {
				int color = (i % 2 == 0 && j % 2 == 0) ? 127 : (i % 2 != 0 && j % 2 != 0) ? 127 : 255;
				g.setColor(new Color(color, color, color));
				g.fillRect(i * size, j * size, size, size);
			}
		}
	}
}

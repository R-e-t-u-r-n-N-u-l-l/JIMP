package utilities;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class Functions {

	public static BufferedImage colorToGray(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		WritableRaster grayRaster = grayImage.getRaster();
		WritableRaster raster = image.getRaster();
		
		for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int[] pixels = raster.getPixel(i, j, (int[]) null);
                for(int k = 0; k < 3; k++)
                	pixels[k] = (int) (pixels[0] * Constants.grayScales[0] + pixels[1] * Constants.grayScales[1] + pixels[2] * Constants.grayScales[2]);
                grayRaster.setPixel(i, j, pixels);
            }
        }
        return grayImage;
	}
}

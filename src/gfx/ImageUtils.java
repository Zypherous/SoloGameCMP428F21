package gfx;

import java.io.IOException;
import java.awt.*;
import javax.imageio.ImageIO;

public class ImageUtils {

	public static Image loadImage(String filePath) {
		try {
			return ImageIO.read(ImageUtils.class.getResource(filePath));
		}
		catch (IOException e){
			System.out.println("Could not load image from path: " + filePath);
		}
		return null;
	}
	
}

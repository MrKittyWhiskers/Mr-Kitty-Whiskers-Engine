package org.nc.engine;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {

	public static BufferedImage getSprite(String path, int num, int width, int height) {
		num++;
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(path));
		} catch (IOException e) {
		}
		int row = num / 10 % 10;
		int col = num * width - width - (row * height);
		
		return img.getSubimage(col, row, width, height);
	}

}

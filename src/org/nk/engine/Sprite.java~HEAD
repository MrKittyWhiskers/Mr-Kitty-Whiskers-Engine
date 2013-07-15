package org.nk.engine;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
	
	static BufferedImage img;
	
	public Sprite(String path) {
		try {
			img = ImageIO.read(new File(path));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static BufferedImage getSprite(int num, int width, int height) {
		num++;
		int row = num / 10 % 10;
		int col = num * width - width - (row * height);
		try {
			return img.getSubimage(col, row, width, height);
		}catch(NullPointerException npe){
			return null;
		}
	}

}

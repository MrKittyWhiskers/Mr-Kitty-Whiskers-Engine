package org.mkw.engine;

import java.awt.Image;

public class EngineImage {

	public static Image resizeImage(Image image, int width, int height) {
		return image.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);
	}
}

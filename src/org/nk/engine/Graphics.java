package org.nk.engine;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Graphics {

	java.awt.Graphics g;
	GameContainer gc;

	public Graphics(java.awt.Graphics g, GameContainer gc) {
		this.g = g;
		this.gc = gc;
	}

	public void setColor(Color c) {
		g.setColor(c);
	}

	public void fillRect(float x, float y, int width, int height) {
		g.fillRect((int) x, (int) y, width, height);
	}

	public void drawString(String text, int x, int y, Font font) {
		g.setFont(font);
		g.drawString(text, x, y);
	}

	public void drawImage(String loc, int x, int y) {
		try {
			g.drawImage(ImageIO.read(new File(loc)), x, y, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void drawImage(BufferedImage image, int x, int y) {
		g.drawImage(image, x, y, null);
	}

	public void drawImage(Image image, float x, float y) {
		g.drawImage(image, (int) x, (int) y, null);
	}

	public void drawShape(Shape shape) {
		Graphics2D g2 = (Graphics2D) g;
		g2.draw(shape);
	}

	public void setFont(Font font) {
		g.setFont(font);
	}

	public Font currentFont() {
		Font font = g.getFont();
		return font;
	}

	public void drawSubImage(BufferedImage subimage, int x, int y, int size, int size2) {
		g.drawImage(subimage, x, y, size, size2, null);
	}
	
	public void drawResizeImage(BufferedImage image, int x, int y, int width, int height) {
		((Graphics2D) g).drawImage(image, x, y, width, height, null);
	}

	public java.awt.Graphics getGraphics() {
		return g;
	}
}
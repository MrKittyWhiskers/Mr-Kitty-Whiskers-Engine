package org.nc.engine;

import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

import org.nc.engine.Graphics;

public class Button {

	int x;
	int y;
	int width;
	int height;
	Game game;
	Input input;
	String text;
	AffineTransform affinetransform = new AffineTransform();
	FontRenderContext frc = new FontRenderContext(affinetransform, true, true);

	public Button(int x, int y, Game game, Input input, String text) {
		this.x = x;
		this.y = y;
		this.width = 250;
		this.height = 70;
		this.game = game;
		this.input = input;
		this.text = text;
	}

	public void render(Graphics g) {
		int textWidth = (int) (g.currentFont().getStringBounds(text, frc).getWidth());
		int textHeight = (int) (g.currentFont().getStringBounds(text, frc).getHeight());
		g.drawImage(game.getButtonLoc(), x, y);
		g.drawString(text, x + width / 2 - textWidth / 2, y + (height / 2 + textHeight / 4), g.currentFont());
		// draw text and crap
	}

	public boolean isClicked() {
		if (game.mouseX() > x && game.mouseX() < x + width && game.mouseY() > y && game.mouseY() < y + height && input.isMouseDown) {
			return true;
		} else {
			return false;
		}
	}

}

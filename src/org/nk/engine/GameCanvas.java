package org.nk.engine;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComponent;

public class GameCanvas extends JComponent {

	private static final long serialVersionUID = 493876654584606450L;
	Game game;
	private GameContainer gc;
	Graphics g;
	org.nk.engine.Graphics myGraphics;

	public GameCanvas(Game game, Input input, GameContainer gc) {
		this.game = game;
		this.gc = gc;
		addKeyListener(input);
		addMouseListener(input);
		addMouseMotionListener(input);
		setBackground(Color.BLACK);
		
		setFocusable(true);
		requestFocus();
	}
	
	public void paintComponent(Graphics g) {
		if (g != null) {
			this.g = g;
			myGraphics = new org.nk.engine.Graphics(g, gc);
		}
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.white);
		game.render(myGraphics);
	}

	public void dispose() {
		try {
			g.dispose();
		} catch(NullPointerException e) {
		}
	}
}

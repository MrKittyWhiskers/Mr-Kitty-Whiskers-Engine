package org.nc.engine;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JComponent;

public class GameCanvas extends JComponent {

	private static final long serialVersionUID = 493876654584606450L;
	Game game;
	private GameContainer gc;

	public GameCanvas(Game game, Input input, GameContainer gc) {
		this.game = game;
		this.gc = gc;
		addKeyListener(input);
		addMouseListener(input);
		addMouseMotionListener(input);
		setFocusable(true);
		requestFocus();
	}

	public void paintComponent(Graphics g) {
		Font f = g.getFont();
		g.setFont(f);
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.white);
		game.render(new org.nc.engine.Graphics(g));
		if (gc.isDebugVis) {
			f = g.getFont();
			Font debugfont = new Font("Calibri", Font.PLAIN, 15);
			g.setFont(debugfont);
			g.drawString(game.debuginfo, 0, 10);
		}
	}
}

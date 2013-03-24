package org.nc.engine;

import javax.swing.JFrame;

public class GameContainer {

	private JFrame frame;
	static String name;
	private Game game;
	public Game g;
	GameCanvas canvas;
	public boolean isDebugVis;
	Input input;

	public void enterState(Game game, Input input) {
		this.game = game;
		this.input = input; 
		input.game = game;
		if (name == null) {
			name = "Game";
		}
		if (g == null) {
			frame = new JFrame(name);
			frame.setSize(game.getWidth(), game.getHeight());
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setAutoRequestFocus(true);
			frame.setResizable(false);
			frame.setVisible(true);
			canvas = new GameCanvas(game, input, this);
			frame.add(canvas);
			frame.addMouseListener(input);
			frame.addKeyListener(input);
		} else {
			g.over = true;
			canvas.game = game;
		}
		game.width = frame.getWidth();
		game.height = frame.getHeight();
		g = game;
		GameLoop loop = new GameLoop(game, canvas);
		loop.start();
	}

	public void setTitle(String s) {
		frame.setTitle(s);
	}
	
	public void setSize(int width, int height) {
		frame.setSize(width, height);
		game.width = width;
		game.height = height;
	}

}

package org.nk.engine;

import java.awt.Cursor;
import java.util.HashMap;
import javax.swing.JFrame;

public class GameContainer {

	public JFrame frame;
	private static String name;
	public Game currentState;
	private Game nextState;
	private int width = 0;
	private int height = 0;
	GameLoop loop;
	public boolean isDebugVis;
	Input input;
	HashMap<Integer, Game> states = new HashMap<Integer, Game>();

	public void initInput(Input input) {
		this.input = input;
	}

	/**
	 * 
	 * @param state
	 */
	public void addState(Game state) {
		states.put(new Integer(state.getID()), state);
		state.init();
	}

	/**
	 * Changes the state. The state will then update and render.
	 * 
	 * @param id
	 *            (Needs to be the same as the number that is returned from getID() in the Game class)
	 */

	GameCanvas canvas;
	public void enterState(int id) {
		nextState = getState(id);
		if (nextState == null) {
			throw new RuntimeException("No game state registered with the ID: " + id);
		} else {
			input.game = nextState;
			if (currentState == null) {
				frame = new JFrame(getName());
				frame.setSize(width, height);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setResizable(false);
				frame.setVisible(true);
				frame.addMouseListener(input);
				frame.addKeyListener(input);
				canvas = new GameCanvas(nextState, input, this);
				frame.add(canvas);
				loop = new GameLoop(this, nextState, canvas);
				Thread l = new Thread(loop);
				l.start();
			} else {
				canvas.game = nextState;
				currentState.over = true;
				currentState.end();
			}
			loop.game = nextState;
			currentState = nextState;
			currentState.over = false;
			width = frame.getWidth();
			height = frame.getHeight();
		}
	}

	public Game getState(int id) {
		return (Game) states.get(new Integer(id));
	}

	public void setTitle(String s) {
		setName(s);
	}

	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public static String getName() {
		return name;
	}

	public static void setName(String s) {
		name = s;
	}
	
	public void setCursorVisible(boolean CusVis) {
	}
	
	public void setPaused(boolean paused) {
		loop.paused = paused;
	}

}

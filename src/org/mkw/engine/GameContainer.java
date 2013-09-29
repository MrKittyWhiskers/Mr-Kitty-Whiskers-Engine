package org.mkw.engine;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.HashMap;
import javax.swing.JFrame;

public class GameContainer {

	static JFrame frame;
	private static String name;
	public Game currentState;
	private Game nextState;
	private int width = 0;
	private int height = 0;
	private int xLoc = 10;
	private int yLoc = 10;
	GameLoop loop;
	public boolean isDebugVis;
	Input input;
	HashMap<Integer, Game> states = new HashMap<Integer, Game>();
	private boolean isErrorScreenEnabled = true;

	private final UncaughtExceptionHandler defUEH = Thread.getDefaultUncaughtExceptionHandler();
	private final UncaughtExceptionHandler errorScreen = new ErrorScreen();
	static String errorScreenText;

	public void initInput(Input input) {
		this.input = input;
	}

	/**
	 * 
	 * @param state
	 */
	public void addState(Game state) {
		states.put(new Integer(state.getID()), state);
	}

	/**
	 * Changes the state. The state will then update and render.
	 * 
	 * @param id
	 *            (Needs to be the same as the number that is returned from
	 *            getID() in the Game class)
	 */

	static GameCanvas canvas;

	public void enterState(int id) {
		nextState = getState(id);
		if (nextState == null) {
			throw new RuntimeException("No game state registered with the ID: " + id);
		} else {
			input.game = nextState;
			if (currentState == null) {
				frame = new JFrame(getName());
				frame.setSize(width, height);
				frame.setLocation(xLoc, yLoc);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setResizable(false);
				frame.setVisible(true);
				frame.addMouseListener(input);
				frame.addKeyListener(input);
				canvas = new GameCanvas(nextState, input, this);
				frame.add(canvas);
				loop = new GameLoop(this, nextState, canvas);
				loop.start("GAMECONTAINER");
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
			xLoc = frame.getX();
			yLoc = frame.getY();
		}
	}

	public void init() {
		for (Game g : states.values()) {
			g.init();
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

	protected void setLocation(int x, int y) {
		xLoc = x;
		yLoc = y;
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

	public void setRunning(boolean bool) {
		if (bool == true) {
			loop.start("THREAD");
		} else {
			loop.stop();
		}
	}

	public boolean isErrorScreenEnabled() {
		return isErrorScreenEnabled;
	}

	public void setErrorScreenEnabled(boolean isErrorScreenEnabled, String customText) {
		this.isErrorScreenEnabled = isErrorScreenEnabled;

		if (isErrorScreenEnabled()) {
			Thread.setDefaultUncaughtExceptionHandler(errorScreen);
			errorScreenText = customText;
		} else {
			Thread.setDefaultUncaughtExceptionHandler(defUEH);
		}
	}

	public void repaint() {
		canvas.repaint();
	}
}

package org.nc.engine;

import org.nc.engine.Graphics;

public abstract class Game {

	protected boolean over;
	protected int delay = 30;
	protected int width = 512;
	protected int height = 512;
	protected String title = GameContainer.name;

	protected int mouseXpro;
	protected int mouseYpro;
	protected int deltapro = 0;
	protected String stateName = "";
	protected String debuginfo = "";
	
	public int mouseX() {
		return mouseXpro;
	}

	public int mouseY() {
		return mouseYpro;
	}

	public int delta() {
		return deltapro;
	}

	public String getTitle() {
		return title;
	}

	public long getDelay() {
		return delay;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
//	public GameContainer gc;
//	public Input input;

	public abstract void init();

	public abstract void update();

	abstract public void render(Graphics g);

	public boolean isOver() {
		return over;
	}
}

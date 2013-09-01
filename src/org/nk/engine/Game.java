package org.nk.engine;

import java.awt.event.KeyEvent;

public abstract class Game {
	
	public Game(GameContainer gc) {
		this.gc = gc;
	}

	protected boolean over;
	protected int delay = 30;
	protected String title = GameContainer.getName();
	
	public GameContainer gc;

	int mouseXpro;
	int mouseYpro;
	int delta = 0;
	float interpolation = 0;
	private String stateName = "";
	public String debuginfo = "";
	
	public int mouseX() {
		return mouseXpro;
	}

	public int mouseY() {
		return mouseYpro;
	}

	public int delta() {
		return delta;
	}
	
	public int interpolation() {
		return delta;
	}

	public String getTitle() {
		return title;
	}

	public long getDelay() {
		return delay;
	}

	public abstract void init();

	public abstract void update();

	public abstract void render(Graphics g);
	
	public void keyPress(KeyEvent e){}
	public void keyRelease(KeyEvent e){}
	
	public abstract int getID();

	public boolean isOver() {
		return over;
	}
	
	public boolean isPaused() {
		return gc.loop.paused;
	}

	public void setPaused(boolean paused) {
		gc.loop.paused = paused;
	}

	protected void end() {
		try {
			this.finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
}

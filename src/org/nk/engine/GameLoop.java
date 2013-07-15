package org.nk.engine;

import java.util.Timer;

public class GameLoop implements Runnable {
	GameContainer gc;
	Game game;
	GameCanvas canvas;
	public static Timer timer = new Timer();
	long lastLoopTime = System.currentTimeMillis();
	boolean hasInitRun = false;
	long delta;

	boolean paused = false;
	private boolean wasPaused;
	private int fps = 0;
	private int frameCount = 0;

	public GameLoop(GameContainer gc, Game game, GameCanvas canvas) {
		this.game = game;
		this.canvas = canvas;
		this.gc = gc;
	}

	public void run() {
		loop();
	}

	private void loop() {
		// This value would probably be stored elsewhere.
		final double GAME_HERTZ = 20.0;
		// Calculate how many ns each frame should take for our target game
		// hertz.
		final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
		// At the very most we will update the game this many times before a new
		// render.
		// If you're worried about visual hitches more than perfect timing, set
		// this to 1.
		final int MAX_UPDATES_BEFORE_RENDER = 20;
		// We will need the last update time.
		double lastUpdateTime = System.nanoTime();
		// Store the last time we rendered.
		double lastRenderTime = System.nanoTime();

		// If we are able to get as high as this FPS, don't render again.
		final double TARGET_FPS = 30;
		final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;
		// Simple way of finding FPS.
		int lastSecondTime = (int) (lastUpdateTime / 1000000000);
		int updateCount = 0;
		while (!game.isOver()) {
			delta = System.currentTimeMillis() - lastLoopTime;
			lastLoopTime = System.currentTimeMillis();
			double now = System.nanoTime();

			if (!paused) {
				// Do as many game updates as we need to, potentially playing
				// catchup.
				while (now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER) {
					game.update();
					lastUpdateTime += TIME_BETWEEN_UPDATES;
					updateCount++;
				}

				// If for some reason an update takes forever, we don't want to
				// do an insane number of catchups.
				// If you were doing some sort of game that needed to keep EXACT
				// time, you would get rid of this.
				if (now - lastUpdateTime > TIME_BETWEEN_UPDATES) {
					lastUpdateTime = now - TIME_BETWEEN_UPDATES;
				}

				// Render. To do so, we need to calculate interpolation for a
				// smooth render.
				game.delta = (int) delta;
				canvas.repaint();
				frameCount++;
				lastRenderTime = now;

				// Update the frames we got.
				int thisSecond = (int) (lastUpdateTime / 1000000000);
				if (thisSecond > lastSecondTime) {
					game.debuginfo = game.getStateName() + " X: " + game.mouseX() + " Y: " + game.mouseY() + " FPS: " + fps + " UPS: " + updateCount;
					fps = frameCount;
					frameCount = 0;
					updateCount = 0;
					lastSecondTime = thisSecond;
				}

				// Yield until it has been at least the target time between
				// renders. This saves the CPU from hogging.
				while (now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS && now - lastUpdateTime < TIME_BETWEEN_UPDATES) {
					Thread.yield();

					// This stops the app from consuming all your CPU. It makes
					// this slightly less accurate, but is worth it.
					// You can remove this line and it will still work (better),
					// your CPU just climbs on certain OSes.
					// FYI on some OS's this can cause pretty bad stuttering.
					// Scroll down and have a look at different peoples'
					// solutions to this.
					try {
						Thread.sleep(1);
					} catch (Exception e) {
					}

					now = System.nanoTime();
					if (wasPaused) {
						game.delta = (int) (delta = 1);
					}
					wasPaused = false;
				}
			} else {
				wasPaused = true;
			}
		}
	}
}
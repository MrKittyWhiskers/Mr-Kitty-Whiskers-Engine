package org.nc.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class Input implements KeyListener, MouseListener, MouseMotionListener {

	public boolean isMouseDown;
	public Game game;

	public Input() {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		game.mouseXpro = e.getX();
		game.mouseYpro = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		game.mouseXpro = e.getX();
		game.mouseYpro = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		isMouseDown = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		isMouseDown = false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keyPress(e, game);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keyRelease(e, game);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		keyType(e, game);
	}
	
	public abstract void keyPress(KeyEvent e, Game game);
	public abstract void keyRelease(KeyEvent e, Game game);
	public abstract void keyType(KeyEvent e, Game game);
}
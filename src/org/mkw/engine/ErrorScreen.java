package org.mkw.engine;

import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.ScrollPane;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;

import javax.swing.JTextArea;

public class ErrorScreen implements UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		Panel p = new Panel();
		JTextArea info = new JTextArea();
		ScrollPane scroll = new ScrollPane();

		p.setLayout(new BorderLayout());
		
		GameContainer.frame.add(p);
		p.add(scroll);
		scroll.add(info);

		ArrayList<String> text = new ArrayList<String>();
		text.add(GameContainer.errorScreenText + "\n");
		text.add("\n");
		text.add(e.getMessage() + " in " + t.getName() + t.getId() + "\n");
		for (StackTraceElement element : e.getStackTrace()) {
			text.add(element.toString() + "\n");
		}
		for (String te : text) {
			info.append(te);
		}

		info.setEditable(false);

		GameContainer.frame.remove(GameContainer.canvas);
	}
}
//BorderLayout.CENTER, scroll
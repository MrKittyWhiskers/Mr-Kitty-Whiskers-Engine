package org.nk.engine;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;

import javax.swing.JTextArea;


public class ErrorScreen implements UncaughtExceptionHandler {
	
	@Override
	public void uncaughtException(Thread t, Throwable e) {
			JTextArea info = new JTextArea();
			ArrayList<String> text = new ArrayList<String>();
			text.add(GameContainer.errorScreenText + "\n");
			text.add("\n");
			text.add(e.getMessage() + " in " + t.getName() + t.getId() + "\n");
			for (StackTraceElement element : e.getStackTrace()) {
				text.add(element.toString() + "\n");
			}
			GameContainer.frame.add(info);
			for (String te : text) {
				info.append(te);
			}
			
			info.setEditable(false);
			
			GameContainer.frame.remove(GameContainer.canvas);
	}

}

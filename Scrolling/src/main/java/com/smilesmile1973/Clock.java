package com.smilesmile1973;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Timer;

public class Clock implements Runnable {
	private Vector<ClockListener> clockListeners = new Vector<ClockListener>();
	private int sleepTime;
	private Thread t;
	public Clock(int timeInMillisecond){
		sleepTime = timeInMillisecond;
		t = new Thread(this);
		t.start();
	}

	public synchronized void addClockListener(ClockListener clockListener) {
		clockListeners.add(clockListener);
	}

	public synchronized void removeListener(ClockListener clockListener) {
		int index = clockListeners.indexOf(clockListener);
		if (index != -1) {
			clockListeners.remove(clockListener);
		}
	}

	private synchronized void notifyListeners() {
		for (ClockListener clockListener : clockListeners) {
			clockListener.runMe();
		}
	}

	@Override
	public void run() {
		while (true){
			notifyListeners();
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	
}

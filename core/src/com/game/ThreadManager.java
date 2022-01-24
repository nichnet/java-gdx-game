package com.game;

import java.util.ArrayList;
import java.util.List;

public class ThreadManager {

	private static ThreadManager instance;
	private List<Thread> threads;
	
	public ThreadManager() {
		this.threads = new ArrayList<>();
	}
	
	public void addThread(Runnable runnable) {
		Thread thread = new Thread(runnable);
		thread.setDaemon(true);
		thread.start();
		
		
		threads.add(thread);
	}
	
	private void stopThread(Thread thread) {
		if(threads.contains(thread)) {
			thread.interrupt();
		}
	}
	
	public void disposeAll() {
		for(Thread thread : threads) { 
			stopThread(thread);
		}
		
		threads.clear();
	}

	public static ThreadManager getInstance() {
		if(instance == null) {
			instance = new ThreadManager();
		}
		
		return instance;
	}
}

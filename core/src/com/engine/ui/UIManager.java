package com.engine.ui;

public class UIManager {

	private static UIManager instance;
	
	public void UIManager() {
		
	}
	
	public static UIManager getInstance() {
		if(instance == null) {
			instance = new UIManager();
		}
		
		return instance;
	}
}

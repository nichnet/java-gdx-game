package com.engine.util;

public class Settings {

	private static Settings instance;
	
	public static Settings getInstance() {
		if(instance == null) {
			instance = new Settings();
		}
		
		return instance;
	}

	public void load() {
		// TODO Load Settings from file
		
	}

	public String getSelectedLanguage() {
		return "EN";
	}

}

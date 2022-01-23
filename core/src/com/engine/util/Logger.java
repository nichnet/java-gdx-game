package com.engine.util;

public class Logger {
	
	private static Logger instance;
	
	public static Logger getInstance() {
		if(instance == null) {
			instance = new Logger();
		}
		
		return instance;
	}
	
	public static void log(String value) {
		getInstance().print(String.format("[Log] %s", value));		
	}
	
	public static void warning(String value) {
		getInstance().print(String.format("[Warning] %s", value));
	}
	
	public static void error(String value) {
		getInstance().print(String.format("[Error] %s", value));
	}
	
	private void print(String value) {
		//TODO write to log file
		System.out.println(value);
	}

}

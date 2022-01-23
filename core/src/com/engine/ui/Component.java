package com.engine.ui;

public abstract class Component {

	private String name;
	private int x, y;
	private int width, heiht;
	private boolean visible = false;
	
	public Component(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void show() {
		visible = true;
	}
	
	public void hide() {
		visible = false;
	}
}

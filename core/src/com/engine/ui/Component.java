package com.engine.ui;

import com.badlogic.gdx.graphics.Texture;
import com.engine.assets.asset.TextureManager;
import com.engine.assets.graphics.Sprite;
import com.engine.util.Logger;

public abstract class Component {

	private String name;
	private int x, y;
	private int width, height;
	private boolean visible = false;
	
	private String background = "ui";//TODO this is a test.

	
	public Texture getBackground() {
		return TextureManager.getInstance().get(background);
	}
	
	public Component(String name, int x, int y, int width, int height) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		Logger.log("wh:"  + width + ", " + height);
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

	public int getY() {
		return y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	
}

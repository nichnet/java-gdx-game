package com.engine.assets.graphics;

import com.engine.assets.ResourceBase;

public class Animation extends ResourceBase {

	private String[] sprites;
	private int speed;
	private boolean loop;
	
	public Animation(String name) {
		super(name);
	}
	
	public Animation setSprites(String[] sprites) {
		this.sprites = sprites;
		
		return this;
	}
	
	public Animation setSprites(Sprite[] sprites) {
		this.sprites = new String[sprites.length];
		
		for(int i = 0; i < sprites.length; i++) {
			this.sprites[i] = sprites[i].getName();
		}

		return this;
	}

	public Animation setSpeed(int speed) {
		this.speed = speed;
		return this;
	}

	public Animation setLoop(boolean loop) {
		this.loop = loop;
		return this;
	}
	
	public String[] getSprites() {
		return this.sprites;
	}
	
	public String getSprite(int index) {
		if(index < 0 || index >= sprites.length) {
			return "missing";
		}
		
		return sprites[index];
	}
	
	public int getSpeed() { 
		return this.speed;
	}
	
	public boolean doesLoop() {
		return this.loop;
	}
	
	@Override
	public String toString() {
		String out = String.format("%s, speed: %d, loop: %s\n", this.getName(), this.getSpeed(), this.doesLoop());
		
		for(String spriteName : this.getSprites()) {				
			out += String.format("\t\t\t%s\n", spriteName);
		}
		
		return out;
	}

	public int getSpriteCount() {
		return sprites.length;
	}
}

package com.engine.assets.graphics;

public class Vector {
	
	private int x;
	private int y;
	
	public static Vector zero() { 
		return new Vector(0,0);
	}
	
	
	public Vector(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override 
	public String toString() {
		return String.format("(%d, %d)", getX(), getY());
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

}

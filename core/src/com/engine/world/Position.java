package com.engine.world;

import com.badlogic.gdx.graphics.Texture;
import com.engine.assets.graphics.Vector;
import com.engine.util.Constants;

public final class Position extends Vector {

	public final static int MOVE_DISTANCE = 1;
	
	public Position(int x, int y) {
		super(x * Constants.GRID_SIZE, y * Constants.GRID_SIZE);
	}
	
	/**
	 * moves the position distance "west" (left) by {@value Position#MOVE_DISTANCE} pixel units.
	 */
	public void west() {
		super.setX(super.getX() - MOVE_DISTANCE);
	}
	
	/**
	 * moves the position distance "east" (right) by {@value Position#MOVE_DISTANCE} pixel units.
	 */
	public void east() {
		super.setX(super.getX() + MOVE_DISTANCE);
	}
	
	/**
	 * moves the position distance "north" (up) by {@value Position#MOVE_DISTANCE} pixel units.
	 */
	public void north() {
		super.setY(super.getY() + MOVE_DISTANCE);
	}	
	
	/**
	 * moves the position distance "south" (down) by {@value Position#MOVE_DISTANCE} pixel units.
	 */
	public void south() {
		super.setY(super.getY() - MOVE_DISTANCE);
	}

	/**
	 * @return Returns the X coordinate in grid units ({@value Constants#GRID_SIZE} pixel units).
	 */
	@Override
	public int getX() {
		return (int) Math.round(Math.floor((double) (super.getX() / Constants.GRID_SIZE)));
	}
	
	/**
	 * @return Returns the X coordinate in pixel units.
	 */
	public int getXAsPixel() {
		return super.getX();
	}
	
	/**
	 * @return Returns the Y coordinate in grid units ({@value Constants#GRID_SIZE} pixel units).
	 */
	@Override
	public int getY() {
		return (int) Math.round(Math.floor((double) (super.getY() / Constants.GRID_SIZE)));
	}
	
	/**
	 * @return Returns the Y coordinate in pixel units.
	 */
	public int getYAsPixel() {
		return super.getY();
	}

	/**
	 * @return Returns the distance between two positions (in grid size units).
	 */
	public final int getDistance(Position other) {
		return (int) Math.sqrt(Math.pow(other.getX() - getX(), 2) + Math.pow(other.getY() - getY(),2));
	}
	
	/**
	 * @return Returns whether the two positions are equal (same X and Y grid coordinate, not pixel coordinate).
	 */
	@Override
	public boolean equals(java.lang.Object other) {
		if(other == null) {
			return false; 
		}
		
		if(!(other instanceof Position)) {
			return false;
		}
		
		return this.getX() == ((Position)other).getX() && 
				this.getY() == ((Position)other).getY();
	}
}
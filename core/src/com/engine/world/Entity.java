package com.engine.world;

import com.badlogic.gdx.utils.TimeUtils;
import com.engine.assets.asset.AssetsManager;
import com.engine.assets.model.EntityAsset;
import com.engine.util.Constants;
import com.game.Game;

public class Entity extends Object {

	public enum Direction { NORTH, EAST, SOUTH, WEST}
	protected Direction direction = Direction.SOUTH;
	
	public Entity(String id, String assetId, Position position) {
		super(id, assetId, position);
	}

	@Override
	public EntityAsset getAsset() {
		return AssetsManager.getInstance().getEntities().get(this.getAssetId());
	}
	
	public void moveNorth() {
		this.direction = Direction.NORTH;
		move();
	}
	public void moveSouth() {
		this.direction = Direction.SOUTH;
		move();
		
	}

	public void moveEast() {
		this.direction = Direction.EAST;
		move();
	}
	
	public void moveWest() {
		this.direction = Direction.WEST;
		move();
	}
	
	private boolean canMove(Direction west) {
		return true;// TimeUtils.timeSinceMillis(lastMoved) >= Constants.TICK_DELAY =1;
	}

	protected void move() { 
		if(!canMove(direction)) {
			return;
		}
		switch (direction) {
			case NORTH:
				this.getPosition().north();
				break;
			case EAST:
				this.getPosition().east();
				break;
			case SOUTH:
				this.getPosition().south();
				break;
			case WEST:
				this.getPosition().west();
				break;
		}
		updateLastMoved();
	}
	
	private void updateLastMoved() {
		lastMoved = TimeUtils.millis();
	}

	private long lastMoved = -1;
	
	public boolean isMoving() {
		return TimeUtils.timeSinceMillis(lastMoved) <= Constants.TICK_DELAY;
	}
	
	@Override
	public void tick() {
		super.tick();
		//TODO other brain things.. FSM
		determineAnimation();
	}
	
	private void determineAnimation() {
		if(isMoving()) {
			switch(direction) {
				case NORTH:
					getAnimator().setAnimation("walk_north");
					break;
				case EAST:
					getAnimator().setAnimation("walk_east");
					break;
				case SOUTH:
					getAnimator().setAnimation("walk_south");
					break;
				case WEST:
					getAnimator().setAnimation("walk_west");
					break;	
			}
		} else {
			switch(direction) {
				case NORTH:
					getAnimator().setAnimation("idle_north");
					break;
				case EAST:
					getAnimator().setAnimation("idle_east");
					break;
				case SOUTH:
					getAnimator().setAnimation("idle_south");
					break;
				case WEST:
					getAnimator().setAnimation("idle_west");
					break;	
			}
		}
	}
}

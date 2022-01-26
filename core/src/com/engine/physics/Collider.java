package com.engine.physics;

import java.util.ArrayList;
import java.util.List;

import com.engine.world.Entity.Direction;
import com.engine.world.ObjectBase;
import com.engine.world.Position;

public class Collider {

	private ObjectBase attachedToObject;
	
	public Collider(ObjectBase object) {
		this.attachedToObject = object;
	}

	public boolean isColliding() {
		return collisions.size() > 0;//if more than 0 active collisions, then object is colliding.
	}
	
	private List<ObjectBase> collisions = new ArrayList<>();
	
	public void setCollision(ObjectBase other, boolean colliding) {
		if(collisions.contains(other)) {
			if(!colliding) {
				//remove other from active collisions if no longer colliding.
				collisions.remove(other);
				attachedToObject.onColliderExit(other);
			} else {
				//was previously colliding and is still colliding ,
				attachedToObject.onColliderStay(other);
			}
		} else {
			//was not previously colliding with other object but now is.
			if(colliding) {
				collisions.add(other);
				attachedToObject.onColliderEnter(other);
			}
		}
	}
	
	

	public boolean isCollisionInDirection(Direction direction, Position position) {
		for(ObjectBase other : collisions) {
			//TODO think this has to be bounds not position. 
			switch(direction) {
			case NORTH:
				if(other.getPosition().isNorthOf(position)) {
					return true;
				}
				break;
			case EAST:
				if(other.getPosition().isEastOf(position)) {
					return true;
				}
				break;
			case SOUTH:
				if(other.getPosition().isSouthOf(position)) {
					return true;
				}
				break;
			case WEST:
				if(other.getPosition().isWestOf(position)) {
					return true;
				}
				break;
			}
		}

		return false;
	}

	public boolean canMoveInDirection() {
		for(ObjectBase other : collisions) {
			return false;
		}//TODO
		
		
		return true;
	}
}

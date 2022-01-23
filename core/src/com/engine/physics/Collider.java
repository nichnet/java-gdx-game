package com.engine.physics;

import com.engine.world.ObjectBase;

public class Collider {

	private boolean isColliding = false;
	private IColliderEvent events;
	
	public Collider(IColliderEvent events) {
		this.events = events;
	}

	public boolean isColliding() {
		return this.isColliding;
	}
	
	public void setCollision(ObjectBase other, boolean colliding) {
		if(colliding) {
			if(isColliding) {
				//then was already colliding. so stay
				events.onColliderStay(other);
			} else {
				events.onColliderEnter(other);
			}
		} else {
			if(isColliding) {
				//was previously colliding... no longer colliding.
				events.onColliderExit(other);
			}
		}
		
		isColliding = colliding;
	}
}

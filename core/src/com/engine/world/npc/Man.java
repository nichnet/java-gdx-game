package com.engine.world.npc;

import com.engine.world.LivingEntity;
import com.engine.world.Position;

public class Man extends LivingEntity {

	public Man(String id, String assetId, Position position) {
		super(id, assetId, position);
	}
	
	@Override
	public void tick() {
		super.tick();
		
		if(getPosition().getX() <= 2) {
			direction = Direction.EAST;
		} else if(getPosition().getX() >= 8) {
			direction = Direction.WEST;
		}
		
		move();
	}
}


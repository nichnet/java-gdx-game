package com.engine.world.npc;

import com.engine.world.LivingEntity;
import com.engine.world.Position;
import com.engine.world.ai.FSM;

public class Rat extends LivingEntity {

	public class RatFSM extends FSM {
		
	}
	
	private RatFSM brain; 
	
	public Rat(String id, Position position) {
		super(id, "1", position);
		this.brain = new RatFSM();
	}
	
	public FSM getBrain() {
		return this.brain;
	}
}
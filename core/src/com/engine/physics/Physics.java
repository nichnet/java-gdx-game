package com.engine.physics;

import com.engine.world.ObjectBase;
import com.game.Game;

public class Physics {
	
	private static Physics instance;
	
	public void tick() {
		ObjectBase[] objects = Game.getInstance().getCurrentWorld().getAllObjects();
		for(ObjectBase obj : objects) {
			//TODO check each object collision here.
		}
	}

	

	public static Physics getInstance() {
		if(instance == null) {
			instance = new Physics();
		}
		
		return instance;
	}
}

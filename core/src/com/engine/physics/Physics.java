package com.engine.physics;

import com.engine.util.Logger;
import com.engine.world.ObjectBase;
import com.engine.world.Tile;
import com.game.Game;

public class Physics {
	
	private static Physics instance;
	
	public void tick(ObjectBase obj) {
		/*for(Tile other : Game.getInstance().getCurrentWorld().getAllTiles().values()) {
			if(obj.getId().equals(other.getId())) {
				continue;
			}
		}*/
	}

	private boolean basicAABBCollisionCheck() {
/*		if (x.getX() + x.getwidth() < y.getX() + y.getWidth() ||
	            x.getY() + x.getHeight() < y.getY() + y.getHeight() {
	        collide = true;
	    }
	*/
		return false;
	}

	public static Physics getInstance() {
		if(instance == null) {
			instance = new Physics();
		}
		
		return instance;
	}
}

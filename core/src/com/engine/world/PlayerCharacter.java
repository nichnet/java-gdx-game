package com.engine.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayerCharacter extends LivingEntity {

	public PlayerCharacter(String id, Position position) {
		super(id, "man", position);
	}

	/**
	 * @return Returns whether this player character belongs to the local player, or remote.
	 */
	public boolean isLocalPlayer()  {
		return true;
	}

}

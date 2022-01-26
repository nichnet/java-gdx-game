package com.engine.world;

import com.engine.util.Logger;

public class PlayerCharacter extends LivingEntity {

	public PlayerCharacter(String id, Position position) {
		super(id, "player", position);
	}

	/**
	 * @return Returns whether this player character belongs to the local player, or remote.
	 */
	public boolean isLocalPlayer()  {
		return true;
	}
	

	@Override
	public void onColliderEnter(ObjectBase other) {
		Logger.log("Entered collider of: " + other.getAssetId());
	}

	@Override
	public void onColliderExit(ObjectBase other) {
		Logger.log("Exited collider of: " + other.getAssetId());
		
	}

	@Override
	public void onColliderStay(ObjectBase other) {
		Logger.log("Staying in collider of: " + other.getAssetId());
		
	}

}

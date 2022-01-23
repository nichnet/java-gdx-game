package com.engine.world;

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
	

	@Override
	public void onColliderEnter(ObjectBase other) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onColliderExit(ObjectBase other) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onColliderStay(ObjectBase other) {
		// TODO Auto-generated method stub
		
	}

}

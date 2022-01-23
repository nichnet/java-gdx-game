package com.engine.physics;

import com.engine.world.ObjectBase;

public interface IColliderEvent {

	public void onColliderEnter(ObjectBase other);
	public void onColliderExit(ObjectBase other);
	public void onColliderStay(ObjectBase other);
}

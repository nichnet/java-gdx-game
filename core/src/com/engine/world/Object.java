package com.engine.world;

import com.engine.assets.asset.AssetsManager;
import com.engine.assets.model.AssetBase;
import com.engine.physics.Physics;

public class Object extends ObjectBase {
	
	
	public Object(String id, String assetId, Position position) {
		super(id, assetId, position);
	}

	@Override
	public AssetBase getAsset() {
		return AssetsManager.getInstance().getObjects().get(this.getAssetId());
	}

	@Override
	public void tick() {
		super.tick();
		/*if(getCollider() != null) {
			Physics.getInstance().tick(this);
		}*/
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

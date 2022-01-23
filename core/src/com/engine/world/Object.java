package com.engine.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.engine.assets.asset.AssetsManager;
import com.engine.assets.model.AssetBase;
import com.engine.renderer.Renderer;

public class Object extends ObjectBase{

	public Object(String id, String assetId, Position position) {
		super(id, assetId, position);
	}

	@Override
	public AssetBase getAsset() {
		return AssetsManager.getInstance().getObjects().get(this.getAssetId());
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
}

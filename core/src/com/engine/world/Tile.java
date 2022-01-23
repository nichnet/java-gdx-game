package com.engine.world;

import com.engine.assets.asset.AssetsManager;
import com.engine.assets.model.TileAsset;

public class Tile extends Object{
	
	public Tile(String id, String assetId, Position position) {
		super(id, assetId, position);
	}

	public final TileAsset getAsset() {
		return AssetsManager.getInstance().getTiles().get(this.getAssetId());
	}
}

package com.engine.world;

import com.engine.assets.asset.AssetsManager;
import com.engine.assets.model.LivingEntityAsset;

public class LivingEntity extends Entity {

	private Inventory inventory;
	
	public LivingEntity(String id, String assetId, Position position) {
		super(id, assetId, position);
		this.inventory = new Inventory(getAsset().getInventoryRows(), getAsset().getInventoryRows());
	}

	public Inventory getInventory() {
		return this.inventory;
	}
	
	@Override
	public LivingEntityAsset getAsset() {
		return AssetsManager.getInstance().getLivingEntities().get(this.getAssetId());
	}
	
	
}

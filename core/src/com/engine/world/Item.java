package com.engine.world;

import com.engine.assets.asset.AssetsManager;
import com.engine.assets.model.ItemAsset;

public class Item extends Object {

	private int quantity = 1;
	
	public Item(String id, String assetId, Position position, int quantity) {
		super(id, assetId, position);
		
		if(quantity > 1) {
			if(getAsset().isStackable()) {
				this.quantity = quantity;
			}
		}
	}
	
	public final int getQuantity() {
		return this.quantity;
	}
	
	public final int canStack() {
		return this.quantity;
	}

	@Override
	public ItemAsset getAsset() {
		return AssetsManager.getInstance().getItems().get(this.getAssetId());
	}


}

package com.engine.assets.model;

public class LivingEntityAsset extends EntityAsset {

	private int inventoryRows;
	private int inventoryColumns;
	
	public LivingEntityAsset(String name) {
	    super(name);
		///this.inventoryRows = inventoryRows;
		//this.inventoryColumns = inventoryColumns;
	}
	
	public int getInventoryRows() { 
		return this.inventoryRows;
	}
	
	public int getInventoryColumns() { 
		return this.inventoryColumns;
	}
}

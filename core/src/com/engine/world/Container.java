package com.engine.world;

public class Container extends Object {
	//Container is for chests, bags, etc. Essentially an object with an inventory.
	private Inventory inventory;
		
	public Container(String id, String assetId, Position position, int rows, int columns) {
		super(id, assetId, position);
		this.inventory = new Inventory(rows, columns);
	}

	public Inventory getInventory() {
		return this.inventory;
	}
}

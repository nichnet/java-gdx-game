package com.engine.assets.model;

public class ItemAsset extends AssetBase {

	private boolean stackable;
	
	public ItemAsset(String name) {
		super(name);
	}

	public ItemAsset setStackable(boolean stackable) {
		this.stackable = stackable;
		return this;
	}
	
	public boolean isStackable() {
		return this.stackable;
	}
	
	@Override
	public String toString() {
		String out = String.format("item: %s\n", this.getName());
		
		 out += super.toString();
		 
		 out += String.format("\tstackable: %s", this.isStackable()); 
		 return out;
	}
}

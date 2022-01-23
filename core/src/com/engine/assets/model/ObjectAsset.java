package com.engine.assets.model;

public class ObjectAsset extends AssetBase {
	
	public ObjectAsset(String name, int offsetX, int offsetY) {
		super(name, offsetX, offsetY);
	}

	@Override
	public String toString() {
		String out = String.format("object: %s\n", this.getName());
		
		 out += super.toString();
		 
		 return out;
	}
}

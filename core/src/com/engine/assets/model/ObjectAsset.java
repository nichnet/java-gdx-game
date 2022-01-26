package com.engine.assets.model;

import com.engine.assets.graphics.Vector;

public class ObjectAsset extends AssetBase {
	
	public ObjectAsset(String name, Vector offset) {
		super(name, offset);
	}

	@Override
	public String toString() {
		String out = String.format("object: %s\n", this.getName());
		
		 out += super.toString();
		 
		 return out;
	}
}

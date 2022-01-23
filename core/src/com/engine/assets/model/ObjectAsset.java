package com.engine.assets.model;

import com.engine.assets.graphics.Animation;
import com.engine.assets.graphics.Sprite;

public class ObjectAsset extends AssetBase {
	
	public ObjectAsset(String name) {
		super(name);
	}

	@Override
	public String toString() {
		String out = String.format("object: %s\n", this.getName());
		
		 out += super.toString();
		 
		 return out;
	}
}

package com.engine.assets.model;

import com.engine.assets.graphics.Animation;
import com.engine.assets.graphics.Sprite;

public class TileAsset extends AssetBase {
	
	private boolean walkable = false;
	
	public TileAsset(String name) {
		super(name);
	}

	public TileAsset setWalkable(boolean walkable) {
		this.walkable = walkable;
		return this;
	}

	public boolean isWalkable() {
		return this.walkable;
	}
	
	@Override
	public String toString() {
		String out = String.format("tile: %s\n", this.getName());
		
		out += super.toString(); 
		out += String.format("\twalkable: %s", this.isWalkable()); 
		
		return out;
	}
}

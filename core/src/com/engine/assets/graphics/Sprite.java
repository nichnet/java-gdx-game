package com.engine.assets.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.engine.assets.ResourceBase;
import com.engine.assets.asset.TextureManager;
import com.engine.util.Logger;

public class Sprite extends ResourceBase {

	private Bounds bounds;
	
	public Sprite(String name) {
		super(name);
	}
	
	public Sprite setBounds(Bounds bounds) {
		this.bounds = bounds;
		return this;
	}
	
	public Bounds getBounds() {
		return this.bounds;
	}

	public Texture getTexture() {
		return TextureManager.getInstance().get(getName());
	}
	@Override
	public String toString() {
		return String.format("name: %s, bounds: %s", getName(), getBounds());
	}
}

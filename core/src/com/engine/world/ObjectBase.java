package com.engine.world;

import com.engine.assets.graphics.Animator;
import com.engine.assets.model.AssetBase;
import com.engine.util.Constants;
import com.game.Game;

public abstract class ObjectBase implements Comparable<ObjectBase> {

	private String id;
	private String assetId; 
	private Position position;
	private Animator animator;

	public ObjectBase(String id, String assetId, Position position) {
		this.id = id;
		this.assetId = assetId;
		this.position = position;

		this.animator = new Animator(this);
	}
	
	public final String getId() {
		return this.id;
	}
	
	public final String getAssetId() { 
		return this.assetId;
	}

	public final Position getPosition() {
		return this.position;
	}
	
	public boolean isVisible() {
		return Game.getInstance().getCurrentWorld().getLocalPlayerCharacter().getPosition().getDistance(getPosition()) <= Constants.VIEW_DISTANCE;
	}
	
	public abstract AssetBase getAsset();
	
	
	
	public Animator getAnimator() {
		return this.animator;
	}
	
	public abstract void tick();
	
	
	public void render() {
		getAnimator().render();
	}

	@Override
	public int compareTo(ObjectBase other) {
		return other.getPosition().getYAsPixel() - this.getPosition().getYAsPixel();
	}
}

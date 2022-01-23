package com.engine.assets.graphics;

import com.badlogic.gdx.utils.TimeUtils;
import com.engine.renderer.Renderer;
import com.engine.util.Constants;
import com.engine.world.ObjectBase;
import com.game.Game;

public class Animator {
	private String currentAnimation;
	private int spriteIndex;
	private long lastUpdate = -1;
	private ObjectBase attachedToObject;
	
	public Animator(ObjectBase object) {
		this.currentAnimation = "default";
		this.spriteIndex = 0;
		this.attachedToObject = object;
	}
	
	public void advanceAnimation() {
		Animation anim = attachedToObject.getAsset().getAnimation(currentAnimation);
		if(lastUpdate != -1) {
			if(anim.getSpeed() == 0) {
				return;//no point animating if its 0 speed.. just return.
			}
			
			if((TimeUtils.timeSinceMillis(lastUpdate) < Constants.MILLIS_IN_SEC / anim.getSpeed())) {
				return;//only update sprite per second
			}
		}
		
		lastUpdate = TimeUtils.millis();
		
		if(anim == null) {
			return;
		}
		int size = anim.getSpriteCount();
		
		if(size <= 1) {
			return;
		}
		
		//advance current index.
		spriteIndex++;
		
		if(spriteIndex >= size) { 
			spriteIndex = 0;		
		}
	}
	
	public void setAnimation(String name) {
		if(name.equals(currentAnimation)) {
			return;
		}
		//TODO check if animation exists.
		currentAnimation = name;
		//reset sprite index when changing animation.
		spriteIndex = 0;
	}
	
	public Sprite getCurrentSprite() {
		return attachedToObject.getAsset().getSprite(currentAnimation, spriteIndex);
	}
	
	public void render() {	
		if(!Game.getInstance().isPaused()) {
			advanceAnimation();			
		}
		
		//attachedToObject.updateCollider()
	
		Renderer.getInstance().draw(attachedToObject);
		
		if(Game.getInstance().isDebugModeActive()) {
			if(getCurrentSprite().getBounds() != null) {
				Renderer.getInstance().drawShape(attachedToObject);				
			}
		}
	}
}

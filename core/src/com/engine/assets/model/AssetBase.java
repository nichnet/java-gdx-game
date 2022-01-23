package com.engine.assets.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.engine.assets.ResourceBase;
import com.engine.assets.graphics.Animation;
import com.engine.assets.graphics.Bounds;
import com.engine.assets.graphics.Sprite;
import com.engine.assets.language.LanguageManager;
import com.engine.util.Logger;

public abstract class AssetBase extends ResourceBase {

	private Sprite[] sprites;
	private Animation[] animations;
	
	private int offsetX;
	private int offsetY;
	
	public AssetBase(String name) {
		this(name, 0, 0);
	}

	public AssetBase(String name, int offsetX, int offsetY) {
		super(name);
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}
	
	public int getOffsetX() {
		return this.offsetX;
	}
	public int getOffsetY() {
		return this.offsetY;
	}
	
	public Sprite getDefaultSprite() {
		return sprites[0];
	}
	
	public <T extends AssetBase> T setAnimations(Animation[] animations) {
		List<Animation> anims = new ArrayList<Animation>();
	
		if(animations == null) {
			//default animation
			Animation def = new Animation("default").setSprites(this.sprites).setSpeed(0).setLoop(false);
			anims.add(def);
		} else {
			//if animations are available
			for(Animation animation: animations) { 
				if(!hasAnimation(animation.getName())) {
					anims.add(animation);
				}
			}
		}
		
		this.animations = anims.toArray(new Animation[] {});
		return (T) this;
	}
	
	public Animation getAnimation(String name) { 
		for(Animation anim : animations) {
			if (anim.getName().equals(name)) {
				return anim;
			}
		}
		
		return null;
	}
	
	private boolean hasAnimation(String name) {
		if(animations == null) {
			return false;
		}
		
		return getAnimation(name) != null ? true : false;
	}

	private boolean hasSprite(String name) {
		if(sprites == null) {
			return false;
		}

		return getSprite(name) != null ? true : false;
	}
	
	public Sprite getSprite(String animationName, int index) {
		for(Animation anim : animations) {
			if (anim.getName().equals(animationName)) {
				return getSprite(anim.getSprite(index));
			}
		}
		
		return null;
	}
	
	public Sprite getSprite(String name) {
		for(Sprite sprite : sprites) {
			if (sprite.getName().equals(name)) {
				return sprite;
			}
		}
		
		return null;
	}


	public <T extends AssetBase> T setSprites(Sprite[] sprites) {
		List<Sprite> sprs = new ArrayList<Sprite>();
		
		if(sprites == null) {
			Sprite def = new Sprite("missing").setBounds(new Bounds(new int[] {0,0,32,0,32,32,0,32}));
			sprs.add(def);
		} else {
			for(Sprite sprite : sprites) { 
				if(!hasSprite(sprite.getName())) {
					sprs.add(sprite);
				}
			}
		}
		
		this.sprites = sprs.toArray(new Sprite[] {});
		
		return (T) this;
	}
	
	public AssetBase setSprite(Sprite sprite) {
		return setSprites(new Sprite[] {sprite});
	}


	public final String getLocalizedName() {
		return LanguageManager.getInstance().get(getName());
	}
	
	public final String getLocalizedDescription() {
		return LanguageManager.getInstance().get(String.format("%s_desc", this.getName()));
	}
	
	@Override
	public String toString() {
		String out = "\tsprites:\n";
		
		for(Sprite sprite : this.sprites) {
			out += String.format("\t\t%s\n", sprite);
		}
		
		out += "\tanimations:\n";
		
		for(Animation animation : this.animations) {
			out += String.format("\t\t%s", animation);
		}
		
		return out;
	}
	


	
}

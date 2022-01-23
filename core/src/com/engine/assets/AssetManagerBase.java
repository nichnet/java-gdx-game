package com.engine.assets;

import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.engine.assets.graphics.Animation;
import com.engine.assets.graphics.Bounds;
import com.engine.assets.graphics.Sprite;
import com.engine.assets.model.AssetBase;
import com.engine.util.Constants;
import com.engine.util.Logger;

public abstract class AssetManagerBase extends ResourceManagerBase {

	public abstract ResourceBase get(String id);
	public abstract AssetBase[] all();
	
	public AssetManagerBase() {
		load();
	}

	protected final Animation[] getAnimations(JSONObject obj) {
		if(!hasProperty(obj, "animations")) {
			return null;
		}
		
		JSONArray animationsDataArr = (JSONArray) obj.get("animations");
		
		if(animationsDataArr.size() == 0) {
			return null;
		}
		
		List<Animation> animations = new ArrayList<Animation>();
		
		for(int i = 0; i < animationsDataArr.size(); i++) {
			JSONObject animationData = (JSONObject) animationsDataArr.get(i);
			
			//name of the animation
			String name = getProperty(animationData, "name");
			
			//sprites of the animation
			JSONArray spritesDataArr = (JSONArray) animationData.get("sprites");
			List<String> sprites = new ArrayList<String>();
			
			if(spritesDataArr != null && spritesDataArr.size() > 0) {
				
				for(int j = 0; j < spritesDataArr.size(); j++) {
					sprites.add((String) spritesDataArr.get(j));
				}
			}
			
			//other animation properties
			int speed = Constants.ANIM_DEFAULT_SPEED;
			boolean loop = false;
			
			if(hasProperty(animationData, "speed")) {
				speed = (int)(long) getProperty(animationData, "speed");				
			}
			
			if(hasProperty(animationData, "loop")) {
				loop = (boolean) getProperty(animationData, "loop");
			}
			
			
			animations.add(new Animation(name)
					.setSprites(sprites.size() > 0 ? sprites.toArray(new String[] {}) : null)
					.setSpeed(speed)
					.setLoop(loop)
				);
		}
		
		return animations.toArray(new Animation[] {});
	}

	protected final Bounds getBounds(JSONObject obj) {
		if(!hasProperty(obj, "bounds")) {
			return null;
		}
		
		JSONArray boundsDataArr = (JSONArray)getProperty(obj, "bounds");
		
		if(boundsDataArr.size() == 0) {
			return null;
		}
		
		int[] points = new int[boundsDataArr.size()];

		for(int i = 0; i < points.length; i++) {	
			points[i] = (int) ((long) boundsDataArr.get(i));
		}
		
		return new Bounds(points);
	}
	
	protected final Sprite[] getSprites(JSONObject obj) {
		if(!hasProperty(obj, "sprites")) {
			return null;
		}
		
		JSONArray spritesDataArr = (JSONArray) obj.get("sprites");
		
		if(spritesDataArr.size() == 0) {
			return null;
		}
		
		Sprite[] sprites = new Sprite[spritesDataArr.size()];
		
		for(int i = 0; i < spritesDataArr.size(); i++) {
			JSONObject spriteData = (JSONObject) spritesDataArr.get(i);
			
			String name = getProperty(spriteData, "name");
			Bounds bounds = getBounds(spriteData);
			
			sprites[i] = new Sprite(name).setBounds(bounds);
		}
		
		return sprites;
	}
}

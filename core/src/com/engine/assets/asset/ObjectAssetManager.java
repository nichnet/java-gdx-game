package com.engine.assets.asset;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.badlogic.gdx.Gdx;
import com.engine.assets.AssetManagerBase;
import com.engine.assets.graphics.Animation;
import com.engine.assets.graphics.Sprite;
import com.engine.assets.graphics.Vector;
import com.engine.assets.model.ObjectAsset;
import com.engine.util.Logger;


public final class ObjectAssetManager extends AssetManagerBase {

	private HashMap<String, ObjectAsset> assets;
	
	@Override
	public void load() {
		this.assets = new HashMap<String, ObjectAsset>();
		
		String path = "assets/objects";
		
		String[] files = AssetsManager.getInstance().getResourceFolderFiles(path, ".json");
		
		if(files == null || files.length == 0) {
			Logger.warning(String.format("No object assets found in: \"%s\".", path));
			return;
		}
		
		for(String file : files) {
			InputStream in = Gdx.files.internal(file).read();
			
			if(in == null) {
				Logger.error(String.format("Failed to load object resource: \"%s\".", file));
				continue;
			}

			JSONParser parser = new JSONParser();

			try {
				JSONObject obj = (JSONObject) parser.parse(new InputStreamReader(in));
				
				if(obj == null) {
					Logger.error(String.format("Failed to load object resource: \"%s\", because the file is malformed.", file));
					continue;
				}
				
				String name = getProperty(obj, "name");
				Sprite[] sprites = getSprites(obj);
				Animation[] animations = getAnimations(obj);
				
				
				//offset
				Vector offset = Vector.zero();
				
				if(hasProperty(obj, "offset")) {
					JSONObject offsetData = (JSONObject) obj.get("offset");
					offset.setX((int) ((long) getProperty(offsetData, "x")));
					offset.setY((int) ((long) getProperty(offsetData, "y")));
				}
				
				
				//properties specific to objects
				JSONObject properties = (JSONObject) obj.get("properties");
				
				
				
				addAsset(name, sprites, animations, offset);
				in.close();
			} catch (IOException e) {
				Logger.error(String.format("Failed to load object resource: \"%s\", because the file could not be opened or located.", file));
			} catch (ParseException e) {
				Logger.error(String.format("Failed to load object resource: \"%s\", because the file is malformed.", file));
			} 
			
		}
	}

	private void addAsset(String name, Sprite[] sprites, Animation[] animations, Vector offset) {
		if(this.assets.containsKey(name)) {
			return;
		}
		
		ObjectAsset asset = new ObjectAsset(name, offset).setSprites(sprites).setAnimations(animations);
		
		this.assets.put(name, asset);
	}

	@Override
	public int getSize() {
		if(this.assets == null) {
			return 0;
		}
		
		return this.assets.size();
	}

	@Override
	public ObjectAsset get(String name) {
		return this.assets.get(name);
	}
	
	@Override
	public ObjectAsset[] all() {
		if(this.assets == null) {
			return new ObjectAsset[] {};
		}
		
		return this.assets.values().toArray(new ObjectAsset[] { });
	}
}

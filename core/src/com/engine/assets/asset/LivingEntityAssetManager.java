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
import com.engine.assets.model.LivingEntityAsset;
import com.engine.assets.model.ObjectAsset;
import com.engine.util.Logger;

public final class LivingEntityAssetManager extends AssetManagerBase {

	private HashMap<String, LivingEntityAsset> assets;
	
	@Override
	public void load() {
		this.assets = new HashMap<String, LivingEntityAsset>();
		
		String path = "assets/entities";
		
		String[] files = AssetsManager.getInstance().getResourceFolderFiles(path, ".json");
		
		if(files == null || files.length == 0) {
			Logger.warning(String.format("No entity assets found in: \"%s\".", path));
			return;
		}
		
		for(String file : files) {
			InputStream in = Gdx.files.internal(file).read();
			
			if(in == null) {
				Logger.error(String.format("Failed to load entity resource: \"%s\".", file));
				continue;
			}

			JSONParser parser = new JSONParser();

			try {
				JSONObject obj = (JSONObject) parser.parse(new InputStreamReader(in));
				
				if(obj == null) {
					Logger.error(String.format("Failed to load entity resource: \"%s\", because the file is malformed.", file));
					continue;
				}
				
				String name = getProperty(obj, "name");
				Sprite[] sprites = getSprites(obj);
				Animation[] animations = getAnimations(obj);
				
				//properties specific to entities
				JSONObject properties = (JSONObject) obj.get("properties");
				
				addAsset(name, sprites, animations);
				in.close();
			} catch (IOException e) {
				Logger.error(String.format("Failed to load entity resource: \"%s\", because the file could not be opened or located.", file));
			} catch (ParseException e) {
				Logger.error(String.format("Failed to load entity resource: \"%s\", because the file is malformed.", file));
			} 
		}
	}

	private void addAsset(String name, Sprite[] sprites, Animation[] animations) {
		if(this.assets.containsKey(name)) {
			return;
		}
		
		LivingEntityAsset asset = new LivingEntityAsset(name);//, sprites, animations);
		asset.setSprites(sprites);
		asset.setAnimations(animations);
		
		this.assets.put(asset.getName(), asset);
	}

	@Override
	public int getSize() {
		if(this.assets == null) {
			return 0;
		}
		
		return this.assets.size();
	}

	@Override
	public LivingEntityAsset get(String id) {
		return this.assets.get(id);
	}
	
	@Override
	public LivingEntityAsset[] all() {
		return this.assets.values().toArray(new LivingEntityAsset[] { });
	}
}

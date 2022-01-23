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
import com.engine.assets.model.ItemAsset;
import com.engine.util.Logger;

public final class ItemAssetManager extends AssetManagerBase {

	private HashMap<String, ItemAsset> assets;
	
	@Override
	public void load() {
		String path = "assets/items";
		this.assets = new HashMap<String, ItemAsset>();
		
		String[] files = AssetsManager.getInstance().getResourceFolderFiles(path, ".json");
		
		if(files == null || files.length == 0) {
			Logger.warning(String.format("No item assets found in: \"%s\".", path));
			return;
		}
		
		for(String file : files) {
			InputStream in = Gdx.files.internal(file).read();
			
			if(in == null) {
				Logger.error(String.format("Failed to load item resource: \"%s\".", file));
				continue;
			}

			JSONParser parser = new JSONParser();

			try {
				JSONObject obj = (JSONObject) parser.parse(new InputStreamReader(in));
				
				if(obj == null) {
					Logger.error(String.format("Failed to load item resource: \"%s\", because the file is malformed.", file));
					continue;
				}
				
				String name = getProperty(obj, "name");
				Sprite[] sprites = getSprites(obj);
				Animation[] animations = getAnimations(obj);
				
				//properties specific to tiles
				JSONObject properties = (JSONObject) obj.get("properties");
				
				boolean stackable = (boolean) getProperty(properties, "stackable");
				
				addAsset(name, sprites, animations, stackable);
				in.close();
			} catch (IOException e) {
				Logger.error(String.format("Failed to load item resource: \"%s\", because the file could not be opened or located.", file));
			} catch (ParseException e) {
				Logger.error(String.format("Failed to load item resource: \"%s\", because the file is malformed.", file));
			} 
		}
	}

	private void addAsset(String name, Sprite[] sprites, Animation[] animations, boolean stackable) {
		if(this.assets.containsKey(name)) {
			return;
		}
		
		ItemAsset asset = new ItemAsset(name).setSprites(sprites).setAnimations(animations);
		asset.setStackable(stackable);
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
	public ItemAsset get(String id) {
		return this.assets.get(id);
	}

	@Override
	public ItemAsset[] all() {
		if(this.assets == null) {
			return new ItemAsset[] {};
		}
		
		return this.assets.values().toArray(new ItemAsset[] { });
	}
}

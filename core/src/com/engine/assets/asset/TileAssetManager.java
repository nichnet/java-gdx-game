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
import com.engine.assets.model.TileAsset;
import com.engine.util.Logger;

public final class TileAssetManager extends AssetManagerBase {

	HashMap<String, TileAsset> assets;
	
	@Override
	public void load() {
		String path = "assets/tiles";
		this.assets = new HashMap<String, TileAsset>();
		
		String[] files = AssetsManager.getInstance().getResourceFolderFiles(path, ".json");
		
		if(files == null || files.length == 0) {
			Logger.warning(String.format("No tile assets found in: \"%s\".", path));
			return;
		}
		
		for(String file : files) {
			InputStream in = Gdx.files.internal(file).read();
			
			if(in == null) {
				Logger.error(String.format("Failed to load tile resource: \"%s\".", file));
				continue;
			}

			JSONParser parser = new JSONParser();

			try {
				JSONObject obj = (JSONObject) parser.parse(new InputStreamReader(in));
				
				if(obj == null) {
					Logger.error(String.format("Failed to load tile resource: \"%s\", because the file is malformed.", file));
					continue;
				}
				
				String name = getProperty(obj, "name");

				Sprite[] sprites = getSprites(obj);
				Animation[] animations = getAnimations(obj);
				
				//properties specific to tiles
				JSONObject properties = (JSONObject) obj.get("properties");
				
				boolean walkable = (boolean) getProperty(properties, "walkable");
				
				addAsset(name, sprites, animations, walkable);
				in.close();
			} catch (IOException e) {
				Logger.error(String.format("Failed to load tile resource: \"%s\", because the file could not be opened or located.", file));
			} catch (ParseException e) {
				Logger.error(String.format("Failed to load tile resource: \"%s\", because the file is malformed.", file));
			} 
		}
	}

	private void addAsset(String name, Sprite[] sprites, Animation[] animations, boolean walkable) {
		if(this.assets.containsKey(name)) {
			return;
		}
		
		TileAsset asset =  new TileAsset(name)
				.setSprites(sprites)
				.setAnimations(animations);
			
		asset.setWalkable(walkable);
		
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
	public TileAsset get(String id) {
		return this.assets.get(id);
	}
	
	@Override
	public TileAsset[] all() {
		if(this.assets == null) {
			return new TileAsset[] {};
		}
		
		return this.assets.values().toArray(new TileAsset[] { });
	}
}

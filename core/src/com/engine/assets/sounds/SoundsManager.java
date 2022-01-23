package com.engine.assets.sounds;

import java.util.HashMap;

import com.engine.assets.AssetManagerBase;
import com.engine.assets.model.AssetBase;

public class SoundsManager extends AssetManagerBase {
	
	private static SoundsManager instance;
	private HashMap<String, Sound> assets;
	
	@Override
	public void load() {
		this.assets = new HashMap<String, Sound>();
		
		put("walk_dirt");
		put("walk_grass"); 
		put("swim");
		put("walk_gravel");
		put("click");
		put("container_open");
		put("container_close");
		put("door_open");
		put("door_close");
	}
	
	private void put(String name) {
		if(this.assets.containsKey(name)) {
			return;
		}
		
		this.assets.put(name, new Sound(name));
	}
	
	@Override
	public Sound get(String key) {
		return this.assets.get(key);
	}

	public static SoundsManager getInstance() {
		if(instance == null) {
			instance = new SoundsManager();
		}
		
		return instance;
	}

	public int getSize() {
		return this.assets.size();
	}

	@Override
	public AssetBase[] all() {
		// TODO Auto-generated method stub
		return null;
	}
}

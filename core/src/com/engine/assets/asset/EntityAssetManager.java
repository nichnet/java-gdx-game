package com.engine.assets.asset;

import java.util.HashMap;

import com.engine.assets.AssetManagerBase;
import com.engine.assets.model.EntityAsset;

public final class EntityAssetManager extends AssetManagerBase {

	private HashMap<String, EntityAsset> assets;
	
	@Override
	public void load() {
		// TODO Load Entity Assets from files.
		this.assets = new HashMap<String, EntityAsset>();
		
		addAsset("1", "null");
	}

	private void addAsset(String id, String name) {
		if(this.assets.containsKey(name)) {
			return;
		}
		
		//EntityAsset asset = new EntityAsset(id, name);
		
	//	this.assets.put(id, asset);
	}

	@Override
	public int getSize() {
		if(this.assets == null) {
			return 0;
		}
		
		return this.assets.size();
	}

	@Override
	public EntityAsset get(String id) {
		return this.assets.get(id);
	}
	
	@Override
	public EntityAsset[] all() {
		return this.assets.values().toArray(new EntityAsset[] { });
	}
}

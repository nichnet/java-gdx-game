package com.engine.assets;

import org.json.simple.JSONObject;

public abstract class ResourceManagerBase {
	
	public abstract void load();
	
	public abstract int getSize();
	
	@SuppressWarnings("unchecked")
	protected final <T> T getProperty(JSONObject obj, String name) {
		if(!hasProperty(obj, name)) {
			return null;
		}
		
		return (T) obj.get(name);
	}
	
	protected final boolean hasProperty(JSONObject obj, String name) {
		if(obj == null) {
			return false;
		}
		
		if(name.isEmpty()) {
			return false;
		}
		
		return obj.containsKey(name);
	}
}

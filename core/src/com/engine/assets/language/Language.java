package com.engine.assets.language;

import java.util.HashMap;
import java.util.Map;

public class Language {

	private String id;
	private String name;
	private HashMap<String, String> values;
	
	public Language(String id, String name) {
		this.id = id;
		this.name = name;
		this.values = new HashMap<String, String>();
	}
	
	public void put(String key, String value) { 
		if(this.values.containsKey(key)) {
			return;
		}
		
		this.values.put(key, value);
	}
	
	public String get(String name) {
		if(this.values == null || this.values.isEmpty() || !this.values.containsKey(name)) {
			return String.format("%s_%s", this.getId(), name);
		}
		
		return this.values.get(name);
	}
	
	public String getId() { 
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean contains(String key) {
		return this.values.containsKey(key);
	}
	
	@Override
	public String toString() {
		String out = String.format("language: %s, name: %s\n", this.getId(), this.getName());
		
		for(Map.Entry<String, String> data : this.values.entrySet()) { 
			out += String.format("\t%s:%s\n",data.getKey(), data.getValue());
		}
		
		return out;
	}
	
}

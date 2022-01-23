package com.engine.assets;

public abstract class ResourceBase {
	
	private String name;

	public ResourceBase() {}
	
	public ResourceBase(String name) {
		this.name = name;
	}
	
	public String getName() { 
		return this.name;
	}
}

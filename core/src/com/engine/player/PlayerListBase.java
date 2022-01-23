package com.engine.player;

import java.util.ArrayList;
import java.util.List;

public abstract class PlayerListBase {

	private List<String> users;
	
	
	public PlayerListBase() {
		this.users = new ArrayList<>();
	}
	
	public String[] getAll() {
		return this.users.toArray(new String[] {});
	}
	
	public void remove(String id) {
		this.users.remove(id);
	}
	
	public void put(String id) {
		if(this.users.contains(id)) {
			return;
		}
		
		if(getSize() >= getMaxSize()) {
			return;
		}
		
		this.users.add(id);
	}
	
	public abstract int getMaxSize();

	public int getSize() {
		return this.users.size();
	}
}

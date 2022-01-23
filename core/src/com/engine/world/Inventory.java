package com.engine.world;

public class Inventory {

	private int rows;
	private int columns;
	
//TODO list	private InventoryItem 
	
	public Inventory(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
	}
	
	public void put(String id, int quantity) {
		
	}
	
	public void remove(String id, int quantity) { 
		
	}
	
	public int getInventorySize() {
		return this.rows * this.columns;
	}
	
	
}

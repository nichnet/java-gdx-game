package com.engine.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.engine.assets.graphics.Vector;

public class UIManager {

	private static UIManager instance;
	
	private HashMap<String, Component> components;
	
	public UIManager() {
		components = new HashMap<>();
	}
	
	public void create(Component component) { 
		//TODO Panel components should have nested components.. eg buttons.
		
		components.put(component.getName(), component);
		//TODO hasComponent may not work if you can open multiple of the same components? consider using an ID? but then how will you acces it.	
	}
	
	private boolean hasComponent(String name) {
		return components.containsKey(name);	
	}
	
	public void show(String name) {
		if(!hasComponent(name)) {
			return;
		}
		
		components.get(name).show();
	}
	
	//TODO may need a close method too for UI.
	public void hide(String name) {
		if(!hasComponent(name)) {
			return;
		}
		
		components.get(name).hide();
	}
	
	public static UIManager getInstance() {
		if(instance == null) {
			instance = new UIManager();
		}
		
		return instance;
	}

	public Component[] getRenderableComponents() {
		List<Component> renderable = new ArrayList<>();
		
		for(Component component : components.values()) {
			if(component.isVisible()) {
				renderable.add(component);
			}
		}
		
		return renderable.toArray(new Component[] {});
	}
	
	public Vector getPointerPosition() {
		return new Vector(Gdx.input.getX(), Gdx.input.getY());
	}
	
	public boolean isPointerOverUI() {
		for(Component component : components.values()) {
			if(!component.isVisible()) {
				continue;
			}
			
			return getPointerPosition().getX() >= component.getX() && 
					getPointerPosition().getX() <= component.getWidth() && 
					getPointerPosition().getY() >= component.getY() && 
					getPointerPosition().getY() <= component.getHeight();
		}
		
		
		return false;
	}
}

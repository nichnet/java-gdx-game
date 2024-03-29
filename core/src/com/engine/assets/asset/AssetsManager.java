package com.engine.assets.asset;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.engine.assets.ResourceManagerBase;
import com.engine.util.Logger;

public class AssetsManager extends ResourceManagerBase {
	
	private static AssetsManager instance;
	
	private ObjectAssetManager objectAssetManager;
	private TileAssetManager tileAssetManager;
	private ItemAssetManager itemAssetManager;
	private EntityAssetManager entityAssetManager;
	private LivingEntityAssetManager livingEntityAssetManager;
	
	public void load() {
		//objects
		Logger.log("Loading Objects...");
		objectAssetManager = new ObjectAssetManager();
		Logger.log(String.format("Loaded %d objects", objectAssetManager.getSize()));
		
		//tiles
		Logger.log("Loading Tiles...");
		tileAssetManager = new TileAssetManager();
		Logger.log(String.format("Loaded %d Tiles", tileAssetManager.getSize()));
		
		//items
		Logger.log("Loading Items...");
		itemAssetManager = new ItemAssetManager();
		Logger.log(String.format("Loaded %d Items", itemAssetManager.getSize()));
		
		//entities
		Logger.log("Loading Entities...");
		entityAssetManager = new EntityAssetManager();
		Logger.log(String.format("Loaded %d Entities", entityAssetManager.getSize()));
		
		//living entities
		Logger.log("Loading Living Entities...");
		livingEntityAssetManager = new LivingEntityAssetManager();
		Logger.log(String.format("Loaded %d Living Entities", livingEntityAssetManager.getSize()));
	}

	public static AssetsManager getInstance() {
		if(instance == null) {
			instance = new AssetsManager();
		}
		
		return instance;
	}
	
	public ObjectAssetManager getObjects() {
		return objectAssetManager;
	}	
	
	public TileAssetManager getTiles() {
		return tileAssetManager;
	}	
	
	public ItemAssetManager getItems() {
		return itemAssetManager;
	}
	
	public EntityAssetManager getEntities() {
		return entityAssetManager;
	}
	
	public LivingEntityAssetManager getLivingEntities() {
		return livingEntityAssetManager;
	}


	public int getSize() {
		return getInstance().getObjects().getSize() +
				getInstance().getTiles().getSize() +
				getInstance().getItems().getSize()+
				getInstance().getEntities().getSize() +
				getInstance().getLivingEntities().getSize();
	}

	public InputStream getFileFromResourceAsStream(String path) {
		/*
		ClassLoader loader = AssetsManager.class.getClassLoader();

		if(loader == null) {
			return null;
		}
		
		InputStream in = loader.getResourceAsStream(path);
		
		if(in == null) {
			return null;
		}
		      
		return in;*/
		return null;//TODO
	}
	
	public String[] getResourceFolderFiles (String path, String filter) {
		FileHandle dirHandle;
	   
		dirHandle = Gdx.files.internal(path);
	    
		FileHandle[] files = dirHandle.list();
		List<String> filePaths = new ArrayList<>();
		
		for(int i = 0; i < files.length; i++) {
			FileHandle file = files[i];
			
			if(file.isDirectory()) {
				//recursively enter the subfolder and add the paths to this list
				Collections.addAll(filePaths, getResourceFolderFiles(file.path(), ""));
			} else {
				filePaths.add(file.path());				
			}
		}
		
		return filePaths.toArray(new String[] {});
	}
}

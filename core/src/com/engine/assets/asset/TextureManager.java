package com.engine.assets.asset;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.engine.util.Logger;

public class TextureManager {

	private static TextureManager instance;
	private HashMap<String, Texture> textures;
	
	public TextureManager() {
		textures = new HashMap<>();
	}
	
	public void load() {
		this.textures = new HashMap<String, Texture>();
		
		String path = "assets/sprites";
		
		String[] files = AssetsManager.getInstance().getResourceFolderFiles(path, ".json");
		
		if(files == null || files.length == 0) {
			Logger.warning(String.format("No textures found in: \"%s\".", path));
			return;
		}
		
		for(String file : files) {
			FileHandle fileHandle = Gdx.files.internal(file);	
			String fileName = fileHandle.nameWithoutExtension();
		
			/*
			InputStream in = fileHandle.read();
			
			if(in == null) {
				Logger.error(String.format("Failed to texture resource: \"%s\".", file));
				continue;
			}*/
			

			this.textures.put(fileName, new Texture(file));
//			in.close();
		}
	}
	
	public Texture get(String name) {
		
		if(textures == null || textures.size() == 0 || !(textures.containsKey(name))) {
			return textures.get("missing");
		}
		
		return textures.get(name);
	}
	

	public static TextureManager getInstance() {
		if(instance == null) {
			instance = new TextureManager();
		}
		
		return instance;
	}

	public int getSize() {	
		if(textures == null) {
			return 0;
		}
		
		return textures.size();
	}
	
	public void disposeTextures() {
		for(Texture texture : textures.values()) {
			texture.dispose();
		}
	}
}

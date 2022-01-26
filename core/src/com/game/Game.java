package com.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.TimeUtils;
import com.engine.renderer.Camera;
import com.engine.renderer.Renderer;
import com.engine.world.World;

import com.engine.assets.asset.AssetsManager;
import com.engine.assets.asset.TextureManager;
import com.engine.assets.language.LanguageManager;
import com.engine.assets.sounds.SoundsManager;
import com.engine.input.InputManager;
import com.engine.physics.Physics;
import com.engine.util.Constants;
import com.engine.util.Logger;
import com.engine.util.Settings;

public class Game extends ApplicationAdapter {
	
	private static Game instance;
	private Camera camera;
	private World world;
	private boolean isPaused = false;
	private boolean debugMode = false;
	
	public World getCurrentWorld() { 
		return world;
	}
	@Override
	public void create () {     
		instance = this;
		
		preloadAssets();
		
		//create new renderer
		Renderer.getInstance();
		camera = new Camera();
		world = new World("world", 200, 200);
		
		createGameThreads();
	}
	
	private long lastTick = -1;
	
	public long getLastTick() {
		return lastTick;
	}
	
	private void createGameThreads() {
		//game ticking thread
		ThreadManager.getInstance().addThread(new Runnable() {	
			@Override
			public void run() {
				while(true) {
					if(Game.getInstance().canTick()) {
						lastTick = TimeUtils.millis();
						getCurrentWorld().tickWorld();
					}
				}
			}
		});
		
		ThreadManager.getInstance().addThread(new Runnable() {	
			@Override
			public void run() {
				while(true) {
					Physics.getInstance().tick();
					if(Game.getInstance().canTick()) {
						
					}
				}
			}
		});
		
	}
	
	
	
	private void preloadAssets() {
		Logger.log("Preloading...");
		//settings
		Logger.log("Loading Settings...");
		Settings.getInstance().load();
		Logger.log("Loaded Settings.");
		
		//languages
		Logger.log("Loading Languages...");
		LanguageManager.getInstance().load();
		Logger.log(String.format("Loaded %d Language Packs.", LanguageManager.getInstance().getSize()));

		//load textures
		Logger.log("Loading Textures...");
		TextureManager.getInstance().load();
		Logger.log(String.format("Loaded %d Textures.", TextureManager.getInstance().getSize()));

		//sounds
		Logger.log("Loading Sounds...");
		SoundsManager.getInstance().load();
		Logger.log(String.format("Loaded %d Sounds.", SoundsManager.getInstance().getSize()));
		
		//asset data
		Logger.log("Loading Assets...");
		AssetsManager.getInstance().load();
		Logger.log(String.format("Loaded %d Assets.", AssetsManager.getInstance().getSize()));
	}
	
	@Override
	public void render () {
		InputManager.getInstance().checkInput();
		Renderer.getInstance().render();
	}
	
	//private boolean disposed = false;
	@Override
	public void dispose () {
	//	disposed = true;
		Logger.log("disposing resources.");
		Renderer.getInstance().dispose();
		TextureManager.getInstance().disposeTextures();
		ThreadManager.getInstance().disposeAll();
	}
	
	public static Game getInstance() {
		return instance;
	}
	
	public boolean isPaused() {
		return isPaused;
	}
	
	public boolean isDebugModeActive() {
		return debugMode;
	}
	
	public void swapPauseState() {
		this.isPaused = !isPaused;
	}
	public void swapDebugMode() {
		this.debugMode = !debugMode;
		Logger.log("debug state:" + debugMode);
	}
	
	public Camera getCamera() {
		return camera;
	}
	
	public boolean canTick() {
		if(isPaused()) {
			return false;
		}
		
		
		if(TimeUtils.timeSinceMillis(lastTick) >= Constants.TICK_DELAY) {
			//TODO magic number 60..
			return true;
		}
		
		return false;
	}
}

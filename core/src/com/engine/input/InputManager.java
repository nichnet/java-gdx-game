package com.engine.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.game.Game;

public class InputManager {

	private static InputManager instance;
	
	public void checkInput() {
		//TODO should only be pressed once. This is for "held"
		if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Game.getInstance().swapPauseState();
		}
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.P)) {
			Game.getInstance().swapDebugMode();
		}
		
		if(!Game.getInstance().isPaused()) {
			//in game.
			if(Gdx.input.isKeyPressed(Input.Keys.A)){
	            Game.getInstance().getCurrentWorld().getLocalPlayerCharacter()
	            	.moveWest();
			} else if(Gdx.input.isKeyPressed(Input.Keys.D)){
	            Game.getInstance().getCurrentWorld().getLocalPlayerCharacter()
            	.moveEast();
			}
			
			if(Gdx.input.isKeyPressed(Input.Keys.W)){
	            Game.getInstance().getCurrentWorld().getLocalPlayerCharacter()
	            	.moveNorth();
			} else if(Gdx.input.isKeyPressed(Input.Keys.S)){
	            Game.getInstance().getCurrentWorld().getLocalPlayerCharacter()
            	.moveSouth();
			}
		}
		
	}
	
	public static InputManager getInstance() {
		if(instance == null) {
			instance = new InputManager();
		}
		
		return instance;
	}
}

package com.engine.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.engine.util.Logger;
import com.game.Game;

public class InputManager {

	private static InputManager instance;
	
	public void checkInput() {
		//TODO should only be pressed once. This is for "held"
		if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Logger.log("escape key pressed");
			Game.getInstance().swapPauseState();
		}
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.P)) {
			Logger.log("P key pressed");
			Game.getInstance().swapDebugMode();
		}
		
		if(!Game.getInstance().isPaused()) {
			//in game.
			if(Gdx.input.isKeyPressed(Input.Keys.A)){
				Logger.log("A key down");
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

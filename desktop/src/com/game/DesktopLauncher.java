package com.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.game.Game;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();		
		config.setTitle("Game - ");
		config.setWindowedMode(768, 512);
		config.setForegroundFPS(60);
		new Lwjgl3Application(new Game(), config);
	}
}

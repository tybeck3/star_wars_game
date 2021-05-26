package com.codingdojo.starwarsgame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.codingdojo.starwarsgame.StarWarsGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "GameScreen";
		config.width = 800;
		config.height = 480;
		config.resizable = false;
		new LwjglApplication(new StarWarsGame(), config);
	}
}

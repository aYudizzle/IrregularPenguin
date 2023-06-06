package com.besteleben;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.besteleben.irregularpenguin.GameStart;

/**
 * Starting point of Irregular Penguin
 * This app got developed with libGDX a nice game engine to offer a lot of helpful tools
 * <a href="https://libgdx.com">LibGDX.com</a>
 */
public class DesktopLauncher {
	/** Main Methode die das Desktop Window aufruft */
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(30);
		config.setWindowSizeLimits(800, 600, 800, 600);
		config.setTitle("Irregular Penguin");
		config.setWindowIcon("character/penguin/walk_01.png");
		config.useVsync(true);
		GameStart myApplicationListener = new GameStart();
		new Lwjgl3Application(myApplicationListener, config);
	}
}

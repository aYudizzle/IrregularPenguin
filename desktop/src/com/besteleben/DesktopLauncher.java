package com.besteleben;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.besteleben.irregularpenguin.IrregularPenguinGame;

/**
 * Starting point of Irregular Penguin
 * This app got developed with libGDX a nice game engine to offer a lot of helpful tools
 * <a href="https://libgdx.com">LibGDX.com</a>
 */
public class DesktopLauncher {
	/**
	 * Main Methode die das Desktop Window aufruft
	 *
	 * @param arg for starting arguments those are not needed
	 * */
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

		//configuration for the app window
		config.setForegroundFPS(30);
		config.setWindowSizeLimits(800, 600, 800, 600);
		config.setTitle("Irregular Penguin");
		config.setWindowIcon("character/penguin/walk_01.png");
		config.useVsync(true);
		IrregularPenguinGame myApplicationListener = new IrregularPenguinGame();
		new Lwjgl3Application(myApplicationListener, config);
	}
}

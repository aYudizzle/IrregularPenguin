package com.besteleben;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.besteleben.core.IrregularPenguinGame;

/**
 * Starting point of Irregular Penguin
 * This app got developed with libGDX a nice game engine to offer a lot of helpful tools
 * <a href="https://libgdx.com">LibGDX.com</a>
 * <p>
 * This App is using LibGDX engine where I use several classes as super class
 * I'm extending the Game class in the IrregularPenguinGame class
 * IngameCharacter extends Actor.
 * MathUtils from libGDX is getting used for a random generator in VocabularyService.
 * KeyboardInputProcessor implements InputProcessor to process the keyboard inputs
 * GameStage extends Stage where all the actors are handled.
 * <p>
 * Few times a TextField and Label got used from libGDX to give the user the possibilities to give user input.
 * <p>
 * Beside of that I'm using slf4j and qos.logback for error logging.
 * <p>
 * test login:
 * user: alfa
 * password: alfa1234
 */
public class DesktopLauncher {
	/**
	 * Main Methode die das desktop window aufruft
	 *
	 * @param arg those are not needed
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

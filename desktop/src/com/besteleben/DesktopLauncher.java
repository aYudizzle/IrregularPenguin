package com.besteleben;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.besteleben.core.IrregularPenguinGame;

/**
 * Starting point of Irregular Penguin
 * This app got developed with libGDX a nice game engine to offer a lot of helpful tools
 * <a href="https://libgdx.com">LibGDX.com</a>
 *
 * This App is using LibGDX engine where i use several classes as super class
 * I'm extending the Game class in the IrregularPenguinGame class
 * IngameCharacter extends Actor.
 * MathUtils from libGDX is getting used for a random generator in VocabularyService.
 * KeyboardInputProcessor implements InputProcessor to process the keyboard inputs
 * GameStage extends Stage where all the actors are handled.
 *
 * SettingsDialogBox extends Dialog this makes use of the Dialog Class, so it can popup a nicely dialog to setup player settings.
 * Few times a Textfield and Label got used from libGDX to give possibilities to type some different things and give user input.
 *
 * But beside of all the classes used from LibGDX, I also use GSON from google for json data and apache-client for a http request to receive data from
 * a web api
 */
public class DesktopLauncher {
	/**
	 * Main Methode die das desktop window aufruft
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

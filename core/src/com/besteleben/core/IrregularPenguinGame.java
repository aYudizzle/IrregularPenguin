package com.besteleben.core;

import com.badlogic.gdx.Game;
import com.besteleben.feature_irregularpenguin.screen.gamescreen.GameScreen;
import com.besteleben.feature_login.screen.LoginScreen;
import org.apache.commons.logging.Log;

/**
 * Klasse die den Einstieg in das Spiel darstellt,
 * hier wird der erste Screen festgesetzt und kann ggf. weitere Einstellungen gemacht werden.
 * Es soll auch nur ein game Fenster geben, daher kommt hier das Singleton Pattern zu tragen.
 */

public class IrregularPenguinGame extends Game {
	/**
	 * reference for the screen manager who switchs between different screens
	 */
	private ScreenManager screenManager;

	/** Constructor der den ersten Screen laedt. */
	@Override
	public void create () {
		screenManager = new ScreenManager(this);

		screenManager.addScreen("login", new LoginScreen(screenManager));
		screenManager.addScreen("game", new GameScreen(screenManager));
		screenManager.setScreen("login");
	}

	/**
	 * clean up used ressources
	 */
	@Override
	public void dispose() {
		screenManager.dispose();
	}
}

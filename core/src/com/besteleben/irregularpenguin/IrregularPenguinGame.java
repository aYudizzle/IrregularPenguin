package com.besteleben.irregularpenguin;

import com.badlogic.gdx.Game;
import com.besteleben.irregularpenguin.screen.gamescreen.GameScreen;

/**
 * Klasse die den Einstieg in das Spiel darstellt,
 * hier wird der erste Screen festgesetzt und kann ggf. weitere Einstellungen gemacht werden.
 * Es soll auch nur ein game Fenster geben, daher kommt hier das Singleton Pattern zu tragen.
 */

public class IrregularPenguinGame extends Game {
	/** Constructor der den ersten Screen laedt. */
	@Override
	public void create () {
		setScreen(new GameScreen());
	}
}

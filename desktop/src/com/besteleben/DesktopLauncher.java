package com.besteleben;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.besteleben.irregularpenguin.GameStart;

/**
 * Einstiegspunkt in unser kleines Spiel Irregular Penguin
 */
public class DesktopLauncher {
	/** Main Methode die das Desktop Window aufruft */
	public static void main (String[] arg) {
//		TexturePacker.Settings settings = new TexturePacker.Settings();
//		settings.maxHeight = 4096;
//		settings.maxWidth = 4096;
//		settings.edgePadding = true;
//		settings.duplicatePadding = true;
//		settings.filterMin =  Texture.TextureFilter.Nearest;
//		settings.filterMag = Texture.TextureFilter.Nearest;
//		TexturePacker.process(settings, "assets/character/questioner/", "assets/","questioner");

		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(15);
		config.setWindowSizeLimits(800, 600, 800, 600);
		config.setTitle("Irregular Penguin");
		config.setWindowIcon("character/penguin/walk_01.png");
		config.useVsync(true);
		new Lwjgl3Application(new GameStart(), config);
	}
}

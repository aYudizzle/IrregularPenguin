package com.besteleben.feature_irregularpenguin.screen.gamescreen.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * ResourceManager zum Erstellen des Skins und der benoetigten Bitmap Fonts
 * dieser sollte auch nur einmal erstellt werden koennen und nicht mehrfach instanziiert werden (Da dies sehr ressourcen hungrig ist).
 * Demnach kommt hier das Singleton Pattern zur Anwendung, um dies sicherzustellen.
 */
public class ResourceManager {
    /**
     * Beinhaltet die einzige instanz referenz eines Resourcemanagers
     */
    private static ResourceManager resourceManager;
    /**
     * FreeTypeFontGenerator das Attribut für die generierung einer Bitmap Font aus einem True Type Font
     */
    private FreeTypeFontGenerator fontGenerator;
    /**
     * beinhaltet alle Angaben für die grafischen Komponenten (Textfield etc.)
     */
    private Skin skin;

    /**
     * Creates a Resource Manager and a Skin for the application.
     */
    private ResourceManager() {
        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("PressStart2P-Regular.ttf"));
        // Erstelle den Skin
        skin = new Skin();
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 12;
        parameter.color = Color.BLACK;
        BitmapFont font = fontGenerator.generateFont(parameter);
        skin.add("PressStart2P-Regular-12", font);
        parameter.size = 24;
        font = fontGenerator.generateFont(parameter);
        skin.add("PressStart2P-Regular", font);
        skin.addRegions(new TextureAtlas("irregularpenguin.atlas"));
        skin.load(Gdx.files.internal("irregularpenguin.json"));
    }

    /**
     * Methode um die instanz zu erhalten oder den ResourceManager zu erstellen
     *
     * @return returns the reference to the instance of the resourceManager
     */
    public static ResourceManager getResourceManager() {
        if (resourceManager == null) {
            resourceManager = new ResourceManager();
        }
        return resourceManager;
    }

    /**
     * free up the resources when the app gets closed
     * Called when a screen should release all resources
     */
    public void dispose() {
        skin.dispose();
        fontGenerator.dispose();
    }

    /**
     * Gets skin.
     *
     * @return value of skin
     */
    public Skin getSkin() {
        return skin;
    }
}

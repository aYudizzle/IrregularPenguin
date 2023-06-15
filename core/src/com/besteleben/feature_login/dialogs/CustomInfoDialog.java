package com.besteleben.feature_login.dialogs;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * A custom information dialog with a custom text and two buttons
 */
public class CustomInfoDialog extends Dialog {
    /**
     * one of the two buttons - on the left of the second button
     */
    private TextButton leftButton;
    /**
     * the second button - on the right of the first button
     */
    private TextButton rightButton;
    /**
     * text which should get displayed
     */
    private Label textToShow;

    /**
     * Constructor for Custom Info Dialog
     *
     * @param title              title of the dialog
     * @param skin               resource reference for the skin
     * @param text               text which should get displayed in the dialog
     * @param leftButtonCaption  caption of the left button
     * @param rightButtonCaption caption of the right button
     */
    public CustomInfoDialog(String title, Skin skin, String text, String leftButtonCaption, String rightButtonCaption) {
        super(title, skin);
        init(text, leftButtonCaption, rightButtonCaption);
    }

    /**
     * init method to initialize the custom dialog
     *
     * @param text               text which should get displayed in the dialog
     * @param leftButtonCaption  caption of the left button
     * @param rightButtonCaption caption of the right button
     */
    private void init(String text, String leftButtonCaption, String rightButtonCaption) {

        setBackground(new TextureRegionDrawable(new Texture("dialog/dialogbg.png")));
        setModal(true);
        textToShow = new Label(text, getSkin().get("login-label-style", Label.LabelStyle.class));
        text(textToShow);
        getContentTable().padLeft(20).padRight(20);
        // on esc close dialog
        key(Input.Keys.ESCAPE, false);

        // Füge die Buttons zu einer Button-Tabelle hinzu um diese besser 'adjusten' zu koennen.
        Table buttonTable = new Table();
        buttonTable.defaults().padRight(10);

        leftButton = new TextButton(leftButtonCaption, getSkin());
        rightButton = new TextButton(rightButtonCaption, getSkin());

        buttonTable.add(leftButton);
        buttonTable.add().width(10);
        buttonTable.add(rightButton);

        buttonTable.add(leftButton).padRight(10);
        buttonTable.add().width(10);
        buttonTable.add(rightButton);

        getContentTable().row();
        getContentTable().add(buttonTable).colspan(2);
    }

    /**
     * Gets leftButton.
     *
     * @return value of leftButton
     */
    public TextButton getLeftButton() {
        return leftButton;
    }

    /**
     * Gets rightButton.
     *
     * @return value of rightButton
     */
    public TextButton getRightButton() {
        return rightButton;
    }

    /**
     * Gets textToShow.
     *
     * @return value of textToShow
     */
    public Label getTextToShow() {
        return textToShow;
    }
}

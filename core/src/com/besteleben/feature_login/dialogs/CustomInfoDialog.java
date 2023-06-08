package com.besteleben.feature_login.dialogs;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class CustomInfoDialog extends Dialog {
    private TextButton leftButton;
    private TextButton rightButton;
    private Label textToShow;

    public CustomInfoDialog(String title, Skin skin, String text, String leftButtonCaption, String rightButtonCaption) {
        super(title, skin);
        initialize(text, leftButtonCaption, rightButtonCaption);
    }

    private void initialize(String text, String leftButtonCaption, String rightButtonCaption) {

        setBackground(new TextureRegionDrawable(new Texture("dialog/dialogbg.png")));
        setModal(true);
        textToShow = new Label(text, getSkin().get("login-label-style", Label.LabelStyle.class));
        text(textToShow);
        getContentTable().padLeft(20).padRight(20);
        // on esc close dialog
        key(Input.Keys.ESCAPE, false);

        // Füge die Buttons zu einer Button-Tabelle hinzu um diese besser adjusten zu koennen.
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

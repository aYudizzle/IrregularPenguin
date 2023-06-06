package com.besteleben.irregularpenguin.input.dialogs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

/**
 * this is a dialog which should appear at the end
 * when the player reach 0 healthpoints.
 * it shall enable a chance to enter a playername for a possible highscore entry
 */
public class SettingsDialogBox extends Dialog {
    /**
     * reference to the playerNameTextfield to enter the player name
     */
    private final TextField playerNameField;
    /**
     * Lable of the dialog
     */
    private final Label nameLabel;
    /**
     * keeps the entered playername
     */
    private String playerName;
    /**
     * Save Button
     */
    private final TextButton saveButton;

    public SettingsDialogBox(String title, Skin skin) {
        super(title, skin);
        setModal(true);

        setBackground(new TextureRegionDrawable(new Texture("dialog/dialogbg.png")));


        nameLabel = new Label("Your player name:", skin,"dialog-label-style");
        playerNameField = new TextField("", skin,"dialogTextfield");


        getContentTable().padTop(10f).add(nameLabel).row();
        getContentTable().add(playerNameField).width(200f).padLeft(20f);

        saveButton = new TextButton("Save",skin);
        getButtonTable().add(saveButton);
        getButtonTable().padRight(20f).padBottom(10f).align(Align.bottomRight);

        setSize(298, 98);
    }

    /**
     * Called when a button is clicked. The dialog will be hidden after this method returns unless {@link #cancel()} is called.
     *
     * @param object The object specified when the button was added.
     */
    @Override
    protected void result(Object object) {
        if((boolean) object){
            playerName = playerNameField.getText();
        } else {
            playerName = null;
        }
    }

    /**
     * Gets saveButton.
     *
     * @return value of saveButton
     */
    public TextButton getSaveButton() {
        return saveButton;
    }

    /**
     * Sets playerName.
     *
     * @param playerName value of playerName
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
        this.playerNameField.setText(playerName);
    }

    /**
     * Gets playerNameField.
     *
     * @return value of playerNameField
     */
    public TextField getPlayerNameField() {
        return playerNameField;
    }
}

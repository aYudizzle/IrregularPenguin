package com.besteleben.feature_irregularpenguin.dialogs;

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
     * Save Button
     */
    private final TextButton saveButton;

    /**
     * Constructor for the Settings Dialog Box
     * @param title title of the DialogBox
     * @param skin skin coming from the resourcemanager
     */
    public SettingsDialogBox(String title, Skin skin) {
        super(title, skin);
        //deactivates everyother userinput as long as the dialog box is shown
        setModal(true);
        setBackground(new TextureRegionDrawable(new Texture("dialog/dialogbg.png")));

        nameLabel = new Label("Your player name:", skin,"dialog-label-style");
        playerNameField = new TextField("", skin,"dialogTextfield");
        saveButton = new TextButton("Save",skin);

        buildDialogBox();



    }

    /**
     * method to build the dialog content table get only called in the constructor
     * to clean up the constructor a little bit.
     */
    private void buildDialogBox() {
        getContentTable().padTop(10f).add(nameLabel).row();
        getContentTable().add(playerNameField).width(200f).padLeft(20f);
        getButtonTable().add(saveButton);
        getButtonTable().padRight(20f).padBottom(10f).align(Align.bottomRight);
        setSize(298, 98);
    }

    /**
     * Gets saveButton.
     * @return reference of saveButton
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
        playerNameField.setText(playerName);
    }

    /**
     * Gets playerNameField.
     *
     * @return reference of playerNameField
     */
    public TextField getPlayerNameField() {
        return playerNameField;
    }
}

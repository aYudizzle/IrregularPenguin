package com.besteleben.feature_login.dialogs;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * This class is creating a Custom Registration Dialog which allows the user to register him/herself
 */
public class CustomRegistrationDialog extends Dialog {
    /**
     * usernamefield for the wanted username
     */
    private TextField usernameField;
    /**
     * passwordfield for the wanted password
     */
    private TextField passwordField;
    /**
     * button to fire off the register event
     */
    private TextButton registerButton;
    /**
     * button to cancel the registration process
     */
    private TextButton cancelButton;
    /**
     * shows the availability of the chosen name.
     */
    private ImageButton checkAvailabilityButton;

    /**
     * the constructor for the registration dialog
     * @param title title of the dialog
     * @param skin resource of the skin file
     * @param leftButtonCaption caption of the left button
     * @param rightButtonCaption caption of the right button
     */
    public CustomRegistrationDialog(String title, Skin skin, String leftButtonCaption, String rightButtonCaption) {
        super(title, skin);

        setBackground(new TextureRegionDrawable(new Texture("dialog/dialogbg.png")));
        // paddding left and right of the content table
        getContentTable().padLeft(20).padRight(20);
        key(Input.Keys.ESCAPE,false);

        init(leftButtonCaption, rightButtonCaption);
    }

    /**
     * init method to create the content of the dialog
     * @param leftButtonCaption caption of the left button
     * @param rightButtonCaption caption of the right button
     */
    private void init(String leftButtonCaption, String rightButtonCaption) {
        Table buttonTable = new Table();

        Label usernameLabel = new Label("Username:", getSkin(),"registration-label-style");
        usernameField = new TextField("", getSkin(),"rtField");
        usernameField.setMessageText("enter username");


        Label passwordLabel = new Label("Password:", getSkin(),"registration-label-style");
        passwordField = new TextField("", getSkin(),"rtField");
        passwordField.setMessageText("choose password");
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');

        Label availabilityLabel = new Label("Username available:",getSkin(),"registration-label-style");
        checkAvailabilityButton = new ImageButton(new TextureRegionDrawable(new Texture("button/dialog/questionmark.png")));

        // add alles in eine formular tabelle für eine optisch besser darstellung.
        Table formularTable = new Table();
        formularTable.add(usernameLabel).right().padLeft(10).padTop(15);
        formularTable.add(usernameField).expandX().fillX().padTop(15).padLeft(10).row();
        formularTable.add(passwordLabel).right().padLeft(10);
        formularTable.add(passwordField).padLeft(10).expandX().fillX().row();
        formularTable.add(availabilityLabel).right().padLeft(10);
        formularTable.add(checkAvailabilityButton).padLeft(10).left();

        registerButton = new TextButton(leftButtonCaption, getSkin());
        registerButton.setVisible(false);
        cancelButton = new TextButton(rightButtonCaption, getSkin());

        // Füge die Elemente zum Dialog hinzu
        getContentTable().add(formularTable).row();
        buttonTable.add(registerButton).padRight(10);
        buttonTable.add().width(10);
        buttonTable.add(cancelButton).padRight(10);
        getContentTable().add();
        getContentTable().add(buttonTable).padBottom(10);
        getContentTable().add();
    }

    /**
     * this method changes the icon in the registration dialog if the name is available or the name is not.
     * @param nameAvailable boolean if the name is available or not available
     */
    public void processAvailability(boolean nameAvailable){
        String imageForButton = nameAvailable ? "check.png" : "cross.png";
        checkAvailabilityButton.getStyle().imageUp = new TextureRegionDrawable(new Texture("button/dialog/"+imageForButton));
        registerButton.setVisible(nameAvailable);
    }

    /**
     * Gets usernameField.
     *
     * @return value of usernameField
     */
    public TextField getUsernameField() {
        return usernameField;
    }

    /**
     * Gets passwordField.
     *
     * @return value of passwordField
     */
    public TextField getPasswordField() {
        return passwordField;
    }

    /**
     * Gets registerButton.
     *
     * @return value of registerButton
     */
    public TextButton getRegisterButton() {
        return registerButton;
    }

    /**
     * Gets cancelButton.
     *
     * @return value of cancelButton
     */
    public TextButton getCancelButton() {
        return cancelButton;
    }

    /**
     * Gets checkAvailabilityButton.
     *
     * @return value of checkAvailabilityButton
     */
    public ImageButton getCheckAvailabilityButton() {
        return checkAvailabilityButton;
    }
}

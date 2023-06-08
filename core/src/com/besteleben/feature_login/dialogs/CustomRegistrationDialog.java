package com.besteleben.feature_login.dialogs;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class CustomRegistrationDialog extends Dialog {

    private TextField usernameField;
    private TextField passwordField;
    private TextButton registerButton;
    private TextButton cancelButton;
    private ImageButton checkAvailabilityButton;

    private String currentCheckAvailabilityButton = "questionmark.png";



    public CustomRegistrationDialog(String title, Skin skin, String text, String leftButtonCaption, String rightButtonCaption) {
        super(title, skin);

        setBackground(new TextureRegionDrawable(new Texture("dialog/dialogbg.png")));
        getContentTable().padLeft(20).padRight(20);
        initialize(text, leftButtonCaption, rightButtonCaption);

        key(Input.Keys.ESCAPE,false);
//        text(text,getSkin().get("login-label-style", Label.LabelStyle.class));

        // Erstelle das Registrierungsformular

    }

    private void initialize(String text, String leftButtonCaption, String rightButtonCaption) {
        Table buttonTable = new Table();
        Table formTable = new Table();



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

        formTable.add(usernameLabel).right().padLeft(10).padTop(15);
        formTable.add(usernameField).expandX().fillX().padTop(15).padLeft(10).row();
        formTable.add(passwordLabel).right().padLeft(10);
        formTable.add(passwordField).padLeft(10).expandX().fillX().row();
        formTable.add(availabilityLabel).right().padLeft(10);
        formTable.add(checkAvailabilityButton).padLeft(10).left();

        registerButton = new TextButton(leftButtonCaption, getSkin());
        registerButton.setVisible(false);
        cancelButton = new TextButton(rightButtonCaption, getSkin());
        // Füge die Elemente zum Dialog hinzu
        getContentTable().add(formTable).row();
        buttonTable.add(registerButton).padRight(10);
        buttonTable.add().width(10);
        buttonTable.add(cancelButton).padRight(10);
        getContentTable().add();
        getContentTable().add(buttonTable).padBottom(10);
        getContentTable().add();
    }

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

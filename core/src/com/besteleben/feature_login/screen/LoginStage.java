package com.besteleben.feature_login.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.besteleben.core.ScreenManager;
import com.besteleben.feature_irregularpenguin.screen.gamescreen.util.ResourceManager;
import com.besteleben.feature_login.dialogs.CustomInfoDialog;
import com.besteleben.feature_login.dialogs.CustomRegistrationDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * LoginStage holding all the actors.
 */
public class LoginStage extends Stage {
    /**
     * list of all actors in the stage
     */
    private List<Actor> loginActors;
    /**
     * a textfield to receive the username input
     */
    private TextField usernameField;
    /**
     * a passwordfield to receive the password input
     */
    private TextField passwordField;
    /**
     * Button to fire the login event
     */
    private ImageButton loginButton;
    /**
     * the resourcemanager with the certain skin file.
     */
    private ResourceManager resourceManager;
    /**
     * to manage the screens and handle screenchanges
     */
    private ScreenManager screenManager;
    /**
     * loginErrorDialog is the dialog to handle login errors like wrong login credentials
     */
    private CustomInfoDialog loginErrorDialog;
    /**
     * if the user did not exist, the registration formular can be used to register yourself.
     */
    private CustomRegistrationDialog registrationFormularDialog;

    /**
     * Creates a stage with a {@link ScalingViewport} set to {@link Scaling#stretch}. The stage will use its own {@link Batch}
     * which will be disposed when the stage is disposed.
     *
     * @param screenManager a reference to the screenmanager to work with it.
     */
    public LoginStage(ScreenManager screenManager) {
        this.screenManager = screenManager;
        loginActors = new ArrayList<>();
        resourceManager = ResourceManager.getResourceManager();

        init();
        buildDialogBoxes();
    }

    /**
     * method to initialize the stage with an image as a background. The formular is getting added to a table
     * and the table is getting added to a container for better placement on the screen.
     */
    private void init() {
        Texture backgroundTexture = new Texture(Gdx.files.internal("login/login_background.png"));
        Image backgroundImage = new Image(backgroundTexture);
        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        addActor(backgroundImage);

        Table loginTable = new Table();
        loginTable.setWidth(410);
        Label titleLabel = new Label("Login", resourceManager.getSkin(), "login-label-style");

        usernameField = new TextField("", resourceManager.getSkin(), "loginField");
        usernameField.setMessageText("Username");

        passwordField = new TextField("", resourceManager.getSkin(), "loginField");
        passwordField.setMessageText("Password");
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');

        loginButton = new ImageButton(new TextureRegionDrawable(new Texture("button/login_buttonup.png")), new TextureRegionDrawable(new Texture("button/login_buttondown.png")));

        loginTable.add(titleLabel).colspan(2).padBottom(20).row();
        loginTable.add(usernameField).width(200).height(40).padBottom(20).row();
        loginTable.add(passwordField).width(200).height(40).padBottom(20).row();
        loginTable.add(loginButton).width(120).height(40).colspan(2).row();
        //container to reposition the login form
        Container<Table> loginContainer = new Container<>(loginTable);
        loginContainer.setPosition(400, 310);
        addActor(loginContainer);
    }

    /**
     * build the dialog boxes in a method instead of the constructor to keep the constructor cleaner
     */
    private void buildDialogBoxes() {
        registrationFormularDialog = new CustomRegistrationDialog("", resourceManager.getSkin(), "register", "cancel");
        loginErrorDialog = new CustomInfoDialog("", resourceManager.getSkin(), "User could not be found.", "register", "ok");
    }

    /**
     * to show the Login Error Dialog with an error message
     *
     * @param errorMessage the message for the error dialog
     */
    public void showLoginErrorDialog(String errorMessage) {
        loginErrorDialog.getTextToShow().setText(errorMessage);
        loginErrorDialog.show(this);
    }

    /**
     * to show the registration failed dialog
     */
    public void showRegistrationFailedDialog() {
        Dialog failedRegistration = new Dialog("", resourceManager.getSkin());
        failedRegistration.setBackground(new TextureRegionDrawable(new Texture("dialog/dialogbg.png")));
        failedRegistration.pad(20);
        failedRegistration.text("Registration failed, please try again.");
        failedRegistration.button("Ok", true);
        failedRegistration.key(Input.Keys.ESCAPE, false);
        failedRegistration.show(this);
    }

    /**
     * to add actors to the actor list and the stage
     */
    @Override
    public void addActor(Actor actor) {
        loginActors.add(actor);
        super.addActor(actor);
    }

    /**
     * draws the stage and all actors with the delta time in seconds
     *
     * @param delta Time in seconds since the last frame.
     */
    @Override
    public void act(float delta) {
        super.act(delta);
        // Aktualisiere alle Actors in der Stage
        for (Actor actor : loginActors) {
            actor.act(delta);
        }
    }

    /**
     * Gets loginButton.
     *
     * @return value of loginButton
     */
    public ImageButton getLoginButton() {
        return loginButton;
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
     * Gets loginDialog.
     *
     * @return value of loginDialog
     */
    public CustomInfoDialog getLoginErrorDialog() {
        return loginErrorDialog;
    }

    /**
     * Gets registrationFormularDialog.
     *
     * @return value of registrationFormularDialog
     */
    public CustomRegistrationDialog getRegistrationFormularDialog() {
        return registrationFormularDialog;
    }
}

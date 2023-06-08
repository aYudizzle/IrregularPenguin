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

public class LoginStage extends Stage {
    private final List<Actor> loginActors;
    private TextField usernameField;
    private TextField passwordField;
    private ImageButton loginButton;

    private final ResourceManager resourceManager;
    private ScreenManager screenManager;
    private CustomInfoDialog loginErrorDialog;
    private CustomRegistrationDialog registrationFormularDialog;

    /**
     * Creates a stage with a {@link ScalingViewport} set to {@link Scaling#stretch}. The stage will use its own {@link Batch}
     * which will be disposed when the stage is disposed.
     */
    public LoginStage(ScreenManager screenManager) {
        this.screenManager = screenManager;
        loginActors = new ArrayList<>();
        resourceManager = ResourceManager.getInstance();

        initUI();
        buildDialogBoxes();
    }

    private void initUI() {
        //Background
        Texture backgroundTexture = new Texture(Gdx.files.internal("login/login_background.png"));
        Image backgroundImage = new Image(backgroundTexture);
        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        addActor(backgroundImage);


        Table loginTable = new Table();
        loginTable.setWidth(410);
        Label titleLabel = new Label("Login",resourceManager.getSkin(),"login-label-style");

        usernameField = new TextField("", resourceManager.getSkin(),"loginField");
        usernameField.setMessageText("Username");

        passwordField = new TextField("", resourceManager.getSkin(),"loginField");
        passwordField.setMessageText("Password");
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');

        loginButton = new ImageButton(new TextureRegionDrawable(new Texture("button/login_buttonup.png")), new TextureRegionDrawable(new Texture("button/login_buttondown.png")));

        loginTable.add(titleLabel).colspan(2).padBottom(20).row();
        loginTable.add(usernameField).width(200).height(40).padBottom(20).row();
        loginTable.add(passwordField).width(200).height(40).padBottom(20).row();
        loginTable.add(loginButton).width(120).height(40).colspan(2).row();
        Container<Table> loginContainer = new Container<>(loginTable);
        loginContainer.setPosition(400,310);
        addActor(loginContainer);

//        addActor(loginButton);
    }


    private void buildDialogBoxes() {
        registrationFormularDialog = new CustomRegistrationDialog("",resourceManager.getSkin(),"Registration.","register","cancel");
        loginErrorDialog = new CustomInfoDialog("",resourceManager.getSkin(), "User could not be found.","register","ok");
    }

    private void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
    }

    public void showLoginErrorDialog(String textToShow) {
        loginErrorDialog.getTextToShow().setText(textToShow);
        loginErrorDialog.show(this);
    }

    public void registrationFailedDialog() {
        Dialog failedRegistration = new Dialog("",resourceManager.getSkin());
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

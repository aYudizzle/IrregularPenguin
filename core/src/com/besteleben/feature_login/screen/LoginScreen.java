package com.besteleben.feature_login.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.besteleben.core.ScreenManager;
import com.besteleben.feature_login.controller.LoginController;
import com.besteleben.feature_login.exceptions.LoginCredentialsWrongException;
import com.besteleben.feature_login.exceptions.UserNotFoundException;
import com.besteleben.feature_login.repository.LoginRepositoryImpl;
import com.besteleben.feature_login.service.LoginService;

/**
 * this is the login screen which creates a stage with all the actors of the stage and the
 * controller to mediate between stage and middletier
 */
public class LoginScreen extends ScreenAdapter {
    /**
     * manages the Actors which are getting shown by the screen
     */
    private LoginStage stage;
    /**
     * LoginController as mediator between stage and service
     */
    private LoginController controller;

    /**
     * screenmanager to handle all screen switches
     */
    private ScreenManager screenManager;


    /**
     * Constructor of the LoginScreen
     * @param screenManager manages the screens of the application
     */
    public LoginScreen(ScreenManager screenManager){
        this.screenManager = screenManager;
        stage = new LoginStage(screenManager);
        controller = new LoginController(new LoginService(new LoginRepositoryImpl()));
        initButtonHandler();
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    /**
     * initialize all the needed button handlers.
     */
    private void initButtonHandler() {
        stage.getLoginButton().addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                String username = stage.getUsernameField().getText();
                String password = stage.getPasswordField().getText();
                try {
                    controller.login(username,password);
                    screenManager.setScreen("game");
                } catch (UserNotFoundException exception) {
                    stage.showLoginErrorDialog("User not found!");
                } catch (LoginCredentialsWrongException exception) {
                    stage.showLoginErrorDialog("Login credentials wrong!");
                }
                return true;
            }
        });
        stage.getLoginErrorDialog().getRightButton().addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                stage.getLoginErrorDialog().hide();
                return true;
            }
        });
        stage.getLoginErrorDialog().getLeftButton().addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                stage.getLoginErrorDialog().hide();
                stage.getRegistrationFormularDialog().show(stage);
                return true;
            }
        });
        stage.getRegistrationFormularDialog().getCheckAvailabilityButton().addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                String wantedName = stage.getRegistrationFormularDialog().getUsernameField().getText();
                boolean nameAvailable = controller.checkUserName(wantedName);
                stage.getRegistrationFormularDialog().processAvailability(nameAvailable);
                return true;
            }
        });
        stage.getRegistrationFormularDialog().getRegisterButton().addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                String username = stage.getRegistrationFormularDialog().getUsernameField().getText();
                String password = stage.getRegistrationFormularDialog().getPasswordField().getText();
                boolean result = controller.registerUser(username,password);
                if(result){
                    stage.getRegistrationFormularDialog().hide();
                }else{
                    stage.showRegistrationFailedDialog();
                }
                return true;
            }
        });
    }

    /**
     * renders the screen with the given delta time
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        ScreenUtils.clear(new Color(186 / 255f, 235 / 255f, 255 / 255f, 1));
        stage.act(delta);
        stage.draw();
    }
}

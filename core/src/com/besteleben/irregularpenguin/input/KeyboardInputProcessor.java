package com.besteleben.irregularpenguin.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.besteleben.irregularpenguin.entities.components.AnswerTextField;

/**
 * KeyboardInputProcessor decides how to handle different types of Keyboard Inputs
 */
public class KeyboardInputProcessor implements InputProcessor {
    /**
     * reference to the answertextfield
     */
    private final AnswerTextField answerTextField;

    /**
     * Constructor for the inputprocessor which handles keyboard inputs
     */
    public KeyboardInputProcessor(AnswerTextField answerTextField) {
        this.answerTextField = answerTextField;
    }

    /**
     * Called when a key was pressed
     *
     * @param keycode one of the constants in {@link com.badlogic.gdx.Input.Keys}
     * @return whether the input was processed
     */
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    /**
     * Called when a key was released
     *
     * @param keycode one of the constants in {@link com.badlogic.gdx.Input.Keys}
     * @return whether the input was processed
     */
    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    /**
     * Called when a key was typed
     *
     * @param character The character
     * @return whether the input was processed
     */
    @Override
    public boolean keyTyped(char character) {
        if (!answerTextField.isDisabled()) {
            // bei der ersten betaetigung des keyboard loesche das textfield erstmal
            if (answerTextField.getText().equals(answerTextField.getTextToDefault()) ||
                    answerTextField.getTextNextRound().contains(answerTextField.getText())) {
                answerTextField.setText("");
            }
            // backspace (entferne einen char)
            if (character == '\b') {
                // Entferne den letzten Buchstaben aus dem Textfeld
                String currentText = answerTextField.getText();
                if (currentText.length() > 0) {
                    currentText = currentText.substring(0, currentText.length() - 1);
                    answerTextField.setText(currentText);
                    return true;
                }
            } else {
                // Füge den eingegebenen Buchstaben zum Textfeld hinzu
                String currentText = answerTextField.getText();
                currentText += character;
                answerTextField.setText(currentText);
                return true;
            }
        }
        return true;
    }

    /**
     * Called when the screen was touched or a mouse button was pressed. The button parameter will be {@link Input.Buttons#LEFT} on iOS.
     *
     * @param screenX The x coordinate, origin is in the upper left corner
     * @param screenY The y coordinate, origin is in the upper left corner
     * @param pointer the pointer for the event.
     * @param button  the button
     * @return whether the input was processed
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    /**
     * Called when a finger was lifted or a mouse button was released. The button parameter will be {@link Input.Buttons#LEFT} on iOS.
     *
     * @param screenX
     * @param screenY
     * @param pointer the pointer for the event.
     * @param button  the button
     * @return whether the input was processed
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    /**
     * Called when a finger or the mouse was dragged.
     *
     * @param screenX
     * @param screenY
     * @param pointer the pointer for the event.
     * @return whether the input was processed
     */
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    /**
     * Called when the mouse was moved without any buttons being pressed. Will not be called on iOS.
     *
     * @param screenX
     * @param screenY
     * @return whether the input was processed
     */
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    /**
     * Called when the mouse wheel was scrolled. Will not be called on iOS.
     *
     * @param amountX the horizontal scroll amount, negative or positive depending on the direction the wheel was scrolled.
     * @param amountY the vertical scroll amount, negative or positive depending on the direction the wheel was scrolled.
     * @return whether the input was processed.
     */
    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}

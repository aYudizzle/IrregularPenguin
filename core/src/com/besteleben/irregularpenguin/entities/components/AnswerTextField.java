package com.besteleben.irregularpenguin.entities.components;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Textfield for the UserInput it also displays a small phrase
 * at the start of every round
 */
public class AnswerTextField extends TextField {
    /**
     * default text for the start of the game
     */
    private final String textToDefault;

    /**
     * list of phrases if the user hits the next round a phrase of this list is getting displayed
     */
    private final List<String> textNextRound = new ArrayList<>();

    /**
     * Constructor for the answertextfield where the user input is getting typed in
     * @param text starting text which is getting displayed at the beginning
     * @param skin reference to the skin out of the resourcemanager
     */
    public AnswerTextField(String text, Skin skin) {
        super("", skin);
        textToDefault = text;
        setWidth(600);
        setHeight(233);
        setPosition((800 - getWidth()) / 2, 10);
        setDisabled(true);
        setMaxLength(35);
        starTypingAnimation(textToDefault);
        setAlignment(Align.center);
        createListOfPhrases();
    }

    /**
     * create some phrases which should be displayed when the next round starts (starting with a typing animation)
     */
    private void createListOfPhrases() {
        textNextRound.add("Do you know this one too?");
        textNextRound.add("Maybe this will be more difficult!");
        textNextRound.add("Huh? You are great");
        textNextRound.add("Come and get your next points.");
        textNextRound.add("Is this gonna be a new highscore?");
    }

    /**
     * types the text with a typing animation.
     * @param textToType text who should be animated and gets typed.
     */
    private void starTypingAnimation(String textToType) {
        Timer.schedule(new Timer.Task() {
            private int currentIndex = 0;
            @Override
            public void run() {
                if (currentIndex < textToType.length()) {
                    setText(getText() + textToType.charAt(currentIndex));
                    currentIndex++;
                } else {
                    cancel();
                }
            }
        }, 0.05f, 0.05f);
    }

    /**
     * Reset method for the next round of the game
     */
    public void reset(){
        Collections.shuffle(textNextRound);
        setText("");
        starTypingAnimation(textNextRound.get(0));
        setDisabled(true);
    }

    /**
     * resets the textfield to its starting values and starts typing the default text
     */
    public void resetForNewGame(){
        setText("");
        starTypingAnimation(textToDefault);
        setDisabled(true);
    }

    /**
     * Gets textToDefault.
     *
     * @return value of textToDefault
     */
    public String getTextToDefault() {
        return textToDefault;
    }

    /**
     * Gets textNextRound.
     *
     * @return value of textNextRound
     */
    public List<String> getTextNextRound() {
        return textNextRound;
    }
}

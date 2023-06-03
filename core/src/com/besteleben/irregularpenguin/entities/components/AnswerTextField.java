package com.besteleben.irregularpenguin.entities.components;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnswerTextField extends TextField {
    private String textToDefault;

    private final List<String> textNextRound = new ArrayList<>();

    public AnswerTextField(String text, Skin skin) {
        super("", skin);
        textToDefault = text;
        setWidth(600);
        setHeight(233);
        setPosition((800 - getWidth()) / 2, 10);
        setDisabled(true);
        setMaxLength(35);
        starTypingAnimation();
        setAlignment(Align.center);
        createListOfPhrases();
//        setAlignment(Center);
    }

    private void createListOfPhrases() {
        textNextRound.add("Do you know this one too?");
        textNextRound.add("Maybe this will be more difficult!");
        textNextRound.add("Huh? You are great");
        textNextRound.add("Come and get your next points.");
        textNextRound.add("Is this gonna be a new highscore?");
    }

    private void starTypingAnimation() {
        Timer.schedule(new Timer.Task() {
            private int currentIndex = 0;

            @Override
            public void run() {
                if (currentIndex < textToDefault.length()) {
                    setText(getText() + textToDefault.charAt(currentIndex));
                    currentIndex++;
                } else {
                    cancel();
                }
            }
        }, 0.1f, 0.1f);
    }

    private void nextRoundTypingAnimation(String nextRoundText) {
        Timer.schedule(new Timer.Task() {
            private int currentIndex = 0;

            @Override
            public void run() {
                if (currentIndex < nextRoundText.length()) {
                    setText(getText() + nextRoundText.charAt(currentIndex));
                    currentIndex++;
                } else {
                    cancel();
                }
            }
        }, 0.1f, 0.1f);

    }

    public void reset(){
        Collections.shuffle(textNextRound);
        setText("");
        nextRoundTypingAnimation(textNextRound.get(0));
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

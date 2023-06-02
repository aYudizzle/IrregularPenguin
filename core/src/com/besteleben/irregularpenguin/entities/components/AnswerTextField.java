package com.besteleben.irregularpenguin.entities.components;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;

public class AnswerTextField extends TextField {
    private String textToDefault;

    public AnswerTextField(String text, Skin skin){
        super("",skin);
        textToDefault = text;
        setWidth(600);
        setHeight(233);
        setPosition((800-getWidth())/2,10 );
        setDisabled(true);
        setMaxLength(25);
        starTypingAnimation();
        setAlignment(Align.center);
//        setAlignment(Center);
    }

    private void starTypingAnimation() {
        Timer.schedule(new Timer.Task() {
            private int currentIndex = 0;

            @Override
            public void run(){
                if(currentIndex < textToDefault.length()) {
                    setText(getText() + textToDefault.charAt(currentIndex));
                    currentIndex++;
                } else {
                    cancel();
                }
            }
        },0.1f,0.1f);
    }

    /**
     * Gets textToDefault.
     *
     * @return value of textToDefault
     */
    public String getTextToDefault() {
        return textToDefault;
    }

}

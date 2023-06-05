package com.besteleben.irregularpenguin.entities.character.questioner;

/**
 * interface for IngameCharacters who should be able to ask Questions.
 */

public interface Questioner {
    /**
     * for displaying the Question
     */
    void settingUpQuestion(String verb);

    /**
     * Processing the answer if right or wrong
     */
    void processRightAnswer();
}

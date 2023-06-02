package com.besteleben.irregularpenguin.entities.character.questioner;

/**
 * interface for IngameCharacters who should be able to ask Questions.
 */

public interface Questioner {
    /**
     * for displaying the Question
     */
    void showQuestion();

    /**
     * Processing the answer if right or wrong
     * @param answer given answer from an user input
     */
    void processAnswer(String answer); //todo kann noch als default erstellt werden und ein standard processing erstellt werden
}

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
     * @param answerRight given answer from an user input right or wrong
     */
    void processRightAnswer(); //todo kann noch als default erstellt werden und ein standard processing erstellt werden
}

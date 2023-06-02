package com.besteleben.irregularpenguin.entities.character.questioner;

public enum QuestionerGhostColor {
    BLUE("blue_idle","blue_blink","infinitive","german"),
    GREEN("green_idle","green_blink","simple_past","german"),
    ORANGE("orange_idle","orange_blink","past_participle", "german"),
    RED("red_idle","red_blink","german","infinitive");

    private final String idleRegionName;
    private final String blinkRegionName;
    private final String questionTerm;
    private final String answerTerm;

    QuestionerGhostColor(String idleRegionName, String blinkRegionName, String questionTerm, String answerTerm){
        this.idleRegionName = idleRegionName;
        this.blinkRegionName = blinkRegionName;
        this.answerTerm = answerTerm;
        this.questionTerm = questionTerm;
    }

    /**
     * Gets idleRegionName.
     *
     * @return value of idleRegionName
     */
    public String getIdleRegionName() {
        return idleRegionName;
    }

    /**
     * Gets blinkRegionName.
     *
     * @return value of blinkRegionName
     */
    public String getBlinkRegionName() {
        return blinkRegionName;
    }

    /**
     * Gets questionTerm.
     *
     * @return value of questionTerm
     */
    public String getQuestionTerm() {
        return questionTerm;
    }

    /**
     * Gets answerTerm.
     *
     * @return value of answerTerm
     */
    public String getAnswerTerm() {
        return answerTerm;
    }
}

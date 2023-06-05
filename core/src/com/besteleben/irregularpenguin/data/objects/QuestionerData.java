package com.besteleben.irregularpenguin.data.objects;

import com.besteleben.irregularpenguin.entities.character.questioner.QuestionerGhostColor;
/** object to transport data from service to controller. */
public class QuestionerData {
    /** color of the ghost and the corresponding textures */
    private QuestionerGhostColor color;
    /** the verb which should be asked for */
    private String verb;
    /** constructor for creating the transfer object */
    public QuestionerData(QuestionerGhostColor color, String verb){
        this.color = color;
        this.verb = verb;
    }

    /**
     * Gets color.
     *
     * @return value of color
     */
    public QuestionerGhostColor getColor() {
        return color;
    }

    /**
     * Gets verb.
     *
     * @return value of verb
     */
    public String getVerb() {
        return verb;
    }

    @Override
    public String toString() {
        return "QuestionerData{" +
                "color=" + color +
                ", verb='" + verb + '\'' +
                '}';
    }
}
package com.besteleben.feature_irregularpenguin.entities.character.questioner;

/**
 * QuestionGhostColor contains all the possible colors of the ghost Textures and their name
 * in the corresponding atlas. Also it contains a requestedForm parameter which is the form the player should get asked for
 */
public enum QuestionerGhostColor {
    /** image atlas entries for the blue ghost */
    BLUE("blue_idle","blue_blink","simple past"),
    /** image atlas entries for the green ghost */
    GREEN("green_idle","green_blink","past participle"),
    /** image atlas entries for the orange ghost */
    ORANGE("orange_idle","orange_blink","german"),
    /** image atlas entries for the Red region */
    RED("red_idle","red_blink", "infinitive"),
    /** image atlas entries for the Dark blue region */
    DARKBLUE("darkblue_idle","darkblue_blink", "simple past"),
    /** image atlas entries for the light blue region */
    LIGHTBLUE("lightblue_idle","lightblue_blink", "past participle"),
    /** image atlas entries for the lightpurple region */
    LIGHTPURPLE("lightpurple_idle","lightpurple_blink","german"),
    /** image atlas entries for the PinkRed region */
    PINKRED("pinkred_idle", "pinkred_blink","infinitive"),
    /** image atlas entries for the purple region */
    PURPLE("purple_idle", "purple_blink","past participle"),
    /** image atlas entries for the turqoise region */
    TURQUOISE("turquoise_idle","turquoise_blink", "german"),
    /** image atlas entries for the yellow region */
    YELLOW("yellow_idle","yellow_blink","infinitive"),
    /** image atlas entries for the pink region */
    PINK("pink_idle","pink_blink","simple past");


    /** keeps a string of the idle animatioon region in the image atlas */
    private final String idleRegionName;
    /** keeps the appearing/disappearing animation region of the image atlas */
    private final String blinkRegionName;
    /** requested form of the wanted answer */
    private final String requestedForm;

    /**
     * Constructor for the QuestionGhostColor
     * @param idleRegionName idle region name in the image atlas
     * @param blinkRegionName blink region mame in the image atlas
     * @param requestedForm the player should get asked for
     */
    QuestionerGhostColor(String idleRegionName, String blinkRegionName, String requestedForm){
        this.idleRegionName = idleRegionName;
        this.blinkRegionName = blinkRegionName;
        this.requestedForm = requestedForm;
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
     * Gets requestedForm.
     *
     * @return value of requestedForm
     */
    public String getRequestedForm() {
        return requestedForm;
    }
}

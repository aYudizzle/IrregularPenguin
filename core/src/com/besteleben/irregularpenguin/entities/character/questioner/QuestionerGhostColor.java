package com.besteleben.irregularpenguin.entities.character.questioner;

public enum QuestionerGhostColor {
    BLUE("blue_idle","blue_blink"),
    GREEN("green_idle","green_blink"),
    ORANGE("orange_idle","orange_blink"),
    RED("red_idle","red_blink");

    private final String idleRegionName;
    private final String blinkRegionName;

    QuestionerGhostColor(String idleRegionName, String blinkRegionName){
        this.idleRegionName = idleRegionName;
        this.blinkRegionName = blinkRegionName;
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

}

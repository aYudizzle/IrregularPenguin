package com.besteleben.feature_irregularpenguin.entities;

/**
 * Player Component for the game representing the tries left for the player
 */
public class Player {
    /** displaying the actual lifes - starting with 3 life per default */
    private int life = 3;
    /** saving the corresponding highscore */
    private int highscore;
    /**
     * PlayerName
     */
    private String playerName;

    /**
     *  Constructor of the player object
     */
    public Player(){
        this.playerName = "unknown";
    }
    /** removing the life if the player didnt get the right answer */
    public void decreaseLife(){
        if(life>0) {
            life--;
        }
    }

    /**
     * resets the player state to starting values
     */
    public void reset(){
        life = 3;
        highscore = 0;
    }

    /**
     * increase the high score by 10 * life
     */
    public void increaseScore(){
        highscore = highscore + life * 10;
    }
    /**
     * Gets life.
     *
     * @return value of life
     */
    public int getLife() {
        return life;
    }

    /**
     * Gets highscore.
     *
     * @return value of highscore
     */
    public int getHighscore() {
        return highscore;
    }

    /**
     * Gets playerName.
     *
     * @return value of playerName
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Sets playerName.
     *
     * @param playerName value of playerName
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}

package com.besteleben.irregularpenguin.entities;

/**
 * Player Component for the game representing the tries left for the player
 */
public class Player {
    /** displaying the actual lifes - starting with 3 life per default */
    private int life = 3;
    /** saving the corresponding highscore */
    private int highscore;
    /** Constructor */
    public Player(){

    }
    /** removing the life if the player didnt get the right answer */
    public void decreaseLife(){
        if(life>0) {
            life--;
        }
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
}

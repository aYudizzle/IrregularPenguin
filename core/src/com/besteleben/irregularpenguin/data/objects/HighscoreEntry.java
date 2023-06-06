package com.besteleben.irregularpenguin.data.objects;

import java.io.Serializable;

/**
 * object for every single entry in the highscore table
 */

public class HighscoreEntry implements Serializable, Comparable<HighscoreEntry> {
    private static final long serialVersionUID = -3012671457440248243L;
    /**
     * playername to save the entry
     */
    private String playerName;

    /**
     * the score of the player
     */
    private Integer playerScore;

    /**
     * constructor for an highscore entry with the name of the player and score
     * @param playerName name of the player
     * @param playerScore scored points by the player in a full round.
     */
    public HighscoreEntry(String playerName, int playerScore) {
        this.playerName = playerName;
        this.playerScore = playerScore;
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

    /**
     * Gets playerScore.
     *
     * @return value of playerScore
     */
    public int getPlayerScore() {
        return playerScore;
    }

    /**
     * Sets playerScore.
     *
     * @param playerScore value of playerScore
     */
    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    /**
     * toString builds a string for debugging purposes
     * @return HighscoreEntry Object as a String
     */

    @Override
    public String toString() {
        return String.format("[Score: %s\t|\tPlayer: %s]", playerScore,playerName);
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure {@link Integer#signum
     * signum}{@code (x.compareTo(y)) == -signum(y.compareTo(x))} for
     * all {@code x} and {@code y}.  (This implies that {@code
     * x.compareTo(y)} must throw an exception if and only if {@code
     * y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code
     * x.compareTo(y)==0} implies that {@code signum(x.compareTo(z))
     * == signum(y.compareTo(z))}, for all {@code z}.
     *
     * @param otherEntry the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     * @apiNote It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     */
    @Override
    public int compareTo(HighscoreEntry otherEntry) {
        return playerScore.compareTo(otherEntry.playerScore);
    }
}

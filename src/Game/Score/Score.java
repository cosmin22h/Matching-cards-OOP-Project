package Game.Score;

import java.io.Serializable;

/**
 * The Score class is used to represents the player score which will be saved.
 */

public class Score implements Serializable {

    // player name and difficulty
    private String playerName, difficultyLevel;
    // score and time
    private int playerScore, playerTime;

    /**
     * The constructor method.
     * @param playerName The player name
     * @param difficultyLevel The difficulty of the game
     * @param playerScore The player score
     * @param playerTime The number of seconds which the player found all the cards
     */
    public Score(String playerName, String difficultyLevel, int playerScore, int playerTime) {
        this.playerName = playerName;
        this.difficultyLevel = difficultyLevel;
        this.playerScore = playerScore;
        this.playerTime = playerTime;
    }

    /**
     * The getter method for the player name.
     * @return String The player name
     */
    public String getPlayerName() {
        return this.playerName;
    }

    /**
     * The getter method for the difficulty of the game.
     * @return String The difficulty level
     */
    public String getDifficultyLevel() {
        return this.difficultyLevel;
    }

    /**
     * The getter method for the player score.
     * @return int The player score
     */
    public int getPlayerScore() {
        return this.playerScore;
    }

    /**
     * The getter method for the number of seconds which the player found all the cards
     * @return int The number of seconds which the player found all the cards
     */
    public int getPlayerTime() {
        return this.playerTime;
    }

}

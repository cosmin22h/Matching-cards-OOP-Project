package Game.Score;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The ScoreTable class is used to represents the score table of the game which will be saved.
 */

public class ScoreTable implements Serializable {

    // player scores
    private ArrayList<Score> playersScore;

    /**
     * The constructor method.
     */
    public ScoreTable() {
        this.playersScore = new ArrayList<Score>();
    }

    /**
     * The getter method for the score table.
     * @return ArrayList A list which contains all the player scores
     */
    public ArrayList<Score> getPlayersScore() {
        return playersScore;
    }

    /**
     * This method add a new score in the score table.
     * @param newPlayerScore The new score
     */
    public void addScore(Score newPlayerScore) {
        this.playersScore.add(newPlayerScore);
    }
}

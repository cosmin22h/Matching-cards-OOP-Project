package Game.Score;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.*;

/**
 * The ScoreSerialization class is used to store the scores of the players.
 */

public class ScoreSerialization {

    // Constant - name of the ser file
    private final static String SER_FILE_NAME = "\\MatchingPokemons.ser";

    // path of the ser file
    private String pathMyDoc;

    /**
     * The constructor method.
     */
    public ScoreSerialization() {
        JFileChooser fr = new JFileChooser();
        FileSystemView fw = fr.getFileSystemView();
        this.pathMyDoc = fw.getDefaultDirectory().toString();
    }

    /**
     * This method is used to score the score table of the game in a ser file.
     * @param scoreTable The score table
     */
    public void serialization(ScoreTable scoreTable) {
        try {
            FileOutputStream file =  new FileOutputStream(this.pathMyDoc  + SER_FILE_NAME);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(scoreTable);
            out.close();
        } catch (IOException e) {}
    }

    /**
     * This method is used to extract the score table from the ser file.
     * @return ScoreTable The score table stored
     */
    public ScoreTable deserialization() {
        ScoreTable scoreTable = null;
        try {
            FileInputStream file = new FileInputStream(this.pathMyDoc  + SER_FILE_NAME);
            ObjectInputStream in = new ObjectInputStream(file);
            scoreTable = (ScoreTable) in.readObject();
            in.close();
        } catch (IOException e1) {
        } catch (ClassNotFoundException e2) {}
        return scoreTable;
    }
}

package GUI;

import Game.Score.Score;
import Game.Score.ScoreTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class HighScores extends JFrame {

    public HighScores(ScoreTable scoreTable) {
        Vector<String> columns = new Vector<String>(2);
        columns.add("Player");
        columns.add("Score");
        columns.add("Time");
        columns.add("Difficulty");
        Vector<Vector<String>> rows = new  Vector<Vector<String>>();
        for (Score scoreData: scoreTable.getPlayersScore()) {
            Vector<String> row = new Vector<String >(2);
            row.add(scoreData.getPlayerName());
            row.add(String.valueOf(scoreData.getPlayerScore()));
            row.add(String.valueOf(scoreData.getPlayerTime()));
            row.add(scoreData.getDifficultyLevel());
            rows.add(row);
        }
        JTable table = new JTable();
        table.setModel(new DefaultTableModel(rows, columns));
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        pack();
        setTitle("High-Scores");
        setVisible(true);
    }
}

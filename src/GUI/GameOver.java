package GUI;

import GUI.Menu.About;
import GUI.Menu.Help;
import GUI.Menu.MenuGame;
import Game.Score.Score;
import Game.Score.ScoreSerialization;
import Game.Score.ScoreTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;

/**
 * The GameOver class is used to display the outcome of the game.
 */

public class GameOver extends JFrame implements ActionListener {

    // Constants - width and height for window
    private final static int WINDOW_WIDTH  = 1130;
    private final static int WINDOW_HEIGHT = 750;
    // Constants - bounds for button
    private final static int HIGH_SCORES_X      = 0;
    private final static int HIGH_SCORES_Y      = 650;
    private final static int HIGH_SCORES_WIDTH  = 115;
    private final static int HIGH_SCORES_HEIGHT = 45;
    // Constants - background
    private final static String PATH_BACKGROUND_WIN     = "pokemon/img/win.jpg";
    private final static String PATH_BACKGROUND_DEFEAT = "pokemon/img/gameOver.jpg";

    // Button for open the table with the high scores
    private JButton viewHighScoresBtn;
    // Table with the high scores
    private ScoreTable scoreTable;
    // Variable used for serialization
    private ScoreSerialization scoreSerialization;

    /**
     * The constructor method.
     * @param win Win flag
     * @param timeLeft The time left - used to calculate the player score
     * @param difficulty The difficulty of the game - used to calculate the player score
     */
    public GameOver(boolean win, int timeLeft, int difficulty){
        setTitle(WelcomeToTheGame.GAME_NAME);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Menu bar
        MenuGame menuGame = new MenuGame();
        menuGame.addMenuItem("New Game", this, 0);
        menuGame.addMenuItem("Help", this, 1);
        menuGame.addMenuItem("About", this, 2);
        menuGame.addMenuItem("Exit", this, 3);
        setJMenuBar(menuGame);

        // Button
        this.viewHighScoresBtn = new JButton("High-scores");
        this.viewHighScoresBtn.addActionListener(this);
        this.viewHighScoresBtn.setBounds(HIGH_SCORES_X,HIGH_SCORES_Y, HIGH_SCORES_WIDTH, HIGH_SCORES_HEIGHT);
        add(this.viewHighScoresBtn);

        // Background
        ImageIcon backgroundImgIcon;
        if (win) {
            backgroundImgIcon = new ImageIcon(PATH_BACKGROUND_WIN);
        } else {
            backgroundImgIcon = new ImageIcon(PATH_BACKGROUND_DEFEAT);
        }
        Image backgroundImg = backgroundImgIcon.getImage();
        backgroundImgIcon = new ImageIcon(backgroundImg.getScaledInstance(WINDOW_WIDTH, WINDOW_HEIGHT, Image.SCALE_SMOOTH));
        JLabel background = new JLabel("", backgroundImgIcon, JLabel.CENTER);
        background.setBounds(0,0, WINDOW_WIDTH, WINDOW_HEIGHT);
        add(background);
        setVisible(true);

        // Storage the score
        this.scoreSerialization = new ScoreSerialization();
        this.scoreTable = this.scoreSerialization.deserialization();
        if (this.scoreTable == null) {
            this.scoreTable = new ScoreTable();
        }
        if (win) {
            String playerDiff = "";
            int playerScore = 0;
            if (difficulty == StartGame.TIMER_VERY_EASY) {
                playerDiff = "Very Easy";
                playerScore = timeLeft;
            }
            else if (difficulty == StartGame.TIMER_EASY) {
                playerDiff = "Easy";
                playerScore = timeLeft * 2;
            }
            else if (difficulty == StartGame.TIMER_MEDIUM) {
                playerDiff = "Medium";
                playerScore = timeLeft * 3;
            }
            else if (difficulty == StartGame.TIMER_HARD) {
                playerDiff = "Hard";
                playerScore = timeLeft * 4;
            }
            else if (difficulty == StartGame.TIMER_VERY_HARD) {
                playerDiff = "Very Hard";
                playerScore = timeLeft * 5;
            }
            String playerName = JOptionPane.showInputDialog(new JFrame(), "Congratulations, new Pokemon master!\n" +
                    "Your score is: " + playerScore + " ( "+ String.valueOf(difficulty - timeLeft) + " seconds)\n" +
                    "Please enter your name, so that everyone will know your achievement",
                    "Pika pika pikaaa!", JOptionPane.PLAIN_MESSAGE);
            if (playerName != null) {
                Score newScore = new Score(playerName, playerDiff, playerScore, difficulty - timeLeft);
                scoreTable.addScore(newScore);
                scoreTable.getPlayersScore().sort(Comparator.comparing(Score::getPlayerScore).reversed());
            }
        }
        this.scoreSerialization.serialization(this.scoreTable);
    };

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(viewHighScoresBtn)) {
            new HighScores(this.scoreTable);
        }
        if (e.getActionCommand().equals("New Game")) {
            dispose();
            new WelcomeToTheGame();
        }
        if (e.getActionCommand().equals("Help")) new Help();
        if (e.getActionCommand().equals("About")) new About();
        if (e.getActionCommand().equals("Exit")) System.exit(0);
    }

}

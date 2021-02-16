package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.Menu.About;
import GUI.Menu.Help;
import GUI.Menu.MenuGame;
import Game.GameController;

/**
 * The WelcomeToTheGame class is used to display the menu for choosing the game difficulty.
 */

public class WelcomeToTheGame extends JFrame implements ActionListener {

    // Constant - name of the game
    public final static String GAME_NAME = "Matching pokemons";
    // Constants - width and height for window (not resizable) and the path for the background
    private final static int WINDOW_WIDTH       = 500;
    private final static int WINDOW_HEIGHT      = 730;
    private final static String BACKGROUND_PATH = "pokemon/img/background1.jpg";
    // Constants - bounds for buttons (x, y, width, height)
    private final static int X_VERY_EASY    = 10;
    private final static int X_EASY         = 110;
    private final static int X_MEDIUM       = 200;
    private final static int X_HARD         = 290;
    private final static int X_VERY_HARD    = 380;
    private final static int Y              = 600;
    private final static int WIDTH_BTN      = 80;
    private final static int WIDTH_VERY_BTN = 90;
    private final static int HEIGHT_BTN     = 40;
    // Constants - number of cards for each level of difficulty
    public final static int VERY_EASY = 4;
    public final static int EASY      = 8;
    public final static int MEDIUM    = 10;
    public final static int HARD      = 12;
    public final static int VERY_HARD = 16;

    // Game menu
    private MenuGame menuGame;
    // Buttons - for selecting the difficulty level
    private JButton veryEasyBtn;
    private JButton easyBtn;
    private JButton mediumBtn;
    private JButton hardBtn;
    private JButton veryHardBtn;

    /**
     * The constructor method.
     */
    public WelcomeToTheGame() {
        setTitle(GAME_NAME);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Game menu
        this.menuGame = new MenuGame();
        setJMenuBar(this.menuGame);
        this.menuGame.addMenuItem("Help", this, 0);
        this.menuGame.addMenuItem("About", this, 1);
        this.menuGame.addMenuItem("Exit", this, 2);

        // Buttons
        this.veryEasyBtn = new JButton("Very Easy");
        this.veryEasyBtn.setBounds(X_VERY_EASY, Y, WIDTH_VERY_BTN, HEIGHT_BTN);
        this.veryEasyBtn.addActionListener(this);
        this.easyBtn = new JButton("Easy");
        this.easyBtn.setBounds(X_EASY,Y, WIDTH_BTN, HEIGHT_BTN);
        this.easyBtn.addActionListener(this);
        this.mediumBtn= new JButton("Medium");
        this.mediumBtn.setBounds(X_MEDIUM, Y, WIDTH_BTN, HEIGHT_BTN);
        this. mediumBtn.addActionListener(this);
        this.hardBtn = new JButton("Hard");
        this.hardBtn.setBounds(X_HARD, Y, WIDTH_BTN, HEIGHT_BTN);
        this.hardBtn.addActionListener(this);
        this.veryHardBtn = new JButton("Very Hard");
        this.veryHardBtn.setBounds(X_VERY_HARD, Y, WIDTH_VERY_BTN, HEIGHT_BTN);
        this.veryHardBtn.addActionListener(this);
        add(this.veryEasyBtn);
        add(this.easyBtn);
        add(this.mediumBtn);
        add(this.hardBtn);
        add(this.veryHardBtn);
        // Background
        ImageIcon backgroundImg = new ImageIcon(BACKGROUND_PATH);
        Image backgroundScaled = backgroundImg.getImage().getScaledInstance(WINDOW_WIDTH, WINDOW_HEIGHT, Image.SCALE_SMOOTH);
        backgroundImg = new ImageIcon(backgroundScaled);
        JLabel background = new JLabel("", backgroundImg, JLabel.CENTER);
        background.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        add(background);

        setVisible(true);
    }

    // Method

    /**
     * This method is used to start the game.
     * @param dim The number of the cards which will be displayed
     */
    private void startGame(int dim) {
        dispose();
        GameController gameController = new GameController(this.menuGame, dim);
        gameController.preview();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(veryEasyBtn)) startGame(VERY_EASY);
        else if (e.getSource().equals(easyBtn)) startGame(EASY);
        else if (e.getSource().equals(mediumBtn)) startGame(MEDIUM);
        else if (e.getSource().equals(hardBtn)) startGame(HARD);
        else if (e.getSource().equals(veryHardBtn)) startGame(VERY_HARD);

        if (e.getActionCommand().equals("Help")) new Help();
        if (e.getActionCommand().equals("About")) new About();
        if (e.getActionCommand().equals("Exit")) System.exit(0);
    }
}

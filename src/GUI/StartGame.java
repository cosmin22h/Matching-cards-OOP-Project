package GUI;

import GUI.Menu.MenuGame;
import Game.Cards.Card;
import Game.Cards.CardsController;
import Game.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * The StartGame class is used to display the game.
 */

public class StartGame extends JFrame implements ActionListener {

    // Constants - width and  height for window (not resizable)
    private final static int WINDOW_WIDTH_VERY_EASY   = 400;
    private final static int WINDOW_WIDTH_EASY_MEDIUM = 900;
    private final static int WINDOW_WIDTH_HARD        = 1100;
    private final static int WINDOW_WIDTH_VERY_HARD   = 1400;
    private final static int WINDOW_HEIGHT            = 1000;
    // Constants - number of cards + back face card and path for the cards
    private final static int CARDS_NO      = 17;
    private final static String CARDS_PATH = "pokemon/jpg/pokemon";
    // Constants - width, height and path for the pokeball image
    private final static int WIDTH_POKEBALL   = 40;
    private final static int HEIGHT_POKEBALL  = 40;
    private final static String PATH_POKEBALL = "pokemon/img/pokeball.jpg";
    // Constants - font and font size for timer
    private final static String FONT_TIMER   = "TimesRoman";
    private final static int FONT_SIZE_TIMER = 35;
    // Constants - start value of the timer
    public final static int TIMER_VERY_EASY = 20;
    public final static int TIMER_EASY      = 40;
    public final static int TIMER_MEDIUM    = 70;
    public final static int TIMER_HARD      = 90;
    public final static int TIMER_VERY_HARD = 110;
    // Constants - number of rows and columns
    private final static int ROW            = 4;
    private final static int COLS_VERY_EASY = 2;
    private final static int COLS_EASY      = 4;
    private final static int COLS_MEDIUM    = 5;
    private final static int COLS_HARD      = 6;
    private final static int COLS_VERY_HARD = 8;

    // Game menu
    private MenuGame menuGame;
    // Number of cards
    private int dim;
    // Game controller
    private GameController gameController;
    // Container
    private Container mainContentPane;
    // Label for timer
    private JLabel timerLabel;
    // Cards
    private ArrayList<ImageIcon> cardsImg;
    private ArrayList<Card> cards;

    /**
     * The constructor method.
     * @param menuGame The menu bar of the game
     * @param dim The number of the cards which are displayed
     * @param gameController The game controller
     */
    public StartGame(MenuGame menuGame, int dim, GameController gameController) {
        super(WelcomeToTheGame.GAME_NAME);
        this.dim = dim;
        this.gameController = gameController;
        this.mainContentPane = getContentPane();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Size
        if (dim == WelcomeToTheGame.VERY_EASY) setSize(WINDOW_WIDTH_VERY_EASY, WINDOW_HEIGHT);
        else if (dim == WelcomeToTheGame.EASY || dim == WelcomeToTheGame.MEDIUM) setSize(WINDOW_WIDTH_EASY_MEDIUM, WINDOW_HEIGHT);
        else if (dim == WelcomeToTheGame.HARD) setSize(WINDOW_WIDTH_HARD, WINDOW_HEIGHT);
        else if(dim == WelcomeToTheGame.VERY_HARD) setSize(WINDOW_WIDTH_VERY_HARD, WINDOW_HEIGHT);
        setResizable(false);

        // New menu items
        this.menuGame = menuGame;
        this.menuGame.addMenuItem("Restart", this, 0);
        this.menuGame.addMenuItem("New Game", this, 0);
        setJMenuBar(menuGame);

        // load cards
        this.cardsImg = loadCards();
    }

    /**
     * The getter method for the timer label.
     * @return JLabel The timer label
     */
    public JLabel getTimerLabel() {
        return this.timerLabel;
    }

    /**
     * The getter method for the cards.
     * @return ArrayList<Card> A list which contains the cards
     */
    public ArrayList<Card> getCards() {
        return this.cards;
    }

    /**
     * This method is used to load the faces for the cards
     * @return ArrayList<ImageIcon> A list which contains the faces of the cards
     */
    private ArrayList<ImageIcon> loadCards() {
        ArrayList<ImageIcon> icons = new ArrayList<ImageIcon>(CARDS_NO);
        for (int i = 0; i <= CARDS_NO; i++) {
            String jpgName = CARDS_PATH + i + ".jpg";
            icons.add(i, new ImageIcon(jpgName));
        }
        return icons;
    }

    /**
     * This method is used to randomize the cards.
     * @param dim The number of the cards which are displayed
     * @return int[] An array which contains the IDs of the cards
     */
    private int[] cardsRandomize(int dim) {
        int idCards[] = new int[2 * dim];
        for (int i = 0; i < dim; i++) {
            idCards[2 * i] = i;
            idCards[2* i + 1] = i;
        }
        Random rand = new Random();
        for (int i = 0; i < idCards.length; i++) {
            int r = rand.nextInt(idCards.length);
            int s = idCards[r];
            idCards[r] = idCards[i];
            idCards[i] = s;
        }

        return idCards;
    }

    /**
     * This method is used to make the components of the game.
     * @return JPanel All components of the game (container)
     */
    public JPanel makeCards() {
        // Main panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        add(panel);

        // Timer panel;
        JPanel timerPanel = new JPanel();
        this.timerLabel = new JLabel();
        ImageIcon pokeballImgIcon = new ImageIcon(PATH_POKEBALL);
        Image pokeballImg = pokeballImgIcon.getImage();
        pokeballImgIcon = new ImageIcon(pokeballImg.getScaledInstance(WIDTH_POKEBALL, HEIGHT_POKEBALL, Image.SCALE_SMOOTH));
        timerPanel.add(new JLabel("", pokeballImgIcon, JLabel.LEFT));
        this.timerLabel.setFont(new Font(FONT_TIMER, Font.PLAIN, FONT_SIZE_TIMER));
        timerPanel.add(this.timerLabel);
        timerPanel.setLayout(new BoxLayout(timerPanel, FlowLayout.RIGHT));
        timerPanel.add(new JLabel("", pokeballImgIcon, JLabel.RIGHT));

        // Cards panel
        JPanel cardsPanel = null;
        if (this.dim == WelcomeToTheGame.VERY_EASY) {
            this.timerLabel.setText(String.valueOf(TIMER_VERY_EASY));
            cardsPanel = new JPanel(new GridLayout(ROW,COLS_VERY_EASY));
        }
        else if (this.dim == WelcomeToTheGame.EASY) {
            this.timerLabel.setText(String.valueOf(TIMER_EASY));
            cardsPanel = new JPanel(new GridLayout(ROW, COLS_EASY));
        }
        else if (this.dim == WelcomeToTheGame.MEDIUM) {
            this.timerLabel.setText(String.valueOf(TIMER_MEDIUM));
            cardsPanel = new JPanel(new GridLayout(ROW, COLS_MEDIUM));
        }
        else if (this.dim == WelcomeToTheGame.HARD) {
            this.timerLabel.setText(String.valueOf(TIMER_HARD));
            cardsPanel = new JPanel(new GridLayout(ROW, COLS_HARD));
        }
        else if (this.dim == WelcomeToTheGame.VERY_HARD) {
            this.timerLabel.setText(String.valueOf(TIMER_VERY_HARD));
            cardsPanel = new JPanel(new GridLayout(ROW, COLS_VERY_HARD));
        }
        panel.add(timerPanel);
        panel.add(cardsPanel);

        // Controller
        CardsController cardsController = new CardsController(gameController);
        int idCards[] = cardsRandomize(this.dim);
        ImageIcon backFace = this.cardsImg.get(CARDS_NO - 1);

        // Cards
        this.cards = new ArrayList<Card>(idCards.length);
        for (int i = 0; i < idCards.length; i++) {
            this.cards.add(new Card(this.cardsImg.get(idCards[i]), backFace, idCards[i], cardsController));
            cardsPanel.add(this.cards.get(i));
        }
        return panel;
    }

    // Method - preview the cards

    /**
     * This method is used to preview the cards before the game stars.
     */
    public void preview() {
        this.mainContentPane.removeAll();
        this.mainContentPane.add(makeCards());
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("New Game")) {
            this.gameController.getTimerGameOver().stop();
            dispose();
            new WelcomeToTheGame();
        }
        else if (e.getActionCommand().equals("Restart")) {
            this.gameController.getTimerGameOver().stop();
            makeCards();
            this.gameController.preview();
        }
    }
}

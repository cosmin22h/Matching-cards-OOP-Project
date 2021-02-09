package Game;

import API.GameOver;
import API.Menu.MenuGame;
import API.StartGame;
import API.WelcomeToTheGame;
import Game.Cards.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The GameController class is used to control the game.
 */

public class GameController {

    // Constant - delay used to memorize the cards
    private final static int DELAY_START_GAME = 1500;
    // Constant - delay for the main timer
    private final static int DELAY_TIMER      = 1000;
    // Constant - critical time
    private final static int REMINDER_TIMER   = 5;

    // API game
    private StartGame startGame;
    // number of cards
    private int toWinCounter;
    // timers - main timer, start game timer
    private Timer timerGameOver, timerStartGame;
    // seconds to find all pairs
    private int defeatCounter, defeatTime;

    /**
     * The constructor method.
     * @param menuGame The menu of the game.
     * @param dim The number of the cards which will be displayed
     */
    public GameController(MenuGame menuGame, int dim) {
        this.startGame = new StartGame(menuGame, dim,  this);
        this.toWinCounter = dim;

        // set the time for the main timer
        if (dim == WelcomeToTheGame.VERY_EASY) this.defeatTime = StartGame.TIMER_VERY_EASY;
        else if (dim == WelcomeToTheGame.EASY) this.defeatTime = StartGame.TIMER_EASY;
        else if (dim == WelcomeToTheGame.MEDIUM) this.defeatTime = StartGame.TIMER_MEDIUM;
        else if (dim == WelcomeToTheGame.HARD) this.defeatTime = StartGame.TIMER_HARD;
        else if (dim == WelcomeToTheGame.VERY_HARD) this.defeatTime = StartGame.TIMER_VERY_HARD;

        // start game
        this.timerStartGame = new Timer(DELAY_START_GAME, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
        this.timerStartGame.setRepeats(false);

        this.timerGameOver = null;
    }

    /**
     * The getter method for the main timer.
     * @return Timer The main timer.
     */
    public Timer getTimerGameOver() {
        return this.timerGameOver;
    }

    /**
     * This method is used to preview the cards before the game stars
     * and start the timer for memorize the cards
     */
    public void preview() {
        this.startGame.preview();
        this.timerStartGame.start();
    }

    /**
     * This method is used to start the game and control the main timer.
     */
    public void startGame() {
        this.defeatCounter = this.defeatTime;
        this.timerGameOver = new Timer(DELAY_TIMER, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                defeatCounter--;
                startGame.getTimerLabel().setText(String.format("%02d", defeatCounter));
                // critical time
                if (defeatCounter <= REMINDER_TIMER) {
                    startGame.getTimerLabel().setForeground(Color.RED);
                }
                // defeat
                if (0 == defeatCounter) {
                    startGame.dispose();
                    timerGameOver.stop();
                    new GameOver(false, -1, -1);
                }
            }
        });
        this.timerGameOver.start();
        for (Card card: this.startGame.getCards()) {
            card.turnBackUp();
        }
    }

    /**
     * This method check if the player has won.
     */
    public void isWin() {
        this.toWinCounter--;
        if(this.toWinCounter == 0) {
            this.startGame.dispose();
            this.timerGameOver.stop();
            new GameOver(true, this.defeatCounter, this.defeatTime);
        }
    }

}

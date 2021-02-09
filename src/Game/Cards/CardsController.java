package Game.Cards;

import Game.GameController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * The CardsController class is used to control the matching of the cards.
 */

public class CardsController implements ActionListener {

    // Constant - delay used to turn 2 cards unmatched with the face down
    private final static int TURN_DOWN_DELAY = 500;

    // Turned cards
    private Vector<Card> turnedCards;
    // Timer to turn the cards with the face down
    private Timer turnDownTimer;
    // Game controller
    private GameController gameController;

    /**
     * The constructor method.
     * @param gameController The game controller
     */
    public CardsController(GameController gameController){
        this.turnedCards = new Vector<Card>(2);
        this.turnDownTimer = new Timer(TURN_DOWN_DELAY, this);
        this.turnDownTimer.setRepeats(false);
        this.gameController = gameController;

    }

    /**
     * This method is used to control which cards will remain with face up.
     * @param card The card turned up
     * @return boolean A flag that says if the card will remain with the face up
     */
    public boolean turnUpCard(Card card) {
        if(this.turnedCards.size() < 2) {
            return doAddCard(card);
        }
        return false;
    }

    /**
     * This method is used to check if 2 cards turned up are a match.
     * @param card The card prepared for matching
     * @return boolean A flag that says if the 2 cards are a match
     */
    private boolean doAddCard(Card card) {
        this.turnedCards.add(card);
        if(this.turnedCards.size() == 2) {
            Card otherCard = (Card) this.turnedCards.get(0);
            if(otherCard.getIdCard() == card.getIdCard()) {
                this.turnedCards.clear();
                this.gameController.isWin();
            }
            else {
                this.turnDownTimer.start();
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < this.turnedCards.size(); i++) {
            Card card = (Card) this.turnedCards.get(i);
            card.turnBackUp();
        }
        this.turnedCards.clear();
    }
}

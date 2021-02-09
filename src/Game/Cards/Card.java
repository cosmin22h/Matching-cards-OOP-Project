package Game.Cards;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * The Card class is used to represents the playing card object.
 */

public class Card extends JLabel implements MouseListener {

    // Faces
    private Icon faceCard, backCard;
    // ID + dimensions
    private int idCard, heightCard, widthCard;
    // flags
    private boolean faceUp, clickOnCard;
    // Controller
    private CardsController controller;

    /**
     * The constructor method.
     * @param face The icon for the face of the card - unique
     * @param back The icon for the back of the card - same icon for all the cards
     * @param idCard The ID for the card - unique
     * @param controller The controller of the cards
     */
    public Card(Icon face, Icon back, int idCard, CardsController controller) {
        super(face);
        this.faceCard = face;
        this.backCard = back;
        this.idCard = idCard;
        this.heightCard = this.backCard.getIconHeight();
        this.widthCard = this.backCard.getIconWidth();
        this.faceUp = true;
        this.clickOnCard = false;
        this.controller = controller;
        this.addMouseListener(this);
    }

    /**
     * The getter method for the ID of the card.
     * @return int The ID of the card
     */
    public int getIdCard() {
        return this.idCard;
    }

    /**
     * This method is used to check if the user clicked on the card.
     * @param x The x coordinate where the user clicked
     * @param y The y coordinate where the user clicked
     * @return boolean A flag that says if the user clicked on the card
     */
    public boolean clickOnCard(int x, int y) {
        int X = Math.abs(x - this.getWidth());
        int Y = Math.abs(y - this.getHeight());

        if (X > this.widthCard || Y > this.heightCard) return false;
        return true;
    }

    /**
     * This method is used to turn the card with the face up.
     */
    public void turnFaceUp() {
        if (this.faceUp) return;
        this.faceUp = this.controller.turnUpCard(this);
        if (this.faceUp) {
            this.setIcon(this.faceCard);
        }
    }

    /**
     * This method is used to turn the card with the face down.
     */
    public void turnBackUp() {
        if (this.faceUp) {
            this.setIcon(this.backCard);
            this.faceUp = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (clickOnCard(e.getX(), e.getY())) this.turnFaceUp();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (clickOnCard(e.getX(), e.getY())) this.clickOnCard = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (this.clickOnCard) this.clickOnCard = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {} // IGNORE

    @Override
    public void mouseExited(MouseEvent e) {
        this.clickOnCard = false;
    }
}

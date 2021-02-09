package API.Menu;

import javax.swing.*;
import java.awt.*;

/**
 * The Help class is used to display the game instructions.
 */

public class Help extends JFrame {

    // Constants - width and height for window
    private final static int WINDOW_WIDTH  = 715;
    private final static int WINDOW_HEIGHT = 590;

    public Help() {
        setLayout(null);
        setTitle("Help");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);

        JTextArea objGame = new JTextArea(" Object of the game");
        objGame.setBounds(0, 0, 715, 40);
        objGame.setFont(new Font("TimesRoman", Font.BOLD, 22));
        objGame.setEditable(false);
        add(objGame);

        JTextArea objGameArea = new JTextArea(" The object of the game is to collect all the matching pairs before the time ran out.");
        objGameArea.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        objGameArea.setBounds(0, 40, 715, 40);
        objGameArea.setEditable(false);
        add(objGameArea);

        JTextArea toPlay = new JTextArea(" How to play");
        toPlay.setBounds(0, 80, 715, 40);
        toPlay.setFont(new Font("TimesRoman", Font.BOLD, 22));
        toPlay.setEditable(false);
        add(toPlay);

        JTextArea toPlayArea = new JTextArea(
                " Choose difficulty by right clicking on: 'Very easy', 'Easy', 'Medium', 'Hard' or 'Very hard'.\n" +
                " The cards will appear and you have 3 secounds to memorize them before they will turn\n" +
                " over. Right click on two cards to see if they match. Keep clicking on two cards until you\n" +
                " find all the matches and win. You need do that before the time ran out!\n"
        );
        toPlayArea.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        toPlayArea.setBounds(0, 120, 715, 100);
        toPlayArea.setEditable(false);
        add(toPlayArea);

        JTextArea diff = new JTextArea(" Difficulties");
        diff.setBounds(0, 220, 715, 40);
        diff.setFont(new Font("TimesRoman", Font.BOLD, 22));
        diff.setEditable(false);
        add(diff);

        JTextArea diffArea = new JTextArea(
                " ->'Very easy': find 4 pairs in 20 seconds\n" +
                " ->'Easy': find 8 pairs in 40 seconds\n" +
                " ->'Medium': find 10 pairs in 70 seconds\n" +
                " ->'Hard': find 12 pairs in 90 second\n" +
                " ->'Very hard': find 16 pairs in 110 seconds"
        );
        diffArea.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        diffArea.setBounds(0, 260, 715, 100);
        diffArea.setEditable(false);
        add(diffArea);

        JTextArea need = new JTextArea(" You need:");
        need.setBounds(0, 360, 715, 40);
        need.setFont(new Font("TimesRoman", Font.BOLD, 22));
        need.setEditable(false);
        add(need);

        ImageIcon mouse = new ImageIcon("pokemon/img/mouse.jpg");
        Image img = mouse.getImage();
        Image temp_img = img.getScaledInstance(715, 160, Image.SCALE_SMOOTH);
        mouse = new ImageIcon(temp_img);
        JLabel new_mouse = new JLabel("", mouse, JLabel.CENTER);
        new_mouse.setBounds(0, 400, 715, 160);
        add(new_mouse);

        setVisible(true);
    }
}

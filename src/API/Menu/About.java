package API.Menu;

import java.awt.Font;
import javax.swing.*;

/**
 * The About class is used to display:
 * - the project name
 * - the project author
 * - the last update
 */

public class About extends JFrame{

    // Constants - width and height for window
    private final static int WINDOW_WIDTH  = 550;
    private final static int WINDOW_HEIGHT = 120;

    public About(){
        setTitle("About");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        JTextArea aboutArea = new JTextArea(
                " OOP Project 2019: Matching cards v2.0\n" +
                " @Handaric Cosmin (Technical University of Cluj-Napoca)\n" +
                " Last update: September 19, 2020"
        );
        aboutArea.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        aboutArea.setEditable(false);
        add(aboutArea);
        setVisible(true);
    }
}


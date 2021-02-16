package GUI.Menu;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * The MenuGame class is used to display the menu bar of the game.
 */

public class MenuGame extends JMenuBar {

    // The menu bar
    private JMenu menu;

    // The constructor method
    public MenuGame() {
        super();
        this.menu = new JMenu("Menu");
        add(menu);
    }

    /**
     * This method is used to add a new command to the menu bar.
     * @param cmd The name of the new command
     * @param listener The action of the new command
     * @param index The place in the menu bar of the new command
     */
    public void addMenuItem(String cmd, ActionListener listener, int index) {
        JMenuItem newItem = new JMenuItem(cmd);
        newItem.setActionCommand(cmd);
        newItem.addActionListener(listener);
        menu.add(newItem, index);
    }
}

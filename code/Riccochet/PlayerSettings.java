//Creates Window that is opened by selecting Player Settings on the Main menu and directs user to either Number of Players, difficulty, or Hint Menus
//author Saurav

import java.awt.*;
import javax.swing.*;
import javax.swing.JPanel;
import java.awt.GridLayout;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

//This class controls what happens when the user clicks on the PlayerSettings.
public class PlayerSettings extends JFrame {

    private JPanel Panel = new JPanel();
    int size = 3;

    public PlayerSettings() {

        super("Player Settings");

        GridLayout grid = new GridLayout(size + 1, 0, 0, 15);

        JLabel label1 = new JLabel();

        // Menu screen is set up.
        RicochetRobots.SetUpScreen(this, grid, Panel, label1, "Player Settings", 80);

        PlayerSettingsButtons[] buttons = new PlayerSettingsButtons[size];
        // All buttons are set up in the player settings menu option.
        for (int i = 0; i < size; i++) {
            buttons[i] = new PlayerSettingsButtons();
            Panel.add(buttons[i]);
            buttons[i].setBackground(Color.ORANGE);
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 60));
            switch (i) {

                case 0:

                    buttons[i].setText("Number Of Players");

                    break;

                case 1:
                    buttons[i].setText("Difficulty");
                    break;

                case 2:
                    buttons[i].setText("Hint");
                    break;

            }

        }

        add(Panel);
        setVisible(true);

    }
}
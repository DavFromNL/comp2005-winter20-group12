import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

//This Implements the MenuButton of types start game, load game etc.
public class MenuButtons extends JButton implements ActionListener {

    public MenuButtons() {

        this.addActionListener(this);
    }

    public void actionPerformed(ActionEvent event) {

        String name = event.getActionCommand();

        switch (name) {

            case "Start Game":
                if (Data.GetBoardType() == 1) {
                    new Board(16, 16, false);
                } else {
                    new ComplexBoard(16, 16, false);
                }

                break;

            case "Load Game":

                LoadGame load = new LoadGame();
                load.LoadBoard();
                if (load.IsFilFound()) {
                    if (Data.GetBoardType() == 1) {
                        new Board(16, 16, true);
                    } else {
                        new ComplexBoard(16, 16, true);
                    }
                }

                break;

            case "Board Settings":
                new BoardSettings();
                break;

            case "Player Settings":
                new PlayerSettings();
                break;

            case "Quit Game":
                System.exit(0);
                break;

        }

    }

}

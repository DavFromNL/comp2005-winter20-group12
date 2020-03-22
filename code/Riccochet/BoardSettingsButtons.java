//This is what happens when you click on each button which is of type Board Settings

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class BoardSettingsButtons extends JButton implements ActionListener {

    public BoardSettingsButtons() {

        this.addActionListener(this);

    }

    public void actionPerformed(ActionEvent event) {

        String name = event.getActionCommand();

        switch (name) {

            case "Color Palette":
                new ColorPalette();
                break;

            case "Board Types":
                new BoardTypes();
                break;

        }
    }

}

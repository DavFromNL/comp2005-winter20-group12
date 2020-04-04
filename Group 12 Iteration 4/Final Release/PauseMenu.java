
import java.awt.GridLayout;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PauseMenu extends JFrame {

    int size = 3;
    JPanel Panel = new JPanel();

    public PauseMenu() {

        super("Pause Menu");

        GridLayout grid = new GridLayout(size + 1, 0, 0, 15);

        JLabel label1 = new JLabel();

        // Menu screen is set up.
        RicochetRobots.SetUpScreen(this, grid, Panel, label1, "Paused", 80);

        SaveButton save = new SaveButton();
        JButton Back = new JButton();

        AddButton(save, "Save Game");
        AddButton(Back, "Back");

        Back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {

                dispose();

            }

        });

        add(Panel);
        setVisible(true);

    }

    private void AddButton(JButton button, String text) {

        Panel.add(button);
        button.setBackground(Color.ORANGE);
        button.setFont(new Font("Arial", Font.PLAIN, 60));
        button.setText(text);

    }

}

//  Creates window that allows user to select if they want hints enabled

import java.awt.*;
import javax.swing.*;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Hint extends JFrame {

    private JPanel Panel = new JPanel();
    int size = 6;
    JButton TurnOn;
    JButton TurnOff;

    public Hint() {

        super("Difficulty");

        GridLayout grid = new GridLayout(size + 1, 0, 0, 15);

        JLabel label1 = new JLabel();

        RicochetRobots.SetUpScreen(this, grid, Panel, label1, "Turn On Hints ?", 70);

        TurnOn = new JButton();
        TurnOff = new JButton();

        JLabel label2 = new JLabel();
        JLabel label3 = new JLabel();

        TurnOn.setBackground(Color.ORANGE);
        TurnOn.setFont(new Font("Arial", Font.PLAIN, 60));
        TurnOn.setText("YES");

        TurnOff.setBackground(Color.ORANGE);
        TurnOff.setFont(new Font("Arial", Font.PLAIN, 60));
        TurnOff.setText("NO");

        Panel.add(label1);

        Panel.add(label2);
        Panel.add(TurnOn);
        Panel.add(label3);
        Panel.add(TurnOff);

        TurnOn.addActionListener(new ActionListener() {

            Data data = new Data();

            public void actionPerformed(ActionEvent event) {

                if (event.getSource() == TurnOn) {

                    data.TurnOnHints();
                    JOptionPane.showMessageDialog(null, "Hint is turned on", "Saved", 1);

                }

            }

        });

        TurnOff.addActionListener(new ActionListener() {

            Data data = new Data();

            public void actionPerformed(ActionEvent event) {

                if (event.getSource() == TurnOff) {

                    data.TurnOffHints();
                    JOptionPane.showMessageDialog(null, "Hint is turned off", "Saved", 1);

                }

            }

        });

        add(Panel);
        setVisible(true);

    }

}

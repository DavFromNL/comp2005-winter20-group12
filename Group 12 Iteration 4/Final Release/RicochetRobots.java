import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;

/*
Main class which results in the start of the application.

*/
public class RicochetRobots extends JFrame {

    private enum StateEnum {
        MenuState, GameState
    }

    private JPanel Panel = new JPanel();
    int size = 5;

    public static void main(String[] args) {
        new RicochetRobots();

    }

    public RicochetRobots() {
        super("Ricochet Robots");

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        GridLayout grid = new GridLayout(size + 1, 0);

        JLabel label1 = new JLabel();

        SetUpScreen(this, grid, Panel, label1, "Ricochet Robots", 80);

        // Buttons array of type MenuButtons.
        MenuButtons[] buttons = new MenuButtons[size];

        for (int i = 0; i < size; i++) {

            buttons[i] = new MenuButtons();
            Panel.add(buttons[i]);
            buttons[i].setBackground(Color.ORANGE);
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 40));

            switch (i) {

                case 0:

                    buttons[i].setText("Start Game");

                    break;

                case 1:
                    buttons[i].setText("Load Game");
                    break;

                case 2:
                    buttons[i].setText("Board Settings");
                    break;

                case 3:
                    buttons[i].setText("Player Settings");
                    break;

                case 4:
                    buttons[i].setText("Quit Game");
                    break;

            }

        }

        add(Panel);
        setVisible(true);

    }

    // Sets menu type screen. It is static method so we can access from anywhere
    // without creating instance.
    public static void SetUpScreen(JFrame frame, GridLayout grid, JPanel panel, JLabel label1, String LabelText,
            int fontsize)

    {

        frame.setSize(800, 800);
        frame.setResizable(false);

        grid.setVgap(10);

        panel.setLayout(grid);
        panel.setBackground(Color.BLACK);

        label1.setText(LabelText);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(new Font("Serif", Font.PLAIN, fontsize));
        label1.setForeground(Color.WHITE);

        panel.add(label1);

    }

}

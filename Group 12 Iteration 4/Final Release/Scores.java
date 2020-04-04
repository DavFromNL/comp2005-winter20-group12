import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.*;

/**
 *
 * @author Saurav
 */
public class Scores extends JFrame {

    public static int P1Scores = 0;
    public static int P2Scores = 0;
    public static int P3Scores = 0;
    public static int P4Scores = 0;

    JPanel Panel = new JPanel();

    public Scores() {
        super("Scores");

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        GridLayout grid = new GridLayout(5, 0);

        setSize(400, 400);
        setResizable(false);

        grid.setVgap(10);

        Panel.setLayout(grid);

        Panel.setBackground(Color.BLACK);

        JButton button = new MenuButtons();
        Panel.add(button);
        button.setBackground(Color.ORANGE);
        button.setFont(new Font("Arial", Font.PLAIN, 40));
        button.setText("Back");

        JLabel p1 = new JLabel();
        JLabel p2 = new JLabel();
        JLabel p3 = new JLabel();
        JLabel p4 = new JLabel();

        CreateLabels(p1, "P1 score ", P1Scores);

        CreateLabels(p2, "P2 score ", P2Scores);
        CreateLabels(p3, "P3 score ", P3Scores);
        CreateLabels(p4, "P4 score ", P4Scores);

        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {

                dispose();
            }

        });

        add(Panel);
        setVisible(true);

    }

    private void CreateLabels(JLabel p1, String text, int score) {

        p1.setFont(new Font("Serif", Font.PLAIN, 40));

        p1.setText(text + String.valueOf(score));

        p1.setForeground(Color.WHITE);

        Panel.add(p1);

    }

}

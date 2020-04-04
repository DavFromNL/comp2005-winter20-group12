import javax.swing.JPanel;
import java.awt.*;
import java.awt.Image.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardTypes extends JFrame {

    private JPanel Panel = new JPanel();
    int size = 2;
    JButton SimpleBoard;
    JButton ComplexBoard;

    public BoardTypes() {

        super("Board Types");

        GridLayout grid = new GridLayout(size + 1, 0, 0, 20);

        JLabel label1 = new JLabel();

        RicochetRobots.SetUpScreen(this, grid, Panel, label1, "Select Board Type", 70);

        SimpleBoard = new JButton();
        ComplexBoard = new JButton();
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            Image image = ImageIO.read(classLoader.getResource("res/simple.jpg"));
            Image image2 = ImageIO.read(classLoader.getResource("res/complex.jpg"));
            SimpleBoard.setIcon(new ImageIcon(image));
            ComplexBoard.setIcon(new ImageIcon(image2));

        } catch (Exception ex) {

            System.out.println("Image not found");
        }
        SimpleBoard.setBackground(Color.ORANGE);
        ComplexBoard.setBackground(Color.ORANGE);
        Panel.add(SimpleBoard);
        Panel.add(ComplexBoard);

        SimpleBoard.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {

                if (event.getSource() == SimpleBoard) {

                    Data.SetBoardType(1);
                    JOptionPane.showMessageDialog(null, "Simple Board Selected", "Saved", 1);

                }

            }

        });

        ComplexBoard.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {

                if (event.getSource() == ComplexBoard) {

                    Data.SetBoardType(2);
                    JOptionPane.showMessageDialog(null, "Complex Board Selected", "Saved", 1);

                }

            }

        });

        add(Panel);
        setVisible(true);

    }

}

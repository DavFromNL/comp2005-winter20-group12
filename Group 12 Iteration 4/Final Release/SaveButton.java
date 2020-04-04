import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveButton extends JButton implements ActionListener {

    public SaveButton() {
        this.addActionListener(this);

    }

    public void actionPerformed(ActionEvent event) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("SaveFile.txt"));
            if (Data.GetBoardType() == 1) {

                writer.write("" + Board.greenX);
                writer.newLine();
                writer.write("" + Board.greenY);
                writer.newLine();
                writer.write("" + Board.blueX);
                writer.newLine();
                writer.write("" + Board.blueY);
                writer.newLine();
                writer.write("" + Board.redX);
                writer.newLine();
                writer.write("" + Board.redY);
                writer.newLine();
                writer.write("" + Board.yellowX);
                writer.newLine();
                writer.write("" + Board.yellowY);

            } else {
                writer.write("" + ComplexBoard.greenX);
                writer.newLine();
                writer.write("" + ComplexBoard.greenY);
                writer.newLine();
                writer.write("" + ComplexBoard.blueX);
                writer.newLine();
                writer.write("" + ComplexBoard.blueY);
                writer.newLine();
                writer.write("" + ComplexBoard.redX);
                writer.newLine();
                writer.write("" + ComplexBoard.redY);
                writer.newLine();
                writer.write("" + ComplexBoard.yellowX);
                writer.newLine();
                writer.write("" + ComplexBoard.yellowY);
            }

            for (String key : BiddingSystem.Bids.keySet()) {
                writer.newLine();
                switch (key) {

                    case "Bid1":
                        writer.write("Bid1 " + BiddingSystem.Bids.get("Bid1"));
                        break;

                    case "Bid2":
                        writer.write("Bid2 " + BiddingSystem.Bids.get("Bid2"));
                        break;

                    case "Bid3":
                        writer.write("Bid3 " + BiddingSystem.Bids.get("Bid3"));
                        break;

                    case "Bid4":
                        writer.write("Bid4 " + BiddingSystem.Bids.get("Bid4"));
                        break;

                }

            }
            writer.newLine();
            writer.write("" + Scores.P1Scores);
            writer.newLine();
            writer.write("" + Scores.P2Scores);
            writer.newLine();
            writer.write("" + Scores.P3Scores);
            writer.newLine();
            writer.write("" + Scores.P4Scores);
            writer.newLine();
            writer.write("" + Data.GetCurrentPalette());
            writer.newLine();
            writer.write("" + Data.GetBoardType());

            writer.close();

            JOptionPane.showMessageDialog(null, "Game is Saved", "Saved", 1);

        } catch (Exception e) {

        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Board.txt"));
            writer.write("" + Data.GetBoardType());
            writer.close();

        } catch (Exception e) {

        }

    }

}

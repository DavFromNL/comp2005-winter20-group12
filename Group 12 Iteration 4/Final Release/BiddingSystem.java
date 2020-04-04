import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.util.*;
import javax.swing.JPanel;

public class BiddingSystem {
    JTextField Bid1;
    JTextField Bid2;
    JTextField Bid3;
    JTextField Bid4;
    JLabel TimerLabel;
    JButton Go;
    List<JTextField> CountdownLayerFields = new ArrayList<JTextField>();
    List<JLabel> CoutdownLayerLabels = new ArrayList<JLabel>();
    public static HashMap<String, Object> Bids = new HashMap();

    List<Integer> CurrentPosList = new ArrayList<Integer>();

    int greenx = 0;
    int greeny = 0;
    int bluex = 0;
    int bluey = 0;
    int yellowx = 0;
    int yellowy = 0;
    int redx = 0;
    int redy = 0;

    public static int counter = 0;
    JLabel MovesNum = new JLabel();
    JLabel BidNum = new JLabel();
    String MinBidPlayer;
    JLabel Turn = new JLabel();
    JButton GiveUp = new JButton();

    JButton Pause = new JButton();

    JButton Scores = new JButton();

    JPanel UpperPanel;
    private boolean clicked = false;

    public static int PlayerTurn = 0;

    List<javax.swing.JComponent> BidLayer = new ArrayList<javax.swing.JComponent>();

    public int TimeNow = 60;

    public void SetUpForTimer() {
        Bid1 = new JTextField(10);

        Bid2 = new JTextField(10);
        Bid3 = new JTextField(10);
        Bid4 = new JTextField(10);

        JLabel bidtext = new JLabel();
        bidtext.setFont(new Font("Serif", Font.PLAIN, 20));
        bidtext.setText("Bid: ");
        CoutdownLayerLabels.add(bidtext);

        if (Data.GetBoardType() == 1) {
            UpperPanel = Board.topPanel;
        } else {
            UpperPanel = ComplexBoard.topPanel;
        }

        UpperPanel.add(bidtext);

        if (Data.GetBoardType() == 1) {
            Board.Back.setVisible(false);
        } else {
            ComplexBoard.Back.setVisible(false);

        }

        NameandBidLocation("P1", Bid1);
        NameandBidLocation("P2", Bid2);
        NameandBidLocation("P3", Bid3);
        NameandBidLocation("P4", Bid4);

        StartTimer();

        GoButton();

    }

    private void StartTimer() {

        UpperPanel.add(Box.createHorizontalStrut(5));

        TimerLabel = new JLabel();
        TimerLabel.setFont(new Font("Serif", Font.PLAIN, 20));

        UpperPanel.add(TimerLabel);

        Timer timer = new Timer();

        TimerTask task = new TimerTask() {

            public void run() {
                if (TimeNow > 0) {
                    TimerLabel.setText("" + TimeNow--);

                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);

        new java.util.Timer().schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                if (!clicked) {
                    DecideTurn();
                }

            }
        }, 60000);

    }

    private void GoButton() {

        UpperPanel.add(Box.createHorizontalStrut(5));

        Go = new JButton();

        Go.setPreferredSize(new Dimension(100, 25));

        Go.setBackground(Color.ORANGE);

        Go.setFont(new Font("Arial", Font.PLAIN, 20));

        Go.setText("GO");

        UpperPanel.add(Go);

        Go.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                clicked = true;
                TooManyMoves();
                DecideTurn();

            }

        });

    }

    private void DecideTurn() {

        String P1bid = Bid1.getText();
        String P2bid = Bid2.getText();
        String P3bid = Bid3.getText();
        String P4bid = Bid4.getText();
        try {
            int bid1 = Integer.parseInt(P1bid);
            int bid2 = Integer.parseInt(P2bid);
            int bid3 = Integer.parseInt(P3bid);
            int bid4 = Integer.parseInt(P4bid);

            if (bid1 <= 0 || bid2 <= 0 || bid3 <= 0 || bid4 <= 0) {

                JOptionPane.showMessageDialog(null, "Invalid bid Entered", "Error", 1);

            } else {

                if (Bids.isEmpty()) {
                    Bids = new HashMap();
                    Bids.put("Bid1", bid1);
                    Bids.put("Bid2", bid2);
                    Bids.put("Bid3", bid3);
                    Bids.put("Bid4", bid4);

                    SetUpForMinBid();

                    SetMovesAndText();
                } else {
                    Bids.clear();
                    Bids.put("Bid1", bid1);
                    Bids.put("Bid2", bid2);
                    Bids.put("Bid3", bid3);
                    Bids.put("Bid4", bid4);

                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid bid Entered", "Error", 1);

        }

    }

    private void SetUpForMinBid() {

        Bid1.setVisible(false);
        Bid2.setVisible(false);
        Bid3.setVisible(false);
        Bid4.setVisible(false);
        Go.setVisible(false);
        TimerLabel.setVisible(false);

        for (int i = 0; i < CountdownLayerFields.size(); i++) {

            CountdownLayerFields.get(i).setVisible(false);

        }

        for (int i = 0; i < CoutdownLayerLabels.size(); i++) {
            CoutdownLayerLabels.get(i).setVisible(false);
        }

        MinBidPlayer = FindMin(Bids);

        if (BidLayer.size() == 0) {

            Turn = new JLabel();
            JLabel BidText = new JLabel();

            JLabel MovesText = new JLabel();
            MovesNum = new JLabel();
            BidNum = new JLabel();

            Turn.setFont(new Font("Serif", Font.PLAIN, 20));
            BidText.setFont(new Font("Serif", Font.PLAIN, 20));
            BidNum.setFont(new Font("Serif", Font.PLAIN, 20));
            MovesText.setFont(new Font("Serif", Font.PLAIN, 20));
            MovesNum.setFont(new Font("Serif", Font.PLAIN, 20));
            MovesNum.setText(String.valueOf(0));

            BidText.setText("Bid :");
            MovesText.setText("Moves: ");

            UpperPanel.add(Turn);
            UpperPanel.add(Box.createHorizontalStrut(5));
            UpperPanel.add(BidText);
            UpperPanel.add(BidNum);
            UpperPanel.add(Box.createHorizontalStrut(5));
            UpperPanel.add(MovesText);
            UpperPanel.add(MovesNum);

            AddButton(GiveUp, "Give Up");

            AddButton(Pause, "Pause");

            if (Data.GetBoardType() == 1) {
                Board.Back.setVisible(true);
                BidLayer.add(Board.Back);
            } else {
                ComplexBoard.Back.setVisible(true);
                BidLayer.add(ComplexBoard.Back);
            }

            AddButton(Scores, "Score");

            BidLayer.add(Turn);
            BidLayer.add(BidText);
            BidLayer.add(BidNum);
            BidLayer.add(MovesText);
            BidLayer.add(MovesNum);
            BidLayer.add(GiveUp);
            BidLayer.add(Pause);
            BidLayer.add(Scores);

        } else {

            for (int i = 0; i < BidLayer.size(); i++) {
                BidLayer.get(i).setVisible(true);

            }
        }

        Listeners();

    }

    public void BackToBidLayer() {

        for (int i = 0; i < BidLayer.size(); i++) {
            BidLayer.get(i).setVisible(false);

        }

        Bid1.setVisible(true);
        Bid2.setVisible(true);
        Bid3.setVisible(true);
        Bid4.setVisible(true);
        Go.setVisible(true);
        TimeNow = 60;
        TimerLabel.setVisible(true);

        for (int i = 0; i < CountdownLayerFields.size(); i++) {

            CountdownLayerFields.get(i).setVisible(true);

        }

        for (int i = 0; i < CoutdownLayerLabels.size(); i++) {
            CoutdownLayerLabels.get(i).setVisible(true);
        }

    }

    public void SetUpPanelLoading() {

        MinBidPlayer = FindMin(Bids);

        Turn = new JLabel();
        JLabel BidText = new JLabel();

        JLabel MovesText = new JLabel();
        MovesNum = new JLabel();
        BidNum = new JLabel();

        Turn.setFont(new Font("Serif", Font.PLAIN, 20));
        BidText.setFont(new Font("Serif", Font.PLAIN, 20));
        BidNum.setFont(new Font("Serif", Font.PLAIN, 20));
        MovesText.setFont(new Font("Serif", Font.PLAIN, 20));
        MovesNum.setFont(new Font("Serif", Font.PLAIN, 20));

        MovesNum.setText(String.valueOf(counter));

        BidText.setText("Bid :");
        MovesText.setText("Moves: ");

        if (Data.GetBoardType() == 1) {
            UpperPanel = Board.topPanel;
        } else {
            UpperPanel = ComplexBoard.topPanel;
        }

        UpperPanel.add(Turn);
        UpperPanel.add(Box.createHorizontalStrut(5));
        UpperPanel.add(BidText);
        UpperPanel.add(BidNum);
        UpperPanel.add(Box.createHorizontalStrut(5));
        UpperPanel.add(MovesText);
        UpperPanel.add(MovesNum);

        AddButton(GiveUp, "Give Up");

        AddButton(Pause, "Pause");

        greenx = 13;
        greeny = 7;
        bluex = 0;
        bluey = 0;
        yellowx = 10;
        yellowy = 3;
        redx = 3;
        redy = 11;

        CurrentPosList.add(greenx);
        CurrentPosList.add(greeny);
        CurrentPosList.add(bluex);
        CurrentPosList.add(bluey);
        CurrentPosList.add(yellowx);
        CurrentPosList.add(yellowy);
        CurrentPosList.add(redx);
        CurrentPosList.add(redy);

        if (Data.GetBoardType() == 1) {
            Board.Back.setVisible(true);
        } else {
            ComplexBoard.Back.setVisible(true);
        }

        AddButton(Scores, "Score");

        Listeners();

    }

    private void Listeners() {

        GiveUp.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {

                TooManyMoves();

            }

        });

        Pause.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {

                new PauseMenu();

            }

        });

        Scores.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {

                new Scores();

            }

        });

    }

    private void AddButton(JButton but, String text) {

        UpperPanel.add(Box.createHorizontalStrut(5));

        but.setPreferredSize(new Dimension(100, 25));

        but.setBackground(Color.ORANGE);

        but.setFont(new Font("Arial", Font.PLAIN, 15));

        but.setText(text);

        UpperPanel.add(but);

    }

    public void SetMovesAndText() {

        switch (MinBidPlayer) {

            case "Bid1":
                PlayerTurn = 1;
                Turn.setText("Player 1's Turn ");
                BidNum.setText(Bids.get("Bid1").toString());
                break;

            case "Bid2":
                PlayerTurn = 2;
                Turn.setText("Player 2's Turn ");
                BidNum.setText(Bids.get("Bid2").toString());
                break;

            case "Bid3":
                PlayerTurn = 3;
                Turn.setText("Player 3's Turn ");
                BidNum.setText(Bids.get("Bid3").toString());
                break;

            case "Bid4":
                PlayerTurn = 4;
                Turn.setText("Player 4's Turn ");
                BidNum.setText(Bids.get("Bid4").toString());
                break;

            default:

                BackToBidLayer();

        }

    }

    public void InitialRobotsPosition() {

        if (Data.GetBoardType() == 1) {

            greenx = Board.greenX;
            greeny = Board.greenY;
            bluex = Board.blueX;
            bluey = Board.blueY;
            yellowx = Board.yellowX;
            yellowy = Board.yellowY;
            redx = Board.redX;
            redy = Board.redY;

            CurrentPosList.add(Board.greenX);
            CurrentPosList.add(Board.greenY);
            CurrentPosList.add(Board.blueX);
            CurrentPosList.add(Board.blueY);
            CurrentPosList.add(Board.yellowX);
            CurrentPosList.add(Board.yellowY);
            CurrentPosList.add(Board.redX);
            CurrentPosList.add(Board.redY);

        } else {
            greenx = ComplexBoard.greenX;
            greeny = ComplexBoard.greenY;
            bluex = ComplexBoard.blueX;
            bluey = ComplexBoard.blueY;
            yellowx = ComplexBoard.yellowX;
            yellowy = ComplexBoard.yellowY;
            redx = ComplexBoard.redX;
            redy = ComplexBoard.redY;

            CurrentPosList.add(ComplexBoard.greenX);
            CurrentPosList.add(ComplexBoard.greenY);
            CurrentPosList.add(ComplexBoard.blueX);
            CurrentPosList.add(ComplexBoard.blueY);
            CurrentPosList.add(ComplexBoard.yellowX);
            CurrentPosList.add(ComplexBoard.yellowY);
            CurrentPosList.add(ComplexBoard.redX);
            CurrentPosList.add(ComplexBoard.redY);

        }

    }

    public boolean RobotChangePos() {

        List<Integer> newpos = new ArrayList<Integer>();

        if (Data.GetBoardType() == 1) {

            newpos.add(Board.greenX);
            newpos.add(Board.greenY);
            newpos.add(Board.blueX);
            newpos.add(Board.blueY);
            newpos.add(Board.yellowX);
            newpos.add(Board.yellowY);
            newpos.add(Board.redX);
            newpos.add(Board.redY);
        } else

        {

            newpos.add(ComplexBoard.greenX);
            newpos.add(ComplexBoard.greenY);
            newpos.add(ComplexBoard.blueX);
            newpos.add(ComplexBoard.blueY);
            newpos.add(ComplexBoard.yellowX);
            newpos.add(ComplexBoard.yellowY);
            newpos.add(ComplexBoard.redX);
            newpos.add(ComplexBoard.redY);

        }

        if (newpos.equals(CurrentPosList)) {

            return false;

        } else {
            counter++;

            MovesNum.setText(String.valueOf(counter));

            CurrentPosList = newpos;

            if (counter > Integer.parseInt(BidNum.getText())) {

                TooManyMoves();

            }

            return true;
        }

    }

    private void MoveRobotAll() {
        if (Data.GetBoardType() == 1) {

            Board.gridSquares[Board.greenY][Board.greenX].makeMove();

            Board.greenX = greenx;
            Board.greenY = greeny;

            Board.gridSquares[Board.greenY][Board.greenX].move(3);

            Board.gridSquares[Board.blueY][Board.blueX].makeMove();

            Board.blueX = bluex;

            Board.blueY = bluey;

            Board.gridSquares[Board.blueY][Board.blueX].move(2);

            Board.gridSquares[Board.redY][Board.redX].makeMove();

            Board.redX = redx;
            Board.redY = redy;

            Board.gridSquares[Board.redY][Board.redX].move(1);

            Board.gridSquares[Board.yellowY][Board.yellowX].makeMove();

            Board.yellowX = yellowx;

            Board.yellowY = yellowy;

            Board.gridSquares[Board.yellowY][Board.yellowX].move(4);

        } else {

            ComplexBoard.gridSquares[ComplexBoard.greenY][ComplexBoard.greenX].makeMove();

            ComplexBoard.greenX = greenx;
            ComplexBoard.greenY = greeny;

            ComplexBoard.gridSquares[ComplexBoard.greenY][ComplexBoard.greenX].move(3);

            ComplexBoard.gridSquares[ComplexBoard.blueY][ComplexBoard.blueX].makeMove();

            ComplexBoard.blueX = bluex;
            ComplexBoard.blueY = bluey;

            ComplexBoard.gridSquares[ComplexBoard.blueY][ComplexBoard.blueX].move(2);

            ComplexBoard.gridSquares[ComplexBoard.redY][ComplexBoard.redX].makeMove();

            ComplexBoard.redX = redx;
            ComplexBoard.redY = redy;

            ComplexBoard.gridSquares[ComplexBoard.redY][ComplexBoard.redX].move(1);

            ComplexBoard.gridSquares[ComplexBoard.yellowY][ComplexBoard.yellowX].makeMove();

            ComplexBoard.yellowX = yellowx;
            ComplexBoard.yellowY = yellowy;

            ComplexBoard.gridSquares[ComplexBoard.yellowY][ComplexBoard.yellowX].move(4);

        }

    }

    public void TooManyMoves() {

        MoveRobotAll();

        InitialRobotsPosition();

        Bids.remove(MinBidPlayer);

        counter = 0;

        MovesNum.setText(String.valueOf(counter));

        MinBidPlayer = FindMin(Bids);

        SetMovesAndText();

    }

    private void NameandBidLocation(String name, JTextField field) {

        UpperPanel.add(Box.createHorizontalStrut(5));
        JLabel p1 = new JLabel();
        p1.setText(name);
        p1.setFont(new Font("Serif", Font.PLAIN, 15));

        CoutdownLayerLabels.add(p1);

        UpperPanel.add(p1);

        field.setFont(new Font("Serif", Font.PLAIN, 15));
        CountdownLayerFields.add(field);
        UpperPanel.add(field);
    }

    private String FindMin(HashMap<String, Object> Bids) {

        int min = Integer.MAX_VALUE;

        String MinBid = "";

        for (String key : Bids.keySet()) {

            switch (key) {

                case "Bid1":

                    if ((int) Bids.get("Bid1") < min) {
                        min = (int) Bids.get("Bid1");

                        MinBid = "Bid1";

                    }
                    break;

                case "Bid2":

                    if ((int) Bids.get("Bid2") < min) {
                        min = (int) Bids.get("Bid2");
                        MinBid = "Bid2";
                    }
                    break;

                case "Bid3":

                    if ((int) Bids.get("Bid3") < min) {
                        min = (int) Bids.get("Bid3");
                        MinBid = "Bid3";

                    }
                    break;

                case "Bid4":

                    if ((int) Bids.get("Bid4") < min) {
                        min = (int) Bids.get("Bid4");
                        MinBid = "Bid4";

                    }
            }

        }

        return MinBid;

    }
}
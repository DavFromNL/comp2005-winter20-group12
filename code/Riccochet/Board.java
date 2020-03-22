//Creates a 2D array of cells from Grid and moves robot around the board

import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

public class Board extends JFrame implements ActionListener {
    // gui components that are contained in this frame:
    private JPanel topPanel, bottomPanel; // top and bottom panels in the main window

    public Grid[][] gridSquares; // squares to appear in grid formation in the bottom panel
    public static int x, y; // the size of the grid
    public int goalX = 1, goalY = 2;
    public int greenX = 13, greenY = 5; // Starting coords for the different robots
    public int blueX = 0, blueY = 0;
    public int yellowX = 10, yellowY = 3;
    public int redX = 3, redY = 11;
    public int selectedX, selectedY; // tracks the selected game square
    public int tempX, tempY;
    public boolean canMove = false; // determines wether a robot has been selected and a move can be made
    public int identifier = 0; // identifies what color robot is selected or updated
    /*
     * constructor method takes as input how many rows and columns of gridsquares to
     * create it then creates the panels, their subcomponents and puts them all
     * together in the main frame it makes sure that action listeners are added to
     * selectable items it makes sure that the gui will be visible
     */

    public Board(int x, int y) {
        this.x = x;
        this.y = y;

        // first create the panels
        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(x, y));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

        // for the bottom panel:
        // create the buttons and add them to the grid
        gridSquares = new Grid[x][y];

        for (int column = 0; column < x; column++) {
            for (int row = 0; row < y; row++) {
                gridSquares[column][row] = new Grid(x, y);
                gridSquares[column][row].setSize(200, 40);

                gridSquares[column][row].setOpaque(true); // without this line and the next the OS' default
                // gridSquares [column][row].setBorderPainted( false); // look & feel will
                // dominate / interfere
                // (try commenting each out & see what happens)

                gridSquares[column][row].addActionListener(this); // AGAIN, don't forget this line!
                gridSquares[column][row].setText("Ѫ");
                Color colour = new Color(220, 202, 152);
                gridSquares[column][row].setForeground(colour);
                gridSquares[column][row].setColor(column, row, redX, redY, blueX, blueY, greenX, greenY, yellowX,
                        yellowY);
                bottomPanel.add(gridSquares[column][row]);
            }
        }

        // now add the top and bottom panels to the main frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        pack();

        // housekeeping : behaviour
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public void changeRob(int x, int y) {
        // updates the position of the robot
        if (identifier == 1) {
            redY = y;
            redX = x;
        }
        if (identifier == 2) {
            blueY = y;
            blueX = x;
        }
        if (identifier == 3) {
            greenY = y;
            greenX = x;
        }
        if (identifier == 4) {
            yellowY = y;
            yellowX = x;
        }
    }

    /*
     * if canMove is false compares the selected square to the 4 robots coords to
     * see if the player has selected a robot
     */
    public void actionPerformed(ActionEvent aevt) {
        // get the object that was selected in the gui
        Object selected = aevt.getSource();
        // if canMove is false compares the selected square to the 4 robots coords to
        // see if the player has selected a robot
        if (canMove == false) {
            if (aevt.getSource() == gridSquares[redY][redX]) {
                canMove = true;
                gridSquares[redY][redX].highlight();
                selectedY = redY;
                selectedX = redX;
                identifier = 1;
            }
            if (aevt.getSource() == gridSquares[blueY][blueX]) {
                canMove = true;
                gridSquares[blueY][blueX].highlight();
                selectedY = blueY;
                selectedX = blueX;
                identifier = 2;
            }
            if (aevt.getSource() == gridSquares[greenY][greenX]) {
                canMove = true;
                gridSquares[greenY][greenX].highlight();
                selectedY = greenY;
                selectedX = greenX;
                identifier = 3;
            }
            if (aevt.getSource() == gridSquares[yellowY][yellowX]) {
                canMove = true;
                gridSquares[yellowY][yellowX].highlight();
                selectedY = yellowY;
                selectedX = yellowX;
                identifier = 4;
            }
        }
        // if canMove is true checks to see if the player has selected another robot. if
        // the player hasnt selected another robot
        // it checks to see if a valid move has been made and the moves the robot
        else if (canMove == true) {
            if (aevt.getSource() == gridSquares[redY][redX]) {
                canMove = true;
                gridSquares[selectedY][selectedX].dehighlight();
                gridSquares[redY][redX].highlight();
                selectedY = redY;
                selectedX = redX;
                identifier = 1;
            }
            if (aevt.getSource() == gridSquares[blueY][blueX]) {
                canMove = true;
                gridSquares[selectedY][selectedX].dehighlight();
                gridSquares[blueY][blueX].highlight();
                selectedY = blueY;
                selectedX = blueX;
                identifier = 2;
            }
            if (aevt.getSource() == gridSquares[greenY][greenX]) {
                canMove = true;
                gridSquares[selectedY][selectedX].dehighlight();
                gridSquares[greenY][greenX].highlight();
                selectedY = greenY;
                selectedX = greenX;
                identifier = 3;
            }
            if (aevt.getSource() == gridSquares[yellowY][yellowX]) {
                canMove = true;
                gridSquares[selectedY][selectedX].dehighlight();
                gridSquares[yellowY][yellowX].highlight();
                selectedY = yellowY;
                selectedX = yellowX;
                identifier = 4;
            }
            if (aevt.getSource() != gridSquares[yellowY][yellowX] & aevt.getSource() != gridSquares[redY][redX]
                    & aevt.getSource() != gridSquares[greenY][greenX] & aevt.getSource() != gridSquares[blueY][blueX]) {
                for (int column = 0; column < 16; column++) {
                    for (int row = 0; row < 16; row++) {
                        if (aevt.getSource() == gridSquares[column][row]) {
                            if (column != selectedY & row != selectedX) {
                                gridSquares[selectedY][selectedX].dehighlight();
                                canMove = false;
                                selectedY = 20;
                                selectedX = 20;

                            } else {
                                if (column == selectedY & row < selectedX) {
                                    gridSquares[selectedY][selectedX].makeMove();
                                    if (column == 0) {
                                        if (selectedX > 11) {

                                            gridSquares[selectedY][12].move(identifier);
                                            changeRob(12, selectedY);
                                        } else if (selectedX < 12 & selectedX > 1) {
                                            gridSquares[selectedY][2].move(identifier);
                                            changeRob(2, selectedY);
                                        } else if (selectedX < 2) {
                                            gridSquares[selectedY][0].move(identifier);
                                            changeRob(0, selectedY);
                                        }

                                    } else if (column == 1) {
                                        if (selectedX > 12) {
                                            gridSquares[selectedY][13].move(identifier);
                                            changeRob(13, selectedY);
                                        }
                                        if (selectedX < 13 & selectedX > 3) {
                                            gridSquares[selectedY][4].move(identifier);
                                            changeRob(4, selectedY);
                                        }
                                        if (selectedX < 4) {
                                            gridSquares[selectedY][0].move(identifier);
                                            changeRob(0, selectedY);
                                        }
                                    } else if (column == 2) {
                                        if (selectedX > 9) {
                                            gridSquares[selectedY][10].move(identifier);
                                            changeRob(10, selectedY);
                                        }
                                        if (selectedX < 10 & selectedX > 1) {
                                            gridSquares[selectedY][2].move(identifier);
                                            changeRob(2, selectedY);
                                        }
                                        if (selectedX < 2) {
                                            gridSquares[selectedY][0].move(identifier);
                                            changeRob(0, selectedY);
                                        }
                                    } else if (column == 3) {
                                        if (selectedX > 6) {
                                            gridSquares[selectedY][7].move(identifier);
                                            changeRob(7, selectedY);
                                        }
                                        if (selectedX < 7) {
                                            gridSquares[selectedY][0].move(identifier);
                                            changeRob(0, selectedY);
                                        }
                                    } else if (column == 4) {
                                        gridSquares[selectedY][0].move(identifier);
                                        changeRob(0, selectedY);
                                    } else if (column == 5) {
                                        if (selectedX > 13) {
                                            gridSquares[selectedY][14].move(identifier);
                                            changeRob(14, selectedY);
                                        }
                                        if (selectedX < 14) {
                                            gridSquares[selectedY][0].move(identifier);
                                            changeRob(0, selectedY);
                                        }
                                    } else if (column == 6) {
                                        if (selectedX > 11) {
                                            gridSquares[selectedY][12].move(identifier);
                                            changeRob(12, selectedY);
                                        }
                                        if (selectedX < 12 & selectedX > 2) {
                                            gridSquares[selectedY][3].move(identifier);
                                            changeRob(3, selectedY);
                                        }
                                        if (selectedX < 3) {
                                            gridSquares[selectedY][0].move(identifier);
                                            changeRob(0, selectedY);
                                        }
                                    } else if (column == 7) {
                                        if (selectedX > 8) {
                                            gridSquares[selectedY][9].move(identifier);
                                            changeRob(9, selectedY);
                                        }
                                        if (selectedX < 7) {
                                            gridSquares[selectedY][0].move(identifier);
                                            changeRob(0, selectedY);
                                        }
                                    } else if (column == 8) {
                                        if (selectedX > 8) {
                                            gridSquares[selectedY][9].move(identifier);
                                            changeRob(9, selectedY);
                                        }
                                        if (selectedX == 6) {
                                            gridSquares[selectedY][6].move(identifier);
                                            changeRob(6, selectedY);
                                        }
                                        if (selectedX < 6) {
                                            gridSquares[selectedY][0].move(identifier);
                                            changeRob(0, selectedY);
                                        }
                                    } else if (column == 9) {
                                        if (selectedX > 14) {
                                            gridSquares[selectedY][15].move(identifier);
                                            changeRob(15, selectedY);
                                        }
                                        if (selectedX < 15 & selectedX > 1) {
                                            gridSquares[selectedY][2].move(identifier);
                                            changeRob(2, selectedY);
                                        }
                                        if (selectedX < 2) {
                                            gridSquares[selectedY][0].move(identifier);
                                            changeRob(0, selectedY);
                                        }
                                    } else if (column == 10) {
                                        if (selectedX > 8) {
                                            gridSquares[selectedY][9].move(identifier);
                                            changeRob(9, selectedY);
                                        }
                                        if (selectedX < 9 & selectedX > 3) {
                                            gridSquares[selectedY][4].move(identifier);
                                            changeRob(4, selectedY);
                                        }
                                        if (selectedX < 4) {
                                            gridSquares[selectedY][0].move(identifier);
                                            changeRob(0, selectedY);
                                        }
                                    } else if (column == 11) {
                                        if (selectedX > 12) {
                                            gridSquares[selectedY][13].move(identifier);
                                            changeRob(13, selectedY);
                                        }
                                        if (selectedX < 13) {
                                            gridSquares[selectedY][0].move(identifier);
                                            changeRob(0, selectedY);
                                        }
                                    } else if (column == 12) {
                                        gridSquares[selectedY][0].move(identifier);
                                        changeRob(0, selectedY);
                                    } else if (column == 13) {
                                        if (selectedX > 9) {
                                            gridSquares[selectedY][10].move(identifier);
                                            changeRob(10, selectedY);
                                        }
                                        if (selectedX < 10 & selectedX > 5) {
                                            gridSquares[selectedY][6].move(identifier);
                                            changeRob(6, selectedY);
                                        }
                                        if (selectedX < 6) {
                                            gridSquares[selectedY][0].move(identifier);
                                            changeRob(0, selectedY);
                                        }
                                    } else if (column == 14) {
                                        if (selectedX > 2) {
                                            gridSquares[selectedY][3].move(identifier);
                                            changeRob(3, selectedY);
                                        }
                                        if (selectedX < 3) {
                                            gridSquares[selectedY][0].move(identifier);
                                            changeRob(0, selectedY);
                                        }
                                    } else if (column == 15) {
                                        if (selectedX > 11) {
                                            gridSquares[selectedY][12].move(identifier);
                                            changeRob(12, selectedY);
                                        }
                                        if (selectedX < 12 & selectedX > 6) {
                                            gridSquares[selectedY][7].move(identifier);
                                            changeRob(7, selectedY);
                                        }
                                        if (selectedX < 7) {
                                            gridSquares[selectedY][0].move(identifier);
                                            changeRob(0, selectedY);
                                        }
                                    }
                                    if (identifier == 1) {
                                        if (redX == blueX && redY == blueY) {
                                            gridSquares[selectedY][blueX + 1].move(identifier);
                                            gridSquares[blueY][blueX].move(2);
                                            changeRob(blueX + 1, selectedY);
                                        }
                                        if (redX == greenX && redY == greenY) {
                                            gridSquares[selectedY][greenX + 1].move(identifier);
                                            gridSquares[greenY][greenX].move(3);
                                            changeRob(greenX + 1, selectedY);
                                        }
                                        if (redX == yellowX && redY == yellowY) {
                                            gridSquares[selectedY][yellowX + 1].move(identifier);
                                            gridSquares[yellowY][yellowX].move(4);
                                            changeRob(yellowX + 1, selectedY);
                                        }
                                    }
                                    if (identifier == 2) {
                                        if (redX == blueX && redY == blueY) {
                                            gridSquares[selectedY][redX + 1].move(identifier);
                                            gridSquares[redY][redX].move(1);
                                            changeRob(redX + 1, selectedY);
                                        }
                                        if (blueX == greenX && blueY == greenY) {
                                            gridSquares[selectedY][greenX + 1].move(identifier);
                                            gridSquares[greenY][greenX].move(3);
                                            changeRob(greenX + 1, selectedY);
                                        }
                                        if (blueX == yellowX && blueY == yellowY) {
                                            gridSquares[selectedY][yellowX + 1].move(identifier);
                                            gridSquares[yellowY][yellowX].move(4);
                                            changeRob(yellowX + 1, selectedY);
                                        }
                                    }
                                    if (identifier == 3) {
                                        if (redX == greenX && redY == greenY) {
                                            gridSquares[selectedY][redX + 1].move(identifier);
                                            gridSquares[redY][redX].move(1);
                                            changeRob(redX + 1, selectedY);
                                        }
                                        if (blueX == greenX && blueY == greenY) {
                                            gridSquares[selectedY][blueX + 1].move(identifier);
                                            gridSquares[blueY][blueX].move(2);
                                            changeRob(blueX + 1, selectedY);
                                        }
                                        if (greenX == yellowX && greenY == yellowY) {
                                            gridSquares[selectedY][yellowX + 1].move(identifier);
                                            gridSquares[yellowY][yellowX].move(4);
                                            changeRob(yellowX + 1, selectedY);
                                        }
                                    }
                                    if (identifier == 4) {
                                        if (redX == yellowX && redY == yellowY) {
                                            gridSquares[selectedY][redX + 1].move(identifier);
                                            gridSquares[redY][redX].move(1);
                                            changeRob(redX + 1, selectedY);
                                        }
                                        if (yellowX == greenX && yellowY == greenY) {
                                            gridSquares[selectedY][greenX + 1].move(identifier);
                                            gridSquares[greenY][greenX].move(3);
                                            changeRob(greenX + 1, selectedY);
                                        }
                                        if (blueX == yellowX && blueY == yellowY) {
                                            gridSquares[selectedY][blueX + 1].move(identifier);
                                            gridSquares[blueY][blueX].move(2);
                                            changeRob(blueX + 1, selectedY);
                                        }
                                    }
                                    canMove = false;
                                }
                                if (column == selectedY & row > selectedX) {
                                    gridSquares[selectedY][selectedX].makeMove();
                                    if (column == 0) {
                                        if (selectedX > 11) {
                                            gridSquares[selectedY][15].move(identifier);
                                            changeRob(15, selectedY);
                                        } else if (selectedX < 12 & selectedX > 1) {
                                            gridSquares[selectedY][11].move(identifier);
                                            changeRob(11, selectedY);
                                        } else if (selectedX < 2) {
                                            gridSquares[selectedY][1].move(identifier);
                                            changeRob(1, selectedY);
                                        }
                                    } else if (column == 1) {
                                        if (selectedX > 12) {
                                            gridSquares[selectedY][15].move(identifier);
                                            changeRob(15, selectedY);
                                        }
                                        if (selectedX < 13 & selectedX > 3) {
                                            gridSquares[selectedY][12].move(identifier);
                                            changeRob(12, selectedY);
                                        }
                                        if (selectedX < 4) {
                                            gridSquares[selectedY][3].move(identifier);
                                            changeRob(3, selectedY);
                                        }
                                    } else if (column == 2) {
                                        if (selectedX > 9) {
                                            gridSquares[selectedY][15].move(identifier);
                                            changeRob(15, selectedY);
                                        }
                                        if (selectedX < 10 & selectedX > 1) {
                                            gridSquares[selectedY][9].move(identifier);
                                            changeRob(9, selectedY);
                                        }
                                        if (selectedX < 2) {
                                            gridSquares[selectedY][1].move(identifier);
                                            changeRob(1, selectedY);
                                        }
                                    } else if (column == 3) {
                                        if (selectedX > 6) {
                                            gridSquares[selectedY][15].move(identifier);
                                            changeRob(15, selectedY);
                                        }
                                        if (selectedX < 7) {
                                            gridSquares[selectedY][6].move(identifier);
                                            changeRob(6, selectedY);
                                        }
                                    } else if (column == 4) {
                                        gridSquares[selectedY][15].move(identifier);
                                        changeRob(15, selectedY);
                                    } else if (column == 5) {
                                        if (selectedX > 13) {
                                            gridSquares[selectedY][15].move(identifier);
                                            changeRob(15, selectedY);
                                        }
                                        if (selectedX < 14) {
                                            gridSquares[selectedY][13].move(identifier);
                                            changeRob(13, selectedY);
                                        }
                                    } else if (column == 6) {
                                        if (selectedX > 11) {
                                            gridSquares[selectedY][15].move(identifier);
                                            changeRob(15, selectedY);
                                        }
                                        if (selectedX < 12 & selectedX > 2) {
                                            gridSquares[selectedY][11].move(identifier);
                                            changeRob(11, selectedY);
                                        }
                                        if (selectedX < 3) {
                                            gridSquares[selectedY][2].move(identifier);
                                            changeRob(2, selectedY);
                                        }
                                    } else if (column == 7) {
                                        if (selectedX > 8) {
                                            gridSquares[selectedY][15].move(identifier);
                                            changeRob(15, selectedY);
                                        }
                                        if (selectedX < 7) {
                                            gridSquares[selectedY][6].move(identifier);
                                            changeRob(6, selectedY);
                                        }
                                    } else if (column == 8) {
                                        if (selectedX > 8) {
                                            gridSquares[selectedY][15].move(identifier);
                                            changeRob(15, selectedY);
                                        }
                                        if (selectedX == 6) {
                                            gridSquares[selectedY][6].move(identifier);
                                            changeRob(6, selectedY);
                                        }
                                        if (selectedX < 6) {
                                            gridSquares[selectedY][5].move(identifier);
                                            changeRob(5, selectedY);
                                        }
                                    } else if (column == 9) {
                                        if (selectedX > 14) {
                                            gridSquares[selectedY][15].move(identifier);
                                            changeRob(15, selectedY);
                                        }
                                        if (selectedX < 15 & selectedX > 1) {
                                            gridSquares[selectedY][14].move(identifier);
                                            changeRob(14, selectedY);
                                        }
                                        if (selectedX < 2) {
                                            gridSquares[selectedY][1].move(identifier);
                                            changeRob(1, selectedY);
                                        }
                                    } else if (column == 10) {
                                        if (selectedX > 8) {
                                            gridSquares[selectedY][15].move(identifier);
                                            changeRob(15, selectedY);
                                        }
                                        if (selectedX < 9 & selectedX > 3) {
                                            gridSquares[selectedY][8].move(identifier);
                                            changeRob(8, selectedY);
                                        }
                                        if (selectedX < 4) {
                                            gridSquares[selectedY][3].move(identifier);
                                            changeRob(3, selectedY);
                                        }
                                    } else if (column == 11) {
                                        if (selectedX > 12) {
                                            gridSquares[selectedY][15].move(identifier);
                                            changeRob(15, selectedY);
                                        }
                                        if (selectedX < 13) {
                                            gridSquares[selectedY][12].move(identifier);
                                            changeRob(12, selectedY);
                                        }
                                    } else if (column == 12) {
                                        gridSquares[selectedY][15].move(identifier);
                                        changeRob(15, selectedY);
                                    } else if (column == 13) {
                                        if (selectedX > 9) {
                                            gridSquares[selectedY][15].move(identifier);
                                            changeRob(15, selectedY);
                                        }
                                        if (selectedX < 10 & selectedX > 5) {
                                            gridSquares[selectedY][9].move(identifier);
                                            changeRob(9, selectedY);
                                        }
                                        if (selectedX < 6) {
                                            gridSquares[selectedY][5].move(identifier);
                                            changeRob(5, selectedY);
                                        }
                                    } else if (column == 14) {
                                        if (selectedX > 2) {
                                            gridSquares[selectedY][15].move(identifier);
                                            changeRob(15, selectedY);
                                        }
                                        if (selectedX < 3) {
                                            gridSquares[selectedY][2].move(identifier);
                                            changeRob(2, selectedY);
                                        }
                                    } else if (column == 15) {
                                        if (selectedX > 11) {
                                            gridSquares[selectedY][15].move(identifier);
                                            changeRob(15, selectedY);
                                        }
                                        if (selectedX < 12 & selectedX > 6) {
                                            gridSquares[selectedY][11].move(identifier);
                                            changeRob(11, selectedY);
                                        }
                                        if (selectedX < 7) {
                                            gridSquares[selectedY][6].move(identifier);
                                            changeRob(6, selectedY);
                                        }
                                    }
                                    if (identifier == 1) {
                                        if (redX == blueX && redY == blueY) {
                                            gridSquares[selectedY][blueX - 1].move(identifier);
                                            gridSquares[blueY][blueX].move(2);
                                            changeRob(blueX - 1, selectedY);
                                        }
                                        if (redX == greenX && redY == greenY) {
                                            gridSquares[selectedY][greenX - 1].move(identifier);
                                            gridSquares[greenY][greenX].move(3);
                                            changeRob(greenX - 1, selectedY);
                                        }
                                        if (redX == yellowX && redY == yellowY) {
                                            gridSquares[selectedY][yellowX - 1].move(identifier);
                                            gridSquares[yellowY][yellowX].move(4);
                                            changeRob(yellowX - 1, selectedY);
                                        }
                                    }
                                    if (identifier == 2) {
                                        if (redX == blueX && redY == blueY) {
                                            gridSquares[selectedY][redX - 1].move(identifier);
                                            gridSquares[redY][redX].move(1);
                                            changeRob(redX - 1, selectedY);
                                        }
                                        if (blueX == greenX && blueY == greenY) {
                                            gridSquares[selectedY][greenX - 1].move(identifier);
                                            gridSquares[greenY][greenX].move(3);
                                            changeRob(greenX - 1, selectedY);
                                        }
                                        if (blueX == yellowX && blueY == yellowY) {
                                            gridSquares[selectedY][yellowX - 1].move(identifier);
                                            gridSquares[yellowY][yellowX].move(4);
                                            changeRob(yellowX - 1, selectedY);
                                        }
                                    }
                                    if (identifier == 3) {
                                        if (redX == greenX && redY == greenY) {
                                            gridSquares[selectedY][redX - 1].move(identifier);
                                            gridSquares[redY][redX].move(1);
                                            changeRob(redX - 1, selectedY);
                                        }
                                        if (blueX == greenX && blueY == greenY) {
                                            gridSquares[selectedY][blueX - 1].move(identifier);
                                            gridSquares[blueY][blueX].move(2);
                                            changeRob(blueX - 1, selectedY);
                                        }
                                        if (greenX == yellowX && greenY == yellowY) {
                                            gridSquares[selectedY][yellowX - 1].move(identifier);
                                            gridSquares[yellowY][yellowX].move(4);
                                            changeRob(yellowX - 1, selectedY);
                                        }
                                    }
                                    if (identifier == 4) {
                                        if (redX == yellowX && redY == yellowY) {
                                            gridSquares[selectedY][redX - 1].move(identifier);
                                            gridSquares[redY][redX].move(1);
                                            changeRob(redX - 1, selectedY);
                                        }
                                        if (yellowX == greenX && yellowY == greenY) {
                                            gridSquares[selectedY][greenX - 1].move(identifier);
                                            gridSquares[greenY][greenX].move(3);
                                            changeRob(greenX - 1, selectedY);
                                        }
                                        if (blueX == yellowX && blueY == yellowY) {
                                            gridSquares[selectedY][blueX - 1].move(identifier);
                                            gridSquares[blueY][blueX].move(2);
                                            changeRob(blueX - 1, selectedY);
                                        }
                                    }
                                    canMove = false;
                                }

                                if (row == selectedX & column < selectedY) {
                                    gridSquares[selectedY][selectedX].makeMove();
                                    if (row == 0) {
                                        if (selectedY > 11) {
                                            gridSquares[12][selectedX].move(identifier);
                                            changeRob(selectedX, 12);
                                        }
                                        if (selectedY < 12 & selectedY > 5) {
                                            gridSquares[6][selectedX].move(identifier);
                                            changeRob(selectedX, 6);
                                        }
                                        if (selectedY < 6) {
                                            gridSquares[0][selectedX].move(identifier);
                                            changeRob(selectedX, 0);
                                        }
                                    } else if (row == 1) {
                                        if (selectedY > 9) {
                                            gridSquares[10][selectedX].move(identifier);
                                            changeRob(selectedX, 10);
                                        }
                                        if (selectedY < 10 & selectedY > 1) {
                                            gridSquares[2][selectedX].move(identifier);
                                            changeRob(selectedX, 2);
                                        }
                                        if (selectedY < 2) {
                                            gridSquares[0][selectedX].move(identifier);
                                            changeRob(selectedX, 0);
                                        }

                                    } else if (row == 2) {

                                        gridSquares[0][selectedX].move(identifier);
                                        changeRob(selectedX, 0);

                                    } else if (row == 3) {
                                        if (selectedY > 13) {
                                            gridSquares[14][selectedX].move(identifier);
                                            changeRob(selectedX, 14);
                                        }
                                        if (selectedY < 14 & selectedY > 6) {
                                            gridSquares[7][selectedX].move(identifier);
                                            changeRob(selectedX, 7);
                                        }
                                        if (selectedY < 7) {
                                            gridSquares[0][selectedX].move(identifier);
                                            changeRob(selectedX, 0);
                                        }

                                    } else if (row == 4) {
                                        if (selectedY > 10) {
                                            gridSquares[11][selectedX].move(identifier);
                                            changeRob(selectedX, 11);
                                        }
                                        if (selectedY < 11 & selectedY > 0) {
                                            gridSquares[1][selectedX].move(identifier);
                                            changeRob(selectedX, 1);
                                        }
                                        if (selectedY < 1) {
                                            gridSquares[0][selectedX].move(identifier);
                                            changeRob(selectedX, 0);
                                        }

                                    } else if (row == 5) {
                                        if (selectedY > 12) {
                                            gridSquares[13][selectedX].move(identifier);
                                            changeRob(selectedX, 13);
                                        }
                                        if (selectedY < 13 & selectedY > 7) {
                                            gridSquares[8][selectedX].move(identifier);
                                            changeRob(selectedX, 8);
                                        }
                                        if (selectedY < 8) {
                                            gridSquares[0][selectedX].move(identifier);
                                            changeRob(selectedX, 0);
                                        }

                                    } else if (row == 6) {
                                        if (selectedY > 3) {
                                            gridSquares[4][selectedX].move(identifier);
                                            changeRob(selectedX, 4);
                                        }
                                        if (selectedY < 4) {
                                            gridSquares[0][selectedX].move(identifier);
                                            changeRob(selectedX, 0);
                                        }

                                    } else if (row == 7) {
                                        if (selectedY > 8) {
                                            gridSquares[9][selectedX].move(identifier);
                                            changeRob(selectedX, 9);
                                        }
                                        if (selectedY < 7) {
                                            gridSquares[0][selectedX].move(identifier);
                                            changeRob(selectedX, 0);
                                        }

                                    } else if (row == 8) {
                                        if (selectedY > 9) {
                                            gridSquares[10][selectedX].move(identifier);
                                            changeRob(selectedX, 10);
                                        }
                                        if (selectedY == 9) {
                                            gridSquares[9][selectedX].move(identifier);
                                            changeRob(selectedX, 9);
                                        }
                                        if (selectedY < 7) {
                                            gridSquares[0][selectedX].move(identifier);
                                            changeRob(selectedX, 0);
                                        }

                                    } else if (row == 9) {
                                        if (selectedY > 2) {
                                            gridSquares[3][selectedX].move(identifier);
                                            changeRob(selectedX, 3);
                                        }
                                        if (selectedY < 3) {
                                            gridSquares[0][selectedX].move(identifier);
                                            changeRob(selectedX, 0);
                                        }

                                    } else if (row == 10) {
                                        if (selectedY > 12) {
                                            gridSquares[13][selectedX].move(identifier);
                                            changeRob(selectedX, 13);
                                        }
                                        if (selectedY < 13) {
                                            gridSquares[0][selectedX].move(identifier);
                                            changeRob(selectedX, 0);
                                        }

                                    } else if (row == 11) {
                                        if (selectedY > 5) {
                                            gridSquares[6][selectedX].move(identifier);
                                            changeRob(selectedX, 6);
                                        }
                                        if (selectedY < 6) {
                                            gridSquares[0][selectedX].move(identifier);
                                            changeRob(selectedX, 0);
                                        }

                                    } else if (row == 12) {

                                        gridSquares[0][selectedX].move(identifier);
                                        changeRob(selectedX, 0);

                                    } else if (row == 13) {
                                        if (selectedY > 11) {
                                            gridSquares[12][selectedX].move(identifier);
                                            changeRob(selectedX, 12);
                                        }
                                        if (selectedY < 12 & selectedY > 0) {
                                            gridSquares[1][selectedX].move(identifier);
                                            changeRob(selectedX, 1);
                                        }
                                        if (selectedY < 1) {
                                            gridSquares[0][selectedX].move(identifier);
                                            changeRob(selectedX, 0);
                                        }

                                    } else if (row == 14) {
                                        if (selectedY > 9) {
                                            gridSquares[10][selectedX].move(identifier);
                                            changeRob(selectedX, 10);
                                        }
                                        if (selectedY < 10 & selectedY > 5) {
                                            gridSquares[6][selectedX].move(identifier);
                                            changeRob(selectedX, 6);
                                        }
                                        if (selectedY < 6) {
                                            gridSquares[0][selectedX].move(identifier);
                                            changeRob(selectedX, 0);
                                        }

                                    } else if (row == 15) {
                                        if (selectedY > 10) {
                                            gridSquares[11][selectedX].move(identifier);
                                            changeRob(selectedX, 11);
                                        }
                                        if (selectedY < 11 & selectedY > 3) {
                                            gridSquares[4][selectedX].move(identifier);
                                            changeRob(selectedX, 4);
                                        }
                                        if (selectedY < 4) {
                                            gridSquares[0][selectedX].move(identifier);
                                            changeRob(selectedX, 0);
                                        }

                                    }
                                    if (identifier == 1) {
                                        if (redX == blueX && redY == blueY) {
                                            gridSquares[blueY + 1][selectedX].move(identifier);
                                            gridSquares[blueY][blueX].move(2);
                                            changeRob(selectedX, blueY + 1);
                                        }
                                        if (redX == greenX && redY == greenY) {
                                            gridSquares[greenY + 1][selectedX].move(identifier);
                                            gridSquares[greenY][greenX].move(3);
                                            changeRob(selectedX, greenY + 1);
                                        }
                                        if (redX == yellowX && redY == yellowY) {
                                            gridSquares[yellowY + 1][selectedX].move(identifier);
                                            gridSquares[yellowY][yellowX].move(4);
                                            changeRob(selectedX, yellowY + 1);
                                        }
                                    }
                                    if (identifier == 2) {
                                        if (redX == blueX && redY == blueY) {
                                            gridSquares[redY + 1][selectedX].move(identifier);
                                            gridSquares[redY][redX].move(1);
                                            changeRob(selectedX, redY + 1);
                                        }
                                        if (blueX == greenX && blueY == greenY) {
                                            gridSquares[greenY + 1][selectedX].move(identifier);
                                            gridSquares[greenY][greenX].move(3);
                                            changeRob(selectedX, greenY + 1);
                                        }
                                        if (blueX == yellowX && blueY == yellowY) {
                                            gridSquares[yellowY + 1][selectedX].move(identifier);
                                            gridSquares[yellowY][yellowX].move(4);
                                            changeRob(selectedX, yellowY + 1);
                                        }
                                    }
                                    if (identifier == 3) {
                                        if (redX == greenX && redY == greenY) {
                                            gridSquares[redY + 1][selectedX].move(identifier);
                                            gridSquares[redY][redX].move(1);
                                            changeRob(selectedX, redY + 1);
                                        }
                                        if (blueX == greenX && blueY == greenY) {
                                            gridSquares[blueY + 1][selectedX].move(identifier);
                                            gridSquares[blueY][blueX].move(2);
                                            changeRob(selectedX, blueY + 1);
                                        }
                                        if (greenX == yellowX && greenY == yellowY) {
                                            gridSquares[yellowY + 1][selectedX].move(identifier);
                                            gridSquares[yellowY][yellowX].move(4);
                                            changeRob(selectedX, yellowY + 1);
                                        }
                                    }
                                    if (identifier == 4) {
                                        if (redX == yellowX && redY == yellowY) {
                                            gridSquares[redY + 1][selectedX].move(identifier);
                                            gridSquares[redY][redX].move(1);
                                            changeRob(selectedX, redY + 1);
                                        }
                                        if (yellowX == greenX && yellowY == greenY) {
                                            gridSquares[greenY + 1][selectedX].move(identifier);
                                            gridSquares[greenY][greenX].move(3);
                                            changeRob(selectedX, greenY + 1);
                                        }
                                        if (blueX == yellowX && blueY == yellowY) {
                                            gridSquares[blueY + 1][selectedX].move(identifier);
                                            gridSquares[blueY][blueX].move(2);
                                            changeRob(selectedX, blueY + 1);
                                        }
                                    }

                                    canMove = false;
                                }

                                if (row == selectedX & column > selectedY) {
                                    gridSquares[selectedY][selectedX].makeMove();
                                    if (row == 0) {
                                        if (selectedY > 11) {
                                            gridSquares[15][selectedX].move(identifier);
                                            changeRob(selectedX, 15);
                                        }
                                        if (selectedY < 12 & selectedY > 5) {
                                            gridSquares[11][selectedX].move(identifier);
                                            changeRob(selectedX, 11);
                                        }
                                        if (selectedY < 6) {
                                            gridSquares[5][selectedX].move(identifier);
                                            changeRob(selectedX, 5);
                                        }
                                    } else if (row == 1) {
                                        if (selectedY > 9) {
                                            gridSquares[15][selectedX].move(identifier);
                                            changeRob(selectedX, 15);
                                        }
                                        if (selectedY < 10 & selectedY > 1) {
                                            gridSquares[9][selectedX].move(identifier);
                                            changeRob(selectedX, 9);
                                        }
                                        if (selectedY < 2) {
                                            gridSquares[1][selectedX].move(identifier);
                                            changeRob(selectedX, 1);
                                        }

                                    } else if (row == 2) {

                                        gridSquares[15][selectedX].move(identifier);
                                        changeRob(selectedX, 15);

                                    } else if (row == 3) {
                                        if (selectedY > 13) {
                                            gridSquares[15][selectedX].move(identifier);
                                            changeRob(selectedX, 15);
                                        }
                                        if (selectedY < 14 & selectedY > 6) {
                                            gridSquares[13][selectedX].move(identifier);
                                            changeRob(selectedX, 13);
                                        }
                                        if (selectedY < 7) {
                                            gridSquares[6][selectedX].move(identifier);
                                            changeRob(selectedX, 6);
                                        }

                                    } else if (row == 4) {
                                        if (selectedY > 10) {
                                            gridSquares[15][selectedX].move(identifier);
                                            changeRob(selectedX, 15);
                                        }
                                        if (selectedY < 11 & selectedY > 0) {
                                            gridSquares[10][selectedX].move(identifier);
                                            changeRob(selectedX, 10);
                                        }
                                        if (selectedY < 1) {
                                            gridSquares[0][selectedX].move(identifier);
                                            changeRob(selectedX, 0);
                                        }

                                    } else if (row == 5) {
                                        if (selectedY > 12) {
                                            gridSquares[15][selectedX].move(identifier);
                                            changeRob(selectedX, 15);
                                        }
                                        if (selectedY < 13 & selectedY > 7) {
                                            gridSquares[12][selectedX].move(identifier);
                                            changeRob(selectedX, 12);
                                        }
                                        if (selectedY < 8) {
                                            gridSquares[7][selectedX].move(identifier);
                                            changeRob(selectedX, 7);
                                        }

                                    } else if (row == 6) {
                                        if (selectedY > 3) {
                                            gridSquares[15][selectedX].move(identifier);
                                            changeRob(selectedX, 15);
                                        }
                                        if (selectedY < 4) {
                                            gridSquares[3][selectedX].move(identifier);
                                            changeRob(selectedX, 3);
                                        }

                                    } else if (row == 7) {
                                        if (selectedY > 8) {
                                            gridSquares[15][selectedX].move(identifier);
                                            changeRob(selectedX, 15);
                                        }
                                        if (selectedY < 7) {
                                            gridSquares[6][selectedX].move(identifier);
                                            changeRob(selectedX, 6);
                                        }

                                    } else if (row == 8) {
                                        if (selectedY > 9) {
                                            gridSquares[15][selectedX].move(identifier);
                                            changeRob(selectedX, 15);
                                        }
                                        if (selectedY == 9) {
                                            gridSquares[9][selectedX].move(identifier);
                                            changeRob(selectedX, 9);
                                        }
                                        if (selectedY < 7) {
                                            gridSquares[6][selectedX].move(identifier);
                                            changeRob(selectedX, 6);
                                        }

                                    } else if (row == 9) {
                                        if (selectedY > 2) {
                                            gridSquares[15][selectedX].move(identifier);
                                            changeRob(selectedX, 15);
                                        }
                                        if (selectedY < 3) {
                                            gridSquares[2][selectedX].move(identifier);
                                            changeRob(selectedX, 2);
                                        }

                                    } else if (row == 10) {
                                        if (selectedY > 12) {
                                            gridSquares[15][selectedX].move(identifier);
                                            changeRob(selectedX, 15);
                                        }
                                        if (selectedY < 13) {
                                            gridSquares[12][selectedX].move(identifier);
                                            changeRob(selectedX, 12);
                                        }

                                    } else if (row == 11) {
                                        if (selectedY > 5) {
                                            gridSquares[15][selectedX].move(identifier);
                                            changeRob(selectedX, 15);
                                        }
                                        if (selectedY < 6) {
                                            gridSquares[5][selectedX].move(identifier);
                                            changeRob(selectedX, 5);
                                        }

                                    } else if (row == 12) {

                                        gridSquares[15][selectedX].move(identifier);
                                        changeRob(selectedX, 15);

                                    } else if (row == 13) {
                                        if (selectedY > 11) {
                                            gridSquares[15][selectedX].move(identifier);
                                            changeRob(selectedX, 15);
                                        }
                                        if (selectedY < 12 & selectedY > 0) {
                                            gridSquares[11][selectedX].move(identifier);
                                            changeRob(selectedX, 11);
                                        }
                                        if (selectedY < 1) {
                                            gridSquares[0][selectedX].move(identifier);
                                            changeRob(selectedX, 0);
                                        }

                                    } else if (row == 14) {
                                        if (selectedY > 9) {
                                            gridSquares[15][selectedX].move(identifier);
                                            changeRob(selectedX, 15);
                                        }
                                        if (selectedY < 10 & selectedY > 5) {
                                            gridSquares[9][selectedX].move(identifier);
                                            changeRob(selectedX, 9);
                                        }
                                        if (selectedY < 6) {
                                            gridSquares[5][selectedX].move(identifier);
                                            changeRob(selectedX, 5);
                                        }

                                    } else if (row == 15) {
                                        if (selectedY > 10) {
                                            gridSquares[15][selectedX].move(identifier);
                                            changeRob(selectedX, 15);
                                        }
                                        if (selectedY < 11 & selectedY > 3) {
                                            gridSquares[10][selectedX].move(identifier);
                                            changeRob(selectedX, 10);
                                        }
                                        if (selectedY < 4) {
                                            gridSquares[3][selectedX].move(identifier);
                                            changeRob(selectedX, 3);
                                        }

                                    }
                                    if (identifier == 1) {
                                        if (redX == blueX && redY == blueY) {
                                            gridSquares[blueY - 1][selectedX].move(identifier);
                                            gridSquares[blueY][blueX].move(2);
                                            changeRob(selectedX, blueY - 1);
                                        }
                                        if (redX == greenX && redY == greenY) {
                                            gridSquares[greenY - 1][selectedX].move(identifier);
                                            gridSquares[greenY][greenX].move(3);
                                            changeRob(selectedX, greenY - 1);
                                        }
                                        if (redX == yellowX && redY == yellowY) {
                                            gridSquares[yellowY - 1][selectedX].move(identifier);
                                            gridSquares[yellowY][yellowX].move(4);
                                            changeRob(selectedX, yellowY - 1);
                                        }
                                    }
                                    if (identifier == 2) {
                                        if (redX == blueX && redY == blueY) {
                                            gridSquares[redY - 1][selectedX].move(identifier);
                                            gridSquares[redY][redX].move(1);
                                            changeRob(selectedX, redY - 1);
                                        }
                                        if (blueX == greenX && blueY == greenY) {
                                            gridSquares[greenY - 1][selectedX].move(identifier);
                                            gridSquares[greenY][greenX].move(3);
                                            changeRob(selectedX, greenY - 1);
                                        }
                                        if (blueX == yellowX && blueY == yellowY) {
                                            gridSquares[yellowY - 1][selectedX].move(identifier);
                                            gridSquares[yellowY][yellowX].move(4);
                                            changeRob(selectedX, yellowY - 1);
                                        }
                                    }
                                    if (identifier == 3) {
                                        if (redX == greenX && redY == greenY) {
                                            gridSquares[redY - 1][selectedX].move(identifier);
                                            gridSquares[redY][redX].move(1);
                                            changeRob(selectedX, redY - 1);
                                        }
                                        if (blueX == greenX && blueY == greenY) {
                                            gridSquares[blueY - 1][selectedX].move(identifier);
                                            gridSquares[blueY][blueX].move(2);
                                            changeRob(selectedX, blueY - 1);
                                        }
                                        if (greenX == yellowX && greenY == yellowY) {
                                            gridSquares[yellowY - 1][selectedX].move(identifier);
                                            gridSquares[yellowY][yellowX].move(4);
                                            changeRob(selectedX, yellowY - 1);
                                        }
                                    }
                                    if (identifier == 4) {
                                        if (redX == yellowX && redY == yellowY) {
                                            gridSquares[redY - 1][selectedX].move(identifier);
                                            gridSquares[redY][redX].move(1);
                                            changeRob(selectedX, redY - 1);
                                        }
                                        if (yellowX == greenX && yellowY == greenY) {
                                            gridSquares[greenY - 1][selectedX].move(identifier);
                                            gridSquares[greenY][greenX].move(3);
                                            changeRob(selectedX, greenY - 1);
                                        }
                                        if (blueX == yellowX && blueY == yellowY) {
                                            gridSquares[blueY - 1][selectedX].move(identifier);
                                            gridSquares[blueY][blueX].move(2);
                                            changeRob(selectedX, blueY - 1);
                                        }
                                    }
                                    canMove = false;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

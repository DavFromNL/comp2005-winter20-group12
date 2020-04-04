import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;
import java.util.concurrent.TimeUnit;
import java.util.Random;

/*
 *  the main window of the gui
 *  notice that it extends JFrame - so we can add our own components
 *  notice that it implements ActionListener - so we can handle user input
 */
public class ComplexBoard extends JFrame implements ActionListener {
    // gui components that are contained in this frame:
    private JPanel bottomPanel; // top and bottom panels in the main window

    public static JPanel topPanel;

    public static ComplexGrid[][] gridSquares; // squares to appear in grid formation in the bottom panel
    public static int x, y; // the size of the grid
    public int goalX, goalY, goalId;
    public static int greenX = 13, greenY = 7; // Starting coords for the different robots
    public static int blueX = 0, blueY = 0;
    public static int yellowX = 10, yellowY = 3;
    public static int redX = 3, redY = 11;
    public int selectedX, selectedY; // tracks the selected game square
    public int tempX, tempY;
    public boolean canMove = false; // determines wether a robot has been selected and a move can be made
    public int identifier = 0; // identifies what color robot is selected or updated
    public int tempblueX, tempredX, tempgreenX, tempyellowX, tempblueY, tempredY, tempgreenY, tempyellowY;
    public static boolean LoadingGame = false;
    public static int brefy = 2, brefx = 14, Brefy = 14, Brefx = 11, rrefy = 7, rrefx = 11, Rrefy = 13, Rrefx = 1;
    public static int grefy = 1, grefx = 4, Grefy = 12, Grefx = 6, yrefy = 7, yrefx = 5, Yrefy = 12, Yrefx = 9;
    public Color[] colorPalette = Data.GetPalette();
    public static JButton Back;
    BiddingSystem bid;
    private boolean load = false;
    boolean once = false;
    public static int close;
    Random rand = new Random();
    public static int x1, y1, x2, y2, x3, y3, x4, y4;

    /*
     * 
     * constructor method takes as input how many rows and columns of gridsquares to
     * create it then creates the panels, their subcomponents and puts them all
     * together in the main frame it makes sure that action listeners are added to
     * selectable items it makes sure that the gui will be visible
     */
    public ComplexBoard(int x, int y, boolean Load) {
        this.x = x;
        this.y = y;

        GameLoad(Load);
        load = Load;

        // first create the panels
        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        BackButton();

        bid = new BiddingSystem();

        if (!Load) {
            bid.SetUpForTimer();

            bid.InitialRobotsPosition();

        } else {
            bid.SetUpPanelLoading();
            bid.SetMovesAndText();

        }

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(x, y));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

        // for the bottom panel:
        // create the buttons and add them to the grid
        gridSquares = new ComplexGrid[x][y];

        for (int column = 0; column < x; column++) {
            for (int row = 0; row < y; row++) {
                gridSquares[column][row] = new ComplexGrid(x, y);
                gridSquares[column][row].setSize(200, 40);

                gridSquares[column][row].setOpaque(true); // without this line and the next the OS' default
                // gridSquares [column][row].setBorderPainted( false); // look & feel will
                // dominate / interfere
                // (try commenting each out & see what happens)

                gridSquares[column][row].addActionListener(this); // AGAIN, don't forget this line!
                gridSquares[column][row].setText("O");
                gridSquares[column][row].setForeground(colorPalette[0]);
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
        loadGoal();
    }

    public void loadGoal() {
        x1 = 3;
        y1 = 6;
        x2 = 10;
        y2 = 1;
        x3 = 3;
        y3 = 13;
        x4 = 13;
        y4 = 11;
        int r = rand.nextInt(3);
        int c = rand.nextInt(3);
        if (r == 0) {
            gridSquares[y1][x1].setColor(20, 20, c + 1, 0, 0, 20, 0, 0, 0, 0);
            gridSquares[7][7].setColor(20, 20, c + 1, 0, 0, 20, 0, 0, 0, 0);
            goalX = x1;
            goalY = y1;
            goalId = c + 1;
        }
        if (r == 1) {
            gridSquares[y2][x2].setColor(20, 20, c + 1, 0, 0, 20, 0, 0, 0, 0);
            gridSquares[7][7].setColor(20, 20, c + 1, 0, 0, 20, 0, 0, 0, 0);
            goalX = x2;
            goalY = y2;
            goalId = c + 1;
        }
        if (r == 2) {
            gridSquares[y3][x3].setColor(20, 20, c + 1, 0, 0, 20, 0, 0, 0, 0);
            gridSquares[7][7].setColor(20, 20, c + 1, 0, 0, 20, 0, 0, 0, 0);
            goalX = x3;
            goalY = y3;
            goalId = c + 1;
        }
        if (r == 3) {
            gridSquares[y4][x4].setColor(20, 20, c + 1, 0, 0, 20, 0, 0, 0, 0);
            gridSquares[7][7].setColor(20, 20, c + 1, 0, 0, 20, 0, 0, 0, 0);
            goalX = x4;
            goalY = y4;
            goalId = c + 1;
        }
    }

    public void changeRob(int x, int y) {
        // updates the position of the robot
        if (identifier == 1) {
            redY = y;
            redX = x;
            if (redY == goalY & redX == goalX & identifier == goalId) {
                if (bid.PlayerTurn == 1) {
                    Scores.P1Scores = Scores.P1Scores + 1;
                }
                if (bid.PlayerTurn == 2) {
                    Scores.P2Scores = Scores.P2Scores + 1;
                }
                if (bid.PlayerTurn == 3) {
                    Scores.P3Scores = Scores.P3Scores + 1;
                }
                if (bid.PlayerTurn == 4) {
                    Scores.P4Scores = Scores.P4Scores + 1;
                }
                bid.TooManyMoves();
                bid.BackToBidLayer();
                loadGoal();
            }
        }
        if (identifier == 2) {
            blueY = y;
            blueX = x;
            if (blueY == goalY & blueX == goalX & identifier == goalId) {
                if (bid.PlayerTurn == 1) {
                    Scores.P1Scores = Scores.P1Scores + 1;
                }
                if (bid.PlayerTurn == 2) {
                    Scores.P2Scores = Scores.P2Scores + 1;
                }
                if (bid.PlayerTurn == 3) {
                    Scores.P3Scores = Scores.P3Scores + 1;
                }
                if (bid.PlayerTurn == 4) {
                    Scores.P4Scores = Scores.P4Scores + 1;
                }
                bid.TooManyMoves();
                bid.BackToBidLayer();
                loadGoal();
            }
        }
        if (identifier == 3) {
            greenY = y;
            greenX = x;
            if (greenY == goalY & greenX == goalX & identifier == goalId) {
                if (bid.PlayerTurn == 1) {
                    Scores.P1Scores = Scores.P1Scores + 1;
                }
                if (bid.PlayerTurn == 2) {
                    Scores.P2Scores = Scores.P2Scores + 1;
                }
                if (bid.PlayerTurn == 3) {
                    Scores.P3Scores = Scores.P3Scores + 1;
                }
                if (bid.PlayerTurn == 4) {
                    Scores.P4Scores = Scores.P4Scores + 1;
                }
                bid.TooManyMoves();
                bid.BackToBidLayer();
                loadGoal();
            }
        }
        if (identifier == 4) {
            yellowY = y;
            yellowX = x;
            if (yellowY == goalY & yellowX == goalX & identifier == goalId) {
                if (bid.PlayerTurn == 1) {
                    Scores.P1Scores = Scores.P1Scores + 1;
                }
                if (bid.PlayerTurn == 2) {
                    Scores.P2Scores = Scores.P2Scores + 1;
                }
                if (bid.PlayerTurn == 3) {
                    Scores.P3Scores = Scores.P3Scores + 1;
                }
                if (bid.PlayerTurn == 4) {
                    Scores.P4Scores = Scores.P4Scores + 1;
                }
                bid.TooManyMoves();
                bid.BackToBidLayer();
                loadGoal();
            }
        }
    }

    public void reflect(int selected, int con, int direction, int coord, int var) {
        if (direction == 0 & coord == 1) {
            if (identifier == 1) {

                if (rrefy == con & rrefx >= var & rrefx < selected) {
                    gridSquares[checkRob(selectedY, 15, 2, 11, 2)][rrefx].move(identifier);
                    changeRob(rrefx, checkRob(selectedY, 15, 2, 11, 2));
                } else if (Rrefy == con & Rrefx >= var & Rrefx < selected) {
                    gridSquares[checkRob(selectedY, 15, 2, Rrefx, 2)][Rrefx].move(identifier);
                    changeRob(Rrefx, checkRob(selectedY, 15, 2, Rrefx, 2));
                }

            }
            if (identifier == 2) {
                if (brefy == con & brefx >= var & brefx < selected) {
                    gridSquares[checkRob(selectedY, 0, 2, brefx, 1)][brefx].move(identifier);
                    changeRob(brefx, checkRob(selectedY, 0, 2, brefx, 1));
                } else if (Brefy == con & Brefx >= var & Brefx < selected) {
                    gridSquares[checkRob(selectedY, 0, 2, Brefx, 1)][Brefx].move(identifier);
                    changeRob(Brefx, checkRob(selectedY, 0, 2, Brefx, 1));
                }

            }
            if (identifier == 3) {
                if (grefy == con & grefx >= var & grefx < selected) {
                    gridSquares[checkRob(selectedY, 15, 2, grefx, 2)][grefx].move(identifier);
                    changeRob(grefx, checkRob(selectedY, 15, 2, grefx, 2));
                } else if (Grefy == con & Grefx >= var & Grefx < selected) {
                    gridSquares[checkRob(selectedY, 15, 2, Grefx, 2)][Grefx].move(identifier);
                    changeRob(Grefx, checkRob(selectedY, 15, 2, Grefx, 2));
                }

            }
            if (identifier == 4) {
                if (yrefy == con & yrefx >= var & yrefx < selected) {
                    gridSquares[checkRob(selectedY, 0, 2, yrefx, 1)][yrefx].move(identifier);
                    changeRob(yrefx, checkRob(selectedY, 0, 2, yrefx, 1));
                } else if (Yrefy == con & Yrefx >= var & Yrefx < selected) {
                    gridSquares[checkRob(selectedY, 0, 2, Yrefx, 1)][Yrefx].move(identifier);
                    changeRob(Yrefx, checkRob(selectedY, 0, 2, Yrefx, 1));
                }
            }
        } else if (direction == 1 & coord == 1) {
            if (identifier == 1) {

                if (rrefy == con & rrefx <= var & rrefx > selected) {
                    gridSquares[checkRob(selectedY, 0, 2, 11, 1)][rrefx].move(identifier);
                    changeRob(rrefx, checkRob(selectedY, 0, 2, 11, 1));
                } else if (Rrefy == con & Rrefx <= var & Rrefx > selected) {
                    gridSquares[checkRob(selectedY, 3, 2, Rrefx, 1)][Rrefx].move(identifier);
                    changeRob(Rrefx, checkRob(selectedY, 3, 2, Rrefx, 1));
                }

            }
            if (identifier == 2) {
                if (brefy == con & brefx <= var & brefx > selected) {
                    gridSquares[checkRob(selectedY, 15, 2, brefx, 2)][brefx].move(identifier);
                    changeRob(brefx, checkRob(selectedY, 15, 2, brefx, 2));
                } else if (Brefy == con & Brefx <= var & Brefx > selected) {
                    gridSquares[checkRob(selectedY, 15, 2, Brefx, 2)][Brefx].move(identifier);
                    changeRob(Brefx, checkRob(selectedY, 15, 2, Brefx, 2));
                }

            }
            if (identifier == 3) {
                if (grefy == con & grefx <= var & grefx > selected) {
                    gridSquares[checkRob(selectedY, 0, 2, grefx, 1)][grefx].move(identifier);
                    changeRob(grefx, checkRob(selectedY, 0, 2, grefx, 1));
                } else if (Grefy == con & Grefx <= var & Grefx > selected) {
                    gridSquares[checkRob(selectedY, 5, 2, Grefx, 1)][Grefx].move(identifier);
                    changeRob(Grefx, checkRob(selectedY, 5, 2, Grefx, 1));
                }

            }
            if (identifier == 4) {
                if (yrefy == con & yrefx <= var & yrefx > selected) {
                    gridSquares[checkRob(selectedY, 13, 2, yrefx, 2)][yrefx].move(identifier);
                    changeRob(yrefx, checkRob(selectedY, 13, 2, yrefx, 2));
                }

            }

        }
        if (direction == 0 & coord == 2) {
            if (identifier == 1) {

                if (rrefx == con & rrefy >= var & rrefy < selected) {
                    gridSquares[rrefy][checkRob(selectedX, 15, 1, rrefy, 2)].move(identifier);
                    changeRob(checkRob(selectedX, 15, 1, rrefy, 2), rrefy);
                } else if (Rrefx == con & Rrefy >= var & Rrefy < selected) {
                    gridSquares[Rrefy][checkRob(selectedX, 2, 1, Rrefy, 2)].move(identifier);
                    changeRob(checkRob(selectedX, 2, 1, Rrefy, 2), Rrefy);
                }

            }
            if (identifier == 2) {
                if (brefx == con & brefy >= var & brefy < selected) {
                    gridSquares[brefy][checkRob(selectedX, 0, 1, brefy, 1)].move(identifier);
                    changeRob(checkRob(selectedX, 0, 1, brefy, 1), brefy);
                } else if (Brefx == con & Brefy >= var & Brefy < selected) {
                    gridSquares[Brefy][checkRob(selectedX, 6, 1, Brefy, 1)].move(identifier);
                    changeRob(checkRob(selectedX, 6, 1, Brefy, 1), Brefy);
                }

            }
            if (identifier == 3) {
                if (grefx == con & grefy >= var & grefy < selected) {
                    gridSquares[grefy][checkRob(selectedX, 9, 1, grefy, 2)].move(identifier);
                    changeRob(checkRob(selectedX, 9, 1, grefy, 2), grefy);
                } else if (Grefx == con & Grefy >= var & Grefy < selected) {
                    gridSquares[Grefy][checkRob(selectedX, 15, 1, Grefy, 2)].move(identifier);
                    changeRob(checkRob(selectedX, 15, 1, Grefy, 2), Grefy);
                }

            }
            if (identifier == 4) {
                if (yrefx == con & yrefy >= var & yrefy < selected) {
                    gridSquares[yrefy][checkRob(selectedX, 0, 1, yrefy, 1)].move(identifier);
                    changeRob(checkRob(selectedX, 0, 1, yrefy, 1), yrefy);
                }

            }
        }
        if (direction == 1 & coord == 2) {
            if (identifier == 1) {

                if (rrefx == con & rrefy <= var & rrefy > selected) {
                    gridSquares[rrefy][checkRob(selectedX, 9, 1, rrefy, 1)].move(identifier);
                    changeRob(checkRob(selectedX, 9, 1, rrefy, 1), rrefy);
                } else if (Rrefx == con & Rrefy <= var & Rrefy > selected) {
                    gridSquares[Rrefy][checkRob(selectedX, 0, 1, Rrefy, 1)].move(identifier);
                    changeRob(checkRob(selectedX, 0, 1, Rrefy, 1), Rrefy);
                }

            }
            if (identifier == 2) {
                if (brefx == con & brefy <= var & brefy > selected) {
                    gridSquares[brefy][checkRob(selectedX, 15, 1, brefy, 2)].move(identifier);
                    changeRob(checkRob(selectedX, 15, 1, brefy, 2), brefy);
                } else if (Brefx == con & Brefy <= var & Brefy > selected) {
                    gridSquares[Brefy][checkRob(selectedX, 15, 1, Brefy, 2)].move(identifier);
                    changeRob(checkRob(selectedX, 15, 1, Brefy, 2), Brefy);
                }

            }
            if (identifier == 3) {
                if (grefx == con & grefy <= var & grefy > selected) {
                    gridSquares[grefy][checkRob(selectedX, 0, 1, grefy, 1)].move(identifier);
                    changeRob(checkRob(selectedX, 0, 1, grefy, 1), grefy);
                } else if (Grefx == con & Grefy <= var & Grefy > selected) {
                    gridSquares[Grefy][checkRob(selectedX, 4, 1, Grefy, 1)].move(identifier);
                    changeRob(checkRob(selectedX, 4, 1, Grefy, 1), Grefy);
                }

            }
            if (identifier == 4) {
                if (yrefx == con & yrefy <= var & yrefy > selected) {
                    gridSquares[yrefy][checkRob(selectedX, 6, 1, yrefy, 2)].move(identifier);
                    changeRob(checkRob(selectedX, 6, 1, yrefy, 2), yrefy);
                }
                if (Yrefx == con & Yrefy <= var & Yrefy > selected) {
                    gridSquares[Yrefy][checkRob(selectedX, 15, 1, Yrefy, 2)].move(identifier);
                    changeRob(checkRob(selectedX, 15, 1, Yrefy, 2), Yrefy);
                }
            }
        }
    }

    public int checkRob(int selected, int var, int decider, int con, int decider2) {

        if (decider == 1) {
            tempredX = redX;
            tempblueX = blueX;
            tempgreenX = greenX;
            tempyellowX = yellowX;
            tempredY = redY;
            tempblueY = blueY;
            tempgreenY = greenY;
            tempyellowY = yellowY;
        }
        if (decider == 2) {
            tempredY = redX;
            tempblueY = blueX;
            tempgreenY = greenX;
            tempyellowY = yellowX;
            tempredX = redY;
            tempblueX = blueY;
            tempgreenX = greenY;
            tempyellowX = yellowY;
        }
        if (decider2 == 1) {
            close = -1;
            if (identifier == 1) {
                if (tempblueY != con & tempgreenY != con & tempyellowY != con) {
                    return var;
                }

                if (tempblueY == con & tempblueX >= var & tempblueX < selected) {
                    if (tempblueX > close) {
                        close = tempblueX;
                    }
                }
                if (tempgreenY == con & tempgreenX >= var & tempgreenX < selected) {
                    if (tempgreenX > close) {
                        close = tempgreenX;
                    }
                }
                if (tempyellowY == con & tempyellowX >= var & tempyellowX < selected) {
                    if (tempyellowX > close) {
                        close = tempyellowX;
                    }
                }
                if (close != -1) {
                    return close + 1;
                }
            }
            if (identifier == 2) {
                if (tempredY != con & tempgreenY != con & tempyellowY != con) {
                    return var;
                }
                if (tempredY == con & tempredX >= var & tempredX < selected) {
                    if (tempredX > close) {
                        close = tempredX;
                    }
                }
                if (tempgreenY == con & tempgreenX >= var & tempgreenX < selected) {
                    if (tempgreenX > close) {
                        close = tempgreenX;
                    }
                }
                if (tempyellowY == con & tempyellowX >= var & tempyellowX < selected) {
                    if (tempyellowX > close) {
                        close = tempyellowX;
                    }
                }
                if (close != -1) {
                    return close + 1;
                }
            }
            if (identifier == 3) {
                if (tempblueY != con & tempredY != con & tempyellowY != con) {
                    return var;
                }
                if (tempblueY == con & tempblueX >= var & tempblueX < selected) {
                    if (tempblueX > close) {
                        close = tempblueX;
                    }
                }
                if (tempredY == con & tempredX >= var & tempredX < selected) {
                    if (tempredX > close) {
                        close = tempredX;
                    }
                }
                if (tempyellowY == con & tempyellowX >= var & tempyellowX < selected) {
                    if (tempyellowX > close) {
                        close = tempyellowX;
                    }
                }
                if (close != -1) {
                    return close + 1;
                }
            }
            if (identifier == 4) {
                if (tempblueY != con & tempgreenY != con & tempredY != con) {
                    return var;
                }
                if (tempblueY == con & tempblueX >= var & tempblueX < selected) {
                    if (tempblueX > close) {
                        close = tempblueX;
                    }
                }
                if (tempgreenY == con & tempgreenX >= var & tempgreenX < selected) {
                    if (tempgreenX > close) {
                        close = tempgreenX;
                    }
                }
                if (tempredY == con & tempredX >= var & tempredX < selected) {
                    if (tempredX > close) {
                        close = tempredX;
                    }
                }
                if (close != -1) {
                    return close + 1;
                }
            }

        }
        if (decider2 == 2) {
            close = 20;
            if (identifier == 1) {
                if (tempblueY != con & tempgreenY != con & tempyellowY != con) {
                    return var;
                }
                if (tempblueY == con & tempblueX <= var & tempblueX > selected) {
                    if (tempblueX < close) {
                        close = tempblueX;
                    }
                }
                if (tempgreenY == con & tempgreenX <= var & tempgreenX > selected) {
                    if (tempgreenX < close) {
                        close = tempgreenX;
                    }
                }
                if (tempyellowY == con & tempyellowX <= var & tempyellowX > selected) {
                    if (tempyellowX < close) {
                        close = tempyellowX;
                    }
                }
                if (close != 20) {
                    return close - 1;
                }
            }
            if (identifier == 2) {
                if (tempredY != con & tempgreenY != con & tempyellowY != con) {
                    return var;
                }
                if (tempredY == con & tempredX <= var & tempredX > selected) {
                    if (tempredX < close) {
                        close = tempredX;
                    }
                }
                if (tempgreenY == con & tempgreenX <= var & tempgreenX > selected) {
                    if (tempgreenX < close) {
                        close = tempgreenX;
                    }
                }
                if (tempyellowY == con & tempyellowX <= var & tempyellowX > selected) {
                    if (tempyellowX < close) {
                        close = tempyellowX;
                    }
                }
                if (close != 20) {
                    return close - 1;
                }
            }
            if (identifier == 3) {
                if (tempblueY != con & tempredY != con & tempyellowY != con) {
                    return var;
                }
                if (tempblueY == con & tempblueX <= var & tempblueX > selected) {
                    if (tempblueX < close) {
                        close = tempblueX;
                    }
                }
                if (tempredY == con & tempredX <= var & tempredX > selected) {
                    if (tempredX < close) {
                        close = tempredX;
                    }
                }
                if (tempyellowY == con & tempyellowX <= var & tempyellowX > selected) {
                    if (tempyellowX < close) {
                        close = tempyellowX;
                    }
                }
                if (close != 20) {
                    return close - 1;
                }
            }
            if (identifier == 4) {
                if (tempblueY != con & tempgreenY != con & tempredY != con) {
                    return var;
                }
                if (tempblueY == con & tempblueX <= var & tempblueX > selected) {
                    if (tempblueX < close) {
                        close = tempblueX;
                    }
                }
                if (tempgreenY == con & tempgreenX <= var & tempgreenX > selected) {
                    if (tempgreenX < close) {
                        close = tempgreenX;
                    }
                }
                if (tempredY == con & tempredX <= var & tempredX > selected) {
                    if (tempredX < close) {
                        close = tempredX;
                    }
                }
                if (close != 20) {
                    return close - 1;
                }
            }

        }
        return var;
    }

    private void SetUpSaveButton() {

        SaveButton Save = new SaveButton();

        Save.setPreferredSize(new Dimension(300, 25));

        Save.setBackground(Color.ORANGE);

        Save.setFont(new Font("Arial", Font.PLAIN, 20));

        Save.setText("Save Game");

        topPanel.add(Save);

    }

    private void BackButton() {

        Back = new JButton();

        Back.setPreferredSize(new Dimension(70, 25));

        Back.setBackground(Color.ORANGE);

        Back.setFont(new Font("Arial", Font.PLAIN, 10));

        Back.setText(" <<< ");

        topPanel.add(Back);

        Back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {

                dispose();

            }

        });

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
                                        if (selectedX > 8) {

                                            gridSquares[selectedY][checkRob(selectedX, 9, 1, 0, 1)].move(identifier);
                                            changeRob(checkRob(selectedX, 9, 1, 0, 1), selectedY);
                                        } else if (selectedX < 9 & selectedX > 5) {

                                            gridSquares[selectedY][checkRob(selectedX, 6, 1, 0, 1)].move(identifier);
                                            changeRob(checkRob(selectedX, 6, 1, 0, 1), selectedY);
                                        }

                                        else if (selectedX < 6) {
                                            gridSquares[selectedY][checkRob(selectedX, 0, 1, 0, 1)].move(identifier);
                                            changeRob(checkRob(selectedX, 0, 1, 0, 1), selectedY);
                                        }

                                    } else if (column == 1) {
                                        if (selectedX > 9) {
                                            gridSquares[selectedY][checkRob(selectedX, 10, 1, 1, 1)].move(identifier);
                                            changeRob(checkRob(selectedX, 10, 1, 1, 1), selectedY);
                                        }
                                        if (selectedX < 10 & selectedX > 4) {
                                            if (identifier != 3 || checkRob(selectedX, 4, 1, column, 1) != 4) {
                                                gridSquares[selectedY][checkRob(selectedX, 0, 1, column, 1)]
                                                        .move(identifier);
                                                changeRob(checkRob(selectedX, 0, 1, column, 1), selectedY);
                                            }
                                            if (identifier == 3 & checkRob(selectedX, 4, 1, column, 1) == 4) {
                                                reflect(selectedX, column, 0, 1, 4);
                                            }
                                        }
                                        if (selectedX < 4) {
                                            gridSquares[selectedY][checkRob(selectedX, 0, 1, 1, 1)].move(identifier);
                                            changeRob(checkRob(selectedX, 0, 1, 1, 1), selectedY);
                                        }
                                    } else if (column == 2) {

                                        if (selectedX > 14) {
                                            if (identifier != 2 || checkRob(selectedX, 14, 1, column, 1) != 14) {
                                                gridSquares[selectedY][checkRob(selectedX, 0, 1, column, 1)]
                                                        .move(identifier);
                                                changeRob(checkRob(selectedX, 0, 1, column, 1), selectedY);
                                            }
                                            if (identifier == 2 & checkRob(selectedX, 14, 1, column, 1) == 14) {
                                                reflect(selectedX, column, 0, 1, 14);
                                            }
                                        }
                                        if (selectedX < 14) {
                                            gridSquares[selectedY][checkRob(selectedX, 0, 1, 2, 1)].move(identifier);
                                            changeRob(checkRob(selectedX, 0, 1, 2, 1), selectedY);
                                        }
                                    } else if (column == 3) {
                                        if (selectedX > 0) {
                                            gridSquares[selectedY][checkRob(selectedX, 1, 1, 3, 1)].move(identifier);
                                            changeRob(checkRob(selectedX, 1, 1, 3, 1), selectedY);
                                        }

                                    } else if (column == 4) {
                                        if (selectedX > 8) {
                                            gridSquares[selectedY][checkRob(selectedX, 9, 1, 3, 1)].move(identifier);
                                            changeRob(checkRob(selectedX, 9, 1, 3, 1), selectedY);
                                        }
                                        if (selectedX > 6 & selectedX < 9) {
                                            gridSquares[selectedY][checkRob(selectedX, 7, 1, 3, 1)].move(identifier);
                                            changeRob(checkRob(selectedX, 7, 1, 3, 1), selectedY);
                                        }
                                        if (selectedX < 7) {
                                            gridSquares[selectedY][checkRob(selectedX, 0, 1, 3, 1)].move(identifier);
                                            changeRob(checkRob(selectedX, 0, 1, 3, 1), selectedY);
                                        }
                                    } else if (column == 5) {
                                        if (selectedX > 12) {
                                            gridSquares[selectedY][checkRob(selectedX, 13, 1, 5, 1)].move(identifier);
                                            changeRob(checkRob(selectedX, 13, 1, 5, 1), selectedY);
                                        }
                                        if (selectedX < 13) {
                                            gridSquares[selectedY][checkRob(selectedX, 0, 1, 5, 1)].move(identifier);
                                            changeRob(checkRob(selectedX, 0, 1, 5, 1), selectedY);
                                        }
                                    } else if (column == 6) {
                                        if (selectedX > 13) {
                                            gridSquares[selectedY][checkRob(selectedX, 14, 1, 6, 1)].move(identifier);
                                            changeRob(checkRob(selectedX, 14, 1, 6, 1), selectedY);
                                        }
                                        if (selectedX < 14 & selectedX > 2) {
                                            gridSquares[selectedY][checkRob(selectedX, 3, 1, 6, 1)].move(identifier);
                                            changeRob(checkRob(selectedX, 3, 1, 6, 1), selectedY);
                                        }
                                        if (selectedX < 3) {
                                            gridSquares[selectedY][checkRob(selectedX, 0, 1, 6, 1)].move(identifier);
                                            changeRob(checkRob(selectedX, 0, 1, 6, 1), selectedY);
                                        }
                                    } else if (column == 7) {
                                        if (selectedX > 11) {
                                            if (identifier != 1 || checkRob(selectedX, 11, 1, 7, 1) != 11) {
                                                gridSquares[selectedY][checkRob(selectedX, 9, 1, 7, 1)]
                                                        .move(identifier);
                                                changeRob(checkRob(selectedX, 9, 1, 7, 1), selectedY);
                                            } else if (identifier == 1 & checkRob(selectedX, 11, 1, 7, 1) == 11) {
                                                reflect(selectedX, column, 0, 1, 11);
                                            }
                                        }
                                        if (selectedX < 7) {
                                            if (identifier != 4 || checkRob(selectedX, 5, 1, 7, 1) != 5) {
                                                gridSquares[selectedY][checkRob(selectedX, 0, 1, 7, 1)]
                                                        .move(identifier);
                                                changeRob(checkRob(selectedX, 0, 1, 7, 1), selectedY);
                                            } else if (identifier == 4 & checkRob(selectedX, 5, 1, 7, 1) == 5) {
                                                reflect(selectedX, column, 0, 1, 5);

                                            }
                                        }
                                    } else if (column == 8) {
                                        if (selectedX > 8) {
                                            gridSquares[selectedY][checkRob(selectedX, 9, 1, 8, 1)].move(identifier);
                                            changeRob(checkRob(selectedX, 9, 1, 8, 1), selectedY);
                                        }
                                        if (selectedX < 6) {
                                            gridSquares[selectedY][checkRob(selectedX, 0, 1, 8, 1)].move(identifier);
                                            changeRob(checkRob(selectedX, 0, 1, 8, 1), selectedY);
                                        }
                                    } else if (column == 9) {
                                        if (selectedX > 10) {
                                            gridSquares[selectedY][checkRob(selectedX, 11, 1, column, 1)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 11, 1, column, 1), selectedY);
                                        }
                                        if (selectedX < 11 & selectedX > 1) {
                                            gridSquares[selectedY][checkRob(selectedX, 2, 1, column, 1)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 2, 1, column, 1), selectedY);
                                        }
                                        if (selectedX < 2) {
                                            gridSquares[selectedY][checkRob(selectedX, 0, 1, column, 1)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 0, 1, column, 1), selectedY);
                                        }
                                    } else if (column == 10) {
                                        if (selectedX > 7) {
                                            gridSquares[selectedY][checkRob(selectedX, 8, 1, column, 1)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 8, 1, column, 1), selectedY);
                                        }
                                        if (selectedX < 8) {
                                            gridSquares[selectedY][checkRob(selectedX, 0, 1, column, 1)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 0, 1, column, 1), selectedY);
                                        }
                                    } else if (column == 11) {
                                        if (selectedX > 12) {
                                            gridSquares[selectedY][checkRob(selectedX, 13, 1, column, 1)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 13, 1, column, 1), selectedY);
                                        }
                                        if (selectedX < 13) {
                                            gridSquares[selectedY][checkRob(selectedX, 0, 1, column, 1)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 0, 1, column, 1), selectedY);
                                        }
                                    } else if (column == 12) {

                                        if (selectedX > 9 || selectedX > 6) {
                                            if (identifier != 3 & identifier != 4) {
                                                gridSquares[selectedY][checkRob(selectedX, 4, 1, column, 1)]
                                                        .move(identifier);
                                                changeRob(checkRob(selectedX, 4, 1, column, 1), selectedY);
                                            } else if (identifier == 3 & checkRob(selectedX, 6, 1, column, 1) == 6) {
                                                reflect(selectedX, column, 0, 1, 6);
                                            } else if (selectedX > 9 & identifier == 4
                                                    & checkRob(selectedX, 9, 1, column, 1) == 9) {
                                                reflect(selectedX, column, 0, 1, 9);
                                            }
                                        }
                                        if (selectedX < 4) {
                                            gridSquares[selectedY][checkRob(selectedX, 0, 1, column, 1)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 0, 1, column, 1), selectedY);
                                        }
                                    } else if (column == 13) {
                                        if (selectedX > 8) {
                                            gridSquares[selectedY][checkRob(selectedX, 9, 1, column, 1)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 9, 1, column, 1), selectedY);
                                        }
                                        if (selectedX < 9 & selectedX > 2) {
                                            gridSquares[selectedY][checkRob(selectedX, 3, 1, column, 1)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 3, 1, column, 1), selectedY);
                                        }
                                        if (selectedX == 2) {
                                            if (identifier != 1 || checkRob(selectedX, 1, 1, column, 1) != 1) {
                                                gridSquares[selectedY][checkRob(selectedX, 0, 1, column, 1)]
                                                        .move(identifier);
                                                changeRob(checkRob(selectedX, 0, 1, column, 1), selectedY);
                                            }
                                            if (identifier == 1 & checkRob(selectedX, 1, 1, column, 1) == 1) {
                                                reflect(selectedX, column, 0, 1, 1);
                                            }
                                        }
                                        if (selectedX < 1) {
                                            gridSquares[selectedY][checkRob(selectedX, 0, 1, column, 1)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 0, 1, column, 1), selectedY);
                                        }
                                    } else if (column == 14) {
                                        if (selectedX > 11 & identifier == 2
                                                & checkRob(selectedX, 11, 1, column, 1) == 11) {
                                            reflect(selectedX, column, 0, 1, 11);
                                        } else if (selectedX > 5) {
                                            gridSquares[selectedY][checkRob(selectedX, 6, 1, column, 1)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 6, 1, column, 1), selectedY);
                                        }

                                        if (selectedX < 6) {
                                            gridSquares[selectedY][checkRob(selectedX, 0, 1, column, 1)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 0, 1, column, 1), selectedY);
                                        }
                                    } else if (column == 15) {
                                        if (selectedX > 10) {
                                            gridSquares[selectedY][checkRob(selectedX, 11, 1, column, 1)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 11, 1, column, 1), selectedY);
                                        }
                                        if (selectedX < 11 & selectedX > 6) {
                                            gridSquares[selectedY][checkRob(selectedX, 7, 1, column, 1)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 7, 1, column, 1), selectedY);
                                        }
                                        if (selectedX < 7) {
                                            gridSquares[selectedY][checkRob(selectedX, 0, 1, column, 1)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 0, 1, column, 1), selectedY);
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
                                        if (selectedX > 8) {
                                            gridSquares[selectedY][checkRob(selectedX, 15, 1, column, 2)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 15, 1, column, 2), selectedY);
                                        } else if (selectedX < 9 & selectedX > 5) {
                                            gridSquares[selectedY][checkRob(selectedX, 8, 1, column, 2)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 8, 1, column, 2), selectedY);
                                        } else if (selectedX < 6) {
                                            gridSquares[selectedY][checkRob(selectedX, 5, 1, column, 2)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 5, 1, column, 2), selectedY);
                                        }
                                    } else if (column == 1) {
                                        if (selectedX > 9) {
                                            gridSquares[selectedY][checkRob(selectedX, 15, 1, column, 2)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 15, 1, column, 2), selectedY);
                                        }
                                        if (selectedX < 4) {
                                            if (identifier != 3 || checkRob(selectedX, 4, 1, column, 1) != 4) {
                                                gridSquares[selectedY][checkRob(selectedX, 9, 1, column, 2)]
                                                        .move(identifier);
                                                changeRob(checkRob(selectedX, 9, 1, column, 2), selectedY);
                                            }
                                            if (identifier == 3 & checkRob(selectedX, 4, 1, column, 1) == 4) {
                                                reflect(selectedX, column, 1, 1, 4);
                                            }

                                        }
                                    } else if (column == 2) {
                                        if (selectedX < 14) {
                                            if (identifier != 2 || checkRob(selectedX, 14, 1, column, 1) != 14) {
                                                gridSquares[selectedY][checkRob(selectedX, 15, 1, column, 2)]
                                                        .move(identifier);
                                                changeRob(checkRob(selectedX, 15, 1, column, 2), selectedY);
                                            }
                                            if (identifier == 2 & checkRob(selectedX, 14, 1, column, 1) == 14) {
                                                reflect(selectedX, column, 1, 1, 14);
                                            }

                                        }
                                    } else if (column == 3) {
                                        if (selectedX > 0) {
                                            gridSquares[selectedY][checkRob(selectedX, 15, 1, column, 2)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 15, 1, column, 2), selectedY);
                                        }
                                        if (selectedX == 0) {
                                            gridSquares[selectedY][checkRob(selectedX, 0, 1, column, 2)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 0, 1, column, 2), selectedY);
                                        }
                                    } else if (column == 4) {
                                        if (selectedX > 8) {
                                            gridSquares[selectedY][checkRob(selectedX, 15, 1, column, 2)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 15, 1, column, 2), selectedY);
                                        }
                                        if (selectedX < 9 & selectedX > 6) {
                                            gridSquares[selectedY][checkRob(selectedX, 8, 1, column, 2)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 8, 1, column, 2), selectedY);
                                        }
                                        if (selectedX < 7) {
                                            gridSquares[selectedY][checkRob(selectedX, 6, 1, column, 2)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 6, 1, column, 2), selectedY);
                                        }
                                    } else if (column == 5) {
                                        if (selectedX > 12) {
                                            gridSquares[selectedY][checkRob(selectedX, 15, 1, column, 2)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 15, 1, column, 2), selectedY);
                                        }
                                        if (selectedX < 13) {
                                            gridSquares[selectedY][checkRob(selectedX, 12, 1, column, 2)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 12, 1, column, 2), selectedY);
                                        }
                                    } else if (column == 6) {
                                        if (selectedX > 13) {
                                            gridSquares[selectedY][checkRob(selectedX, 15, 1, column, 2)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 15, 1, column, 2), selectedY);
                                        }
                                        if (selectedX < 14 & selectedX > 2) {
                                            gridSquares[selectedY][checkRob(selectedX, 13, 1, column, 2)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 13, 1, column, 2), selectedY);
                                        }
                                        if (selectedX < 3) {
                                            gridSquares[selectedY][checkRob(selectedX, 2, 1, column, 2)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 2, 1, column, 2), selectedY);
                                        }
                                    } else if (column == 7) {
                                        if (selectedX > 11) {
                                            gridSquares[selectedY][checkRob(selectedX, 15, 1, column, 2)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 15, 1, column, 2), selectedY);
                                        }
                                        if (selectedX < 5) {
                                            if (identifier != 4 || checkRob(selectedX, 5, 1, column, 1) != 5) {
                                                gridSquares[selectedY][checkRob(selectedX, 6, 1, column, 2)]
                                                        .move(identifier);
                                                changeRob(checkRob(selectedX, 6, 1, column, 2), selectedY);
                                            }
                                            if (identifier == 4 & checkRob(selectedX, 5, 1, column, 1) == 5) {
                                                reflect(selectedX, column, 1, 1, 5);
                                            }

                                        }
                                        if (selectedX < 11 & selectedX > 8) {
                                            if (identifier != 1 || checkRob(selectedX, 11, 1, column, 1) != 11) {
                                                gridSquares[selectedY][checkRob(selectedX, 15, 1, column, 2)]
                                                        .move(identifier);
                                                changeRob(checkRob(selectedX, 15, 1, column, 2), selectedY);
                                            }
                                            if (identifier == 1 & checkRob(selectedX, 11, 1, column, 1) == 11) {
                                                reflect(selectedX, column, 1, 1, 11);
                                            }

                                        }
                                    } else if (column == 8) {
                                        if (selectedX > 8) {
                                            gridSquares[selectedY][checkRob(selectedX, 15, 1, column, 2)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 15, 1, column, 2), selectedY);
                                        }

                                        if (selectedX < 7) {
                                            gridSquares[selectedY][checkRob(selectedX, 6, 1, column, 2)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 6, 1, column, 2), selectedY);
                                        }
                                    } else if (column == 9) {
                                        if (selectedX > 10) {
                                            gridSquares[selectedY][checkRob(selectedX, 15, 1, column, 2)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 15, 1, column, 2), selectedY);
                                        }
                                        if (selectedX < 11 & selectedX > 1) {
                                            gridSquares[selectedY][checkRob(selectedX, 10, 1, column, 2)]
                                                    .move(identifier);
                                            changeRob(10, selectedY);
                                        }
                                        if (selectedX < 2) {
                                            gridSquares[selectedY][checkRob(selectedX, 1, 1, column, 2)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 1, 1, column, 2), selectedY);
                                        }
                                    } else if (column == 10) {
                                        if (selectedX > 7) {
                                            gridSquares[selectedY][checkRob(selectedX, 15, 1, column, 2)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 15, 1, column, 2), selectedY);
                                        }
                                        if (selectedX < 8) {
                                            gridSquares[selectedY][checkRob(selectedX, 7, 1, column, 2)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 7, 1, column, 2), selectedY);
                                        }
                                    } else if (column == 11) {
                                        if (selectedX > 12) {
                                            gridSquares[selectedY][checkRob(selectedX, 15, 1, column, 2)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 15, 1, column, 2), selectedY);
                                        }
                                        if (selectedX < 13) {
                                            gridSquares[selectedY][checkRob(selectedX, 12, 1, column, 2)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 12, 1, column, 2), selectedY);
                                        }
                                    } else if (column == 12) {
                                        if (selectedX > 3 & selectedX < 9) {
                                            if (identifier != 3 & identifier != 4) {
                                                gridSquares[selectedY][checkRob(selectedX, 15, 1, column, 2)]
                                                        .move(identifier);
                                                changeRob(checkRob(selectedX, 15, 1, column, 2), selectedY);
                                            } else if (identifier == 3 & checkRob(selectedX, 6, 1, column, 1) == 6
                                                    & selectedX < 6) {
                                                reflect(selectedX, column, 1, 1, 6);
                                            } else if (identifier == 4 & checkRob(selectedX, 9, 1, column, 1) == 9) {
                                                reflect(selectedX, column, 1, 1, 9);
                                            } else if (identifier == 3 & selectedX > 6) {
                                                gridSquares[selectedY][checkRob(selectedX, 15, 1, column, 2)]
                                                        .move(identifier);
                                                changeRob(checkRob(selectedX, 15, 1, column, 2), selectedY);
                                            }
                                        }

                                        if (selectedX < 4) {
                                            gridSquares[selectedY][checkRob(selectedX, 3, 1, column, 2)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 3, 1, column, 2), selectedY);
                                        }
                                    } else if (column == 13) {
                                        if (selectedX > 8) {
                                            gridSquares[selectedY][checkRob(selectedX, 15, 1, column, 2)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 15, 1, column, 2), selectedY);
                                        }
                                        if (selectedX < 9 & selectedX > 2) {
                                            gridSquares[selectedY][checkRob(selectedX, 8, 1, column, 2)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 8, 1, column, 2), selectedY);
                                        }
                                        if (selectedX == 2) {
                                            gridSquares[selectedY][checkRob(selectedX, 2, 1, column, 2)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 2, 1, column, 2), selectedY);
                                        }
                                        if (selectedX < 1) {
                                            if (identifier != 1 || checkRob(selectedX, 1, 1, column, 1) != 1) {
                                                gridSquares[selectedY][checkRob(selectedX, 2, 1, column, 2)]
                                                        .move(identifier);
                                                changeRob(checkRob(selectedX, 2, 1, column, 2), selectedY);
                                            } else if (identifier == 1 & checkRob(selectedX, 1, 1, column, 1) == 1) {
                                                reflect(selectedX, column, 1, 1, 1);
                                            }

                                        }
                                    } else if (column == 14) {
                                        if (selectedX > 11) {
                                            gridSquares[selectedY][checkRob(selectedX, 15, 1, column, 2)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 15, 1, column, 2), selectedY);
                                        }
                                        if (selectedX < 6) {
                                            gridSquares[selectedY][checkRob(selectedX, 5, 1, column, 2)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 5, 1, column, 2), selectedY);
                                        }
                                        if (selectedX < 11 & selectedX > 5) {
                                            if (identifier != 2 || checkRob(selectedX, 11, 1, column, 1) != 11) {
                                                gridSquares[selectedY][checkRob(selectedX, 15, 1, column, 2)]
                                                        .move(identifier);
                                                changeRob(checkRob(selectedX, 15, 1, column, 2), selectedY);
                                            } else if (identifier == 1 & checkRob(selectedX, 11, 1, column, 1) == 11) {
                                                reflect(selectedX, column, 1, 1, 11);
                                            }
                                        }
                                    } else if (column == 15) {
                                        if (selectedX > 10) {
                                            gridSquares[selectedY][checkRob(selectedX, 15, 1, column, 2)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 15, 1, column, 2), selectedY);
                                        }
                                        if (selectedX < 11 & selectedX > 6) {
                                            gridSquares[selectedY][checkRob(selectedX, 10, 1, column, 2)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 10, 1, column, 2), selectedY);
                                        }
                                        if (selectedX < 7) {
                                            gridSquares[selectedY][checkRob(selectedX, 6, 1, column, 2)]
                                                    .move(identifier);
                                            changeRob(checkRob(selectedX, 6, 1, column, 2), selectedY);
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
                                        if (selectedY > 10) {
                                            gridSquares[checkRob(selectedY, 11, 2, row, 1)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 11, 2, row, 1));
                                        }
                                        if (selectedY < 11 & selectedY > 5) {
                                            gridSquares[checkRob(selectedY, 6, 2, row, 1)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 6, 2, row, 1));
                                        }
                                        if (selectedY < 6) {
                                            gridSquares[checkRob(selectedY, 0, 2, row, 1)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 0, 2, row, 1));
                                        }
                                    } else if (row == 1) {
                                        if (selectedY > 2 & selectedY < 13) {
                                            gridSquares[checkRob(selectedY, 3, 2, row, 1)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 3, 2, row, 1));
                                        }
                                        if (selectedY < 3) {
                                            gridSquares[checkRob(selectedY, 0, 2, row, 1)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 0, 2, row, 1));
                                        }
                                        if (selectedY > 13) {
                                            if (identifier != 1 || checkRob(selectedY, 13, 2, row, 1) != 13) {
                                                gridSquares[checkRob(selectedY, 3, 2, row, 1)][selectedX]
                                                        .move(identifier);
                                                changeRob(selectedX, checkRob(selectedY, 3, 2, row, 1));
                                            }
                                            if (identifier == 1 & checkRob(selectedY, 13, 2, row, 1) == 13) {
                                                reflect(selectedY, row, 0, 2, 13);
                                            }
                                        }
                                    } else if (row == 2) {
                                        if (selectedY > 9) {
                                            gridSquares[checkRob(selectedY, 10, 2, row, 1)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 10, 2, row, 1));
                                        }
                                        if (selectedY < 10 & selectedY > 5) {
                                            gridSquares[checkRob(selectedY, 6, 2, row, 1)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 6, 2, row, 1));
                                        }
                                        if (selectedY < 6) {
                                            gridSquares[checkRob(selectedY, 0, 2, row, 1)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 0, 2, row, 1));
                                        }

                                    } else if (row == 3) {
                                        if (selectedY > 12) {
                                            gridSquares[checkRob(selectedY, 13, 2, row, 1)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 13, 2, row, 1));
                                        }
                                        if (selectedY < 13 & selectedY > 6) {
                                            gridSquares[checkRob(selectedY, 7, 2, row, 1)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 7, 2, row, 1));
                                        }
                                        if (selectedY < 7) {
                                            gridSquares[checkRob(selectedY, 0, 2, row, 1)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 0, 2, row, 1));
                                        }

                                    } else if (row == 4) {

                                        if (selectedY > 1) {
                                            if (identifier != 3 || checkRob(selectedY, 1, 2, row, 1) != 1) {
                                                gridSquares[checkRob(selectedY, 0, 2, row, 1)][selectedX]
                                                        .move(identifier);
                                                changeRob(selectedX, checkRob(selectedY, 0, 2, row, 1));
                                            }
                                            if (identifier == 3 & checkRob(selectedY, 1, 2, row, 1) == 1) {
                                                reflect(selectedY, row, 0, 2, 1);

                                            }
                                        }

                                    } else if (row == 5) {
                                        if (selectedY > 13) {
                                            gridSquares[checkRob(selectedY, 14, 2, row, 1)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 14, 2, row, 1));
                                        }
                                        if (selectedY < 7) {
                                            gridSquares[checkRob(selectedY, 0, 2, row, 1)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 0, 2, row, 1));
                                        }
                                        if (selectedY < 14 & selectedY > 7) {
                                            if (identifier != 4 || checkRob(selectedY, 5, 2, row, 1) != 5) {
                                                gridSquares[checkRob(selectedY, 0, 2, row, 1)][selectedX]
                                                        .move(identifier);
                                                changeRob(selectedX, checkRob(selectedY, 0, 2, row, 1));
                                            }
                                            if (identifier == 4 & checkRob(selectedY, 5, 2, row, 1) == 5) {
                                                reflect(selectedY, row, 0, 2, 5);

                                            }
                                        }
                                    } else if (row == 6) {
                                        if (selectedY > 4 & selectedY < 12) {
                                            gridSquares[checkRob(selectedY, 5, 2, row, 1)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 5, 2, row, 1));
                                        }
                                        if (selectedY < 5) {
                                            gridSquares[checkRob(selectedY, 0, 2, row, 1)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 0, 2, row, 1));
                                        }
                                        if (selectedY > 12) {
                                            if (identifier != 3 || checkRob(selectedY, row, 2, row, 1) != row) {
                                                gridSquares[checkRob(selectedY, 5, 2, row, 1)][selectedX]
                                                        .move(identifier);
                                                changeRob(selectedX, checkRob(selectedY, 5, 2, row, 1));
                                            }
                                            if (identifier == 3 & checkRob(selectedY, row, 2, row, 1) == row) {
                                                reflect(selectedY, row, 0, 2, 6);

                                            }
                                        }
                                    } else if (row == 7) {
                                        if (selectedY > 9) {
                                            gridSquares[checkRob(selectedY, 10, 2, row, 1)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 10, 2, row, 1));
                                        }
                                        if (selectedY < 7) {
                                            gridSquares[checkRob(selectedY, 0, 2, row, 1)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 0, 2, row, 1));
                                        }

                                    } else if (row == 8) {
                                        if (selectedY > 8) {
                                            gridSquares[checkRob(selectedY, 9, 2, row, 1)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 9, 2, row, 1));
                                        }
                                        if (selectedY < 7 & selectedY > 4) {
                                            gridSquares[checkRob(selectedY, 5, 2, row, 1)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 5, 2, row, 1));
                                        }
                                        if (selectedY < 5) {
                                            gridSquares[checkRob(selectedY, 0, 2, row, 1)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 0, 2, row, 1));
                                        }

                                    } else if (row == 9) {
                                        if (selectedY > 12) {
                                            gridSquares[checkRob(selectedY, 13, 2, row, 1)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 13, 2, row, 1));
                                        }
                                        if (selectedY < 12) {
                                            gridSquares[checkRob(selectedY, 0, 2, row, 1)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 0, 2, row, 1));
                                        }

                                    } else if (row == 10) {
                                        if (selectedY > 9) {
                                            gridSquares[checkRob(selectedY, 10, 2, row, 1)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 10, 2, row, 1));
                                        }
                                        if (selectedY < 10 & selectedY > 0) {
                                            gridSquares[checkRob(selectedY, 1, 2, row, 1)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 1, 2, row, 1));
                                        }

                                    } else if (row == 11) {
                                        if (selectedY > 7 & selectedY < 14) {
                                            if (identifier != 1 || checkRob(selectedY, 7, 2, row, 1) != 7) {
                                                gridSquares[checkRob(selectedY, 0, 2, row, 1)][selectedX]
                                                        .move(identifier);
                                                changeRob(selectedX, checkRob(selectedY, 0, 2, row, 1));
                                            }
                                            if (identifier == 1 & checkRob(selectedY, 7, 2, row, 1) == 7) {
                                                reflect(selectedY, row, 0, 2, 7);
                                            }
                                        }
                                        if (selectedY > 14) {
                                            if (identifier == 1 & checkRob(selectedY, 7, 2, row, 1) == 7) {
                                                reflect(selectedY, row, 0, 2, 7);
                                            } else if (identifier != 2 || checkRob(selectedY, 14, 2, row, 1) != 14) {
                                                gridSquares[checkRob(selectedY, 0, 2, row, 1)][selectedX]
                                                        .move(identifier);
                                                changeRob(selectedX, checkRob(selectedY, 0, 2, row, 1));
                                            } else if (identifier == 2 & checkRob(selectedY, 14, 2, row, 1) == 14) {
                                                reflect(selectedY, row, 0, 2, 14);

                                            }
                                        }
                                        if (selectedY < 7) {
                                            gridSquares[checkRob(selectedY, 0, 2, row, 1)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 0, 2, row, 1));
                                        }
                                    } else if (row == 12) {
                                        if (selectedY > 10) {
                                            gridSquares[checkRob(selectedY, 11, 2, row, 1)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 11, 2, row, 1));
                                        }

                                        if (selectedY < 11) {
                                            gridSquares[checkRob(selectedY, 0, 2, row, 1)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 0, 2, row, 1));
                                        }

                                    } else if (row == 13) {
                                        if (selectedY > 11) {
                                            gridSquares[checkRob(selectedY, 12, 2, row, 1)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 12, 2, row, 1));
                                        }
                                        if (selectedY < 12 & selectedY > 5) {
                                            gridSquares[checkRob(selectedY, 6, 2, row, 1)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 6, 2, row, 1));
                                        }
                                        if (selectedY < 6) {
                                            gridSquares[checkRob(selectedY, 0, 2, row, 1)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 0, 2, row, 1));
                                        }

                                    } else if (row == 14) {

                                        if (selectedY > 2) {
                                            if (identifier != 2 || checkRob(selectedY, 2, 2, row, 1) != 2) {
                                                gridSquares[checkRob(selectedY, 0, 2, row, 1)][selectedX]
                                                        .move(identifier);
                                                changeRob(selectedX, checkRob(selectedY, 0, 2, row, 1));
                                            }
                                            if (identifier == 2 & checkRob(selectedY, 2, 2, row, 1) == 2) {
                                                reflect(selectedY, row, 0, 2, 2);
                                            }
                                        }
                                        if (selectedY < 2) {
                                            gridSquares[checkRob(selectedY, 0, 2, row, 1)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 0, 2, row, 1));
                                        }

                                    } else if (row == 15) {
                                        if (selectedY > 12) {
                                            gridSquares[checkRob(selectedY, 13, 2, row, 1)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 13, 2, row, 1));
                                        }
                                        if (selectedY < 13 & selectedY > 2) {
                                            gridSquares[checkRob(selectedY, 3, 2, row, 1)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 3, 2, row, 1));
                                        }
                                        if (selectedY < 3) {
                                            gridSquares[checkRob(selectedY, 0, 2, row, 1)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 0, 2, row, 1));
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
                                        if (selectedY > 10) {
                                            gridSquares[checkRob(selectedY, 15, 2, row, 2)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 15, 2, row, 2));
                                        }
                                        if (selectedY < 11 & selectedY > 5) {
                                            gridSquares[checkRob(selectedY, 10, 2, row, 2)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 10, 2, row, 2));
                                        }
                                        if (selectedY < 6) {
                                            gridSquares[checkRob(selectedY, 5, 2, row, 2)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 5, 2, row, 2));
                                        }
                                    } else if (row == 1) {
                                        if (selectedY > 13) {
                                            gridSquares[checkRob(selectedY, 15, 2, row, 2)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 15, 2, row, 2));
                                        }
                                        if (selectedY > 2 & selectedY < 13) {
                                            if (identifier != 1 || checkRob(selectedY, 13, 2, row, 2) != 13) {
                                                gridSquares[checkRob(selectedY, 15, 2, row, 2)][selectedX]
                                                        .move(identifier);
                                                changeRob(selectedX, checkRob(selectedY, 15, 2, row, 2));
                                            }
                                            if (identifier == 1 & checkRob(selectedY, 13, 2, row, 2) == 13) {
                                                reflect(selectedY, row, 1, 2, 13);
                                            }
                                        }
                                        if (selectedY < 3) {
                                            gridSquares[checkRob(selectedY, 2, 2, row, 2)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 2, 2, row, 2));
                                        }

                                    } else if (row == 2) {
                                        if (selectedY > 9) {
                                            gridSquares[checkRob(selectedY, 15, 2, row, 2)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 15, 2, row, 2));
                                        }
                                        if (selectedY < 10 & selectedY > 5) {
                                            gridSquares[checkRob(selectedY, 9, 2, row, 2)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 9, 2, row, 2));
                                        }
                                        if (selectedY < 6) {
                                            gridSquares[checkRob(selectedY, 5, 2, row, 2)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 5, 2, row, 2));
                                        }

                                    } else if (row == 3) {
                                        if (selectedY > 12) {
                                            gridSquares[checkRob(selectedY, 15, 2, row, 2)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 15, 2, row, 2));
                                        }
                                        if (selectedY < 13 & selectedY > 6) {
                                            gridSquares[checkRob(selectedY, 12, 2, row, 2)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 12, 2, row, 2));
                                        }
                                        if (selectedY < 7) {
                                            gridSquares[checkRob(selectedY, 6, 2, row, 2)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 6, 2, row, 2));
                                        }

                                    } else if (row == 4) {
                                        if (selectedY > 1) {
                                            gridSquares[checkRob(selectedY, 15, 2, row, 2)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 15, 2, row, 2));
                                        }
                                        if (selectedY < 1) {
                                            if (identifier != 3 || checkRob(selectedY, 1, 2, row, 2) != 1) {
                                                gridSquares[checkRob(selectedY, 15, 2, row, 2)][selectedX]
                                                        .move(identifier);
                                                changeRob(selectedX, checkRob(selectedY, 15, 2, row, 2));
                                            }
                                            if (identifier == 3 & checkRob(selectedY, 1, 2, row, 2) == 1) {
                                                reflect(selectedY, row, 1, 2, 1);
                                            }
                                        }

                                    } else if (row == 5) {
                                        if (selectedY > 13) {
                                            gridSquares[checkRob(selectedY, 15, 2, row, 2)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 15, 2, row, 2));
                                        }
                                        if (selectedY < 14 & selectedY > 7) {
                                            gridSquares[checkRob(selectedY, 13, 2, row, 2)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 13, 2, row, 2));
                                        }
                                        if (selectedY < 7) {
                                            if (identifier != 4 || checkRob(selectedY, 7, 2, row, 2) != 7) {
                                                gridSquares[checkRob(selectedY, 13, 2, row, 2)][selectedX]
                                                        .move(identifier);
                                                changeRob(selectedX, checkRob(selectedY, 13, 2, row, 2));
                                            }
                                            if (identifier == 4 & checkRob(selectedY, 7, 2, row, 2) == 7) {
                                                reflect(selectedY, row, 1, 2, 7);
                                            }
                                        }
                                    } else if (row == 6) {
                                        if (selectedY > 12) {
                                            gridSquares[checkRob(selectedY, 15, 2, row, 2)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 15, 2, row, 2));
                                        }

                                        if (selectedY < 5) {
                                            gridSquares[checkRob(selectedY, 4, 2, row, 2)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 4, 2, row, 2));
                                        }
                                        if (selectedY < 12 & selectedY > 4) {
                                            if (identifier != 3 || checkRob(selectedY, 12, 2, row, 2) != 12) {
                                                gridSquares[checkRob(selectedY, 15, 2, row, 2)][selectedX]
                                                        .move(identifier);
                                                changeRob(selectedX, checkRob(selectedY, 15, 2, row, 2));
                                            }
                                            if (identifier == 3 & checkRob(selectedY, 12, 2, row, 2) == 12) {
                                                reflect(selectedY, row, 1, 2, 12);
                                            }
                                        }
                                    } else if (row == 7) {
                                        if (selectedY > 9) {
                                            gridSquares[checkRob(selectedY, 15, 2, row, 2)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 15, 2, row, 2));
                                        }
                                        if (selectedY < 10 & selectedY > 8) {
                                            gridSquares[checkRob(selectedY, 9, 2, row, 2)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 9, 2, row, 2));
                                        }
                                        if (selectedY < 7) {
                                            gridSquares[checkRob(selectedY, 6, 2, row, 2)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 6, 2, row, 2));
                                        }

                                    } else if (row == 8) {
                                        if (selectedY > 8) {
                                            gridSquares[checkRob(selectedY, 15, 2, row, 2)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 15, 2, row, 2));
                                        }
                                        if (selectedY < 7 & selectedY > 4) {
                                            gridSquares[checkRob(selectedY, 6, 2, row, 2)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 6, 2, row, 2));
                                        }
                                        if (selectedY < 5) {
                                            gridSquares[checkRob(selectedY, 4, 2, row, 2)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 4, 2, row, 2));
                                        }

                                    } else if (row == 9) {
                                        if (selectedY > 12) {
                                            gridSquares[checkRob(selectedY, 15, 2, row, 2)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 15, 2, row, 2));
                                        }
                                        if (selectedY < 12) {
                                            if (identifier != 4 || checkRob(selectedY, 12, 2, row, 2) != 12) {
                                                gridSquares[checkRob(selectedY, 11, 2, row, 2)][selectedX]
                                                        .move(identifier);
                                                changeRob(selectedX, checkRob(selectedY, 11, 2, row, 2));
                                            }
                                            if (identifier == 4 & checkRob(selectedY, 12, 2, row, 2) == 12) {
                                                reflect(selectedY, row, 1, 2, 12);
                                            }
                                        }
                                    } else if (row == 10) {
                                        if (selectedY > 9) {
                                            gridSquares[checkRob(selectedY, 15, 2, row, 2)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 15, 2, row, 2));
                                        }
                                        if (selectedY < 10 & selectedY > 0) {
                                            gridSquares[checkRob(selectedY, 9, 2, row, 2)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 9, 2, row, 2));
                                        }
                                        if (selectedY < 1) {
                                            gridSquares[checkRob(selectedY, 0, 2, row, 2)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 0, 2, row, 2));
                                        }

                                    } else if (row == 11) {
                                        if (selectedY > 14) {
                                            gridSquares[checkRob(selectedY, 15, 2, row, 2)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 15, 2, row, 2));
                                        }

                                        if (selectedY < 14) {
                                            if (selectedY < 7 & identifier == 1
                                                    & checkRob(selectedY, 7, 2, row, 2) == 7) {
                                                reflect(selectedY, row, 1, 2, 7);
                                            } else if (identifier != 2 || checkRob(selectedY, 14, 2, row, 2) != 14) {
                                                gridSquares[checkRob(selectedY, 15, 2, row, 2)][selectedX]
                                                        .move(identifier);
                                                changeRob(selectedX, checkRob(selectedY, 15, 2, row, 2));
                                            } else if (identifier == 2 & checkRob(selectedY, 14, 2, row, 2) == 14) {
                                                reflect(selectedY, row, 1, 2, 14);
                                            }
                                        }
                                    } else if (row == 12) {

                                        if (selectedY > 10) {
                                            gridSquares[checkRob(selectedY, 15, 2, row, 2)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 15, 2, row, 2));
                                        }
                                        if (selectedY < 11) {
                                            gridSquares[checkRob(selectedY, 10, 2, row, 2)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 10, 2, row, 2));
                                        }

                                    } else if (row == 13) {
                                        if (selectedY > 11) {
                                            gridSquares[checkRob(selectedY, 15, 2, row, 2)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 15, 2, row, 2));
                                        }
                                        if (selectedY < 12 & selectedY > 5) {
                                            gridSquares[checkRob(selectedY, 11, 2, row, 2)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 11, 2, row, 2));
                                        }
                                        if (selectedY < 6) {
                                            gridSquares[checkRob(selectedY, 5, 2, row, 2)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 5, 2, row, 2));
                                        }

                                    } else if (row == 14) {
                                        if (selectedY > 2) {
                                            gridSquares[checkRob(selectedY, 15, 2, row, 2)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 15, 2, row, 2));
                                        }
                                        if (selectedY < 2) {
                                            if (identifier != 2 || checkRob(selectedY, 2, 2, row, 2) != 2) {
                                                gridSquares[checkRob(selectedY, 15, 2, row, 2)][selectedX]
                                                        .move(identifier);
                                                changeRob(selectedX, checkRob(selectedY, 15, 2, row, 2));
                                            }
                                            if (identifier == 2 & checkRob(selectedY, 2, 2, row, 2) == 2) {
                                                reflect(selectedY, row, 1, 2, 2);
                                            }
                                        }

                                    } else if (row == 15) {
                                        if (selectedY > 12) {
                                            gridSquares[checkRob(selectedY, 15, 2, row, 2)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 15, 2, row, 2));
                                        }
                                        if (selectedY < 13 & selectedY > 2) {
                                            gridSquares[checkRob(selectedY, 12, 2, row, 2)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 12, 2, row, 2));
                                        }
                                        if (selectedY < 3) {
                                            gridSquares[checkRob(selectedY, 2, 2, row, 2)][selectedX].move(identifier);
                                            changeRob(selectedX, checkRob(selectedY, 2, 2, row, 2));
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

        if (load) {
            if (once) {
                bid.RobotChangePos();
            }
            once = true;

        } else {

            bid.RobotChangePos();

        }

    }

    private void GameLoad(boolean Load) {

        if (Load) {

            LoadGame load = new LoadGame();
            load.Loading();

        } else {
            greenX = 13;
            greenY = 7; // Starting coords for the different robots
            blueX = 0;
            blueY = 0;
            yellowX = 10;
            yellowY = 3;
            redX = 3;
            redY = 11;
        }

    }
}

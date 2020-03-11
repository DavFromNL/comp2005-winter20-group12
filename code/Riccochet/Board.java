import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;
import java.util.concurrent.TimeUnit;
/*
 *  the main window of the gui
 *  notice that it extends JFrame - so we can add our own components
 *  notice that it implements ActionListener - so we can handle user input
 */
public class Board extends JFrame implements ActionListener
{
    // gui components that are contained in this frame:
    private JPanel topPanel, bottomPanel;   // top and bottom panels in the main window
    
    
    public Grid [][] gridSquares;  // squares to appear in grid formation in the bottom panel
    public static int x,y;                        // the size of the grid
    
    public int greenX = 13, greenY = 5; //Starting coords for the different robots
    public int blueX = 0, blueY = 0;
    public int yellowX = 10, yellowY = 3;
    public int redX = 3, redY = 11;
    public int selectedX, selectedY; //tracks the selected game square
    
    public boolean canMove = false; //determines wether a robot has been selected and a move can be made
    public int identifier=0; //identifies what color robot is selected or updated
    /*
     *  constructor method takes as input how many rows and columns of gridsquares to create
     *  it then creates the panels, their subcomponents and puts them all together in the main frame
     *  it makes sure that action listeners are added to selectable items
     *  it makes sure that the gui will be visible
     */
    public Board(int x, int y)
    {
        this.x = x;
        this.y = y;
        
        
        // first create the panels
        topPanel = new JPanel();
        topPanel.setLayout( new FlowLayout());
        
        bottomPanel = new JPanel();
        bottomPanel.setLayout( new GridLayout( x, y));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));


        
        
        
    
        // for the bottom panel:    
        // create the buttons and add them to the grid
        gridSquares = new Grid [x][y];
        
        for ( int column = 0; column < x; column ++)
        {
            for ( int row = 0; row < y; row ++)
            {
                gridSquares [column][row] = new Grid( x,y);
                gridSquares [column][row].setSize( 200, 40);
                
                gridSquares [column][row].setOpaque( true);             // without this line and the next the OS' default
                //gridSquares [column][row].setBorderPainted( false);     // look & feel will dominate / interfere
                                                                       // (try commenting each out & see what happens)
                
                
                gridSquares [column][row].addActionListener( this);     // AGAIN, don't forget this line!
                gridSquares [column][row].setText("Ѫ");
                Color colour =  new Color(220, 202, 152);
                gridSquares [column][row].setForeground(colour);
                gridSquares [column][row].setColor( column , row);
                bottomPanel.add( gridSquares [column][row]);
                }
        }
        
        // now add the top and bottom panels to the main frame
        getContentPane().setLayout( new BorderLayout());
        getContentPane().add( topPanel, BorderLayout.NORTH);
        getContentPane().add( bottomPanel, BorderLayout.SOUTH);
        pack();
        
        // housekeeping : behaviour
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        setResizable( false);
        setVisible( true);
    }
    
    
    
    
    /*
     *  if canMove is false compares the selected square to the 4 robots coords to see if the player has selected a robot
     */
    public void actionPerformed (ActionEvent aevt)
    {
        // get the object that was selected in the gui
        Object selected = aevt.getSource();
        //if canMove is false compares the selected square to the 4 robots coords to see if the player has selected a robot
         if(canMove==false){
            if( aevt.getSource()==gridSquares[redY][redX]){
            canMove = true;
            gridSquares[redY][redX].highlight();
            selectedY=redY;
            selectedX=redX;
            identifier=1;
           }
           if( aevt.getSource()==gridSquares[blueY][blueX]){
            canMove = true;
            gridSquares[blueY][blueX].highlight();
            selectedY=blueY;
            selectedX=blueX;
            identifier=2;
           }
           if( aevt.getSource()==gridSquares[greenY][greenX]){
            canMove = true;
            gridSquares[greenY][greenX].highlight();
            selectedY=greenY;
            selectedX=greenX;
            identifier=3;
           }
           if( aevt.getSource()==gridSquares[yellowY][yellowX]){
            canMove = true;
            gridSquares[yellowY][yellowX].highlight();
            selectedY=yellowY;
            selectedX=yellowX;
            identifier=4;
           }
           }
           //if canMove is true checks to see if the player has selected another robot. if the player hasnt selected another robot
           //it checks to see if a valid move has been made and the moves the robot
        else if(canMove==true){
               if( aevt.getSource()==gridSquares[redY][redX]){
            canMove = true;
            gridSquares[selectedY][selectedX].dehighlight();
            gridSquares[redY][redX].highlight();
            selectedY=redY;
            selectedX=redX;
            identifier=1;
           }
           if( aevt.getSource()==gridSquares[blueY][blueX]){
            canMove = true;
            gridSquares[selectedY][selectedX].dehighlight();
            gridSquares[blueY][blueX].highlight();
            selectedY=blueY;
            selectedX=blueX;
            identifier=2;
           }
           if( aevt.getSource()==gridSquares[greenY][greenX]){
            canMove = true;
            gridSquares[selectedY][selectedX].dehighlight();
            gridSquares[greenY][greenX].highlight();
            selectedY=greenY;
            selectedX=greenX;
            identifier=3;
           }
           if( aevt.getSource()==gridSquares[yellowY][yellowX]){
            canMove = true;
            gridSquares[selectedY][selectedX].dehighlight();
            gridSquares[yellowY][yellowX].highlight();
            selectedY=yellowY;
            selectedX=yellowX;
            identifier=4;
           }
           if(aevt.getSource()!=gridSquares[yellowY][yellowX] & aevt.getSource()!=gridSquares[redY][redX] & aevt.getSource()!=gridSquares[greenY][greenX] & aevt.getSource()!=gridSquares[blueY][blueX]){
               for ( int column = 0; column < 16; column ++){
                    for ( int row = 0; row < 16; row ++){
                        if ( aevt.getSource()==gridSquares[column][row])
                    {   
                        if(column != selectedY & row!= selectedX){
                            gridSquares[selectedY][selectedX].dehighlight();
                            canMove=false;
                            selectedY=20;
                            selectedX=20;
                            
                        }
                        else{if(column == selectedY & row< selectedX){
                            gridSquares[selectedY][selectedX].makeMove();
                            gridSquares[selectedY][0].move(identifier);
                            if(identifier == 1){
                                redY=selectedY;
                                redX=0;
                            }
                            if(identifier == 2){
                                blueY=selectedY;
                                blueX=0;
                            }
                            if(identifier == 3){
                                greenY=selectedY;
                                greenX=0;
                            }
                            if(identifier == 4){
                                yellowY=selectedY;
                                yellowX=0;
                            }
                            canMove=false;
                        }
                        
                        if(column == selectedY & row> selectedX){
                            gridSquares[selectedY][selectedX].makeMove();
                            gridSquares[selectedY][15].move(identifier);
                            if(identifier == 1){
                                redY=selectedY;
                                redX=15;
                            }
                            if(identifier == 2){
                                blueY=selectedY;
                                blueX=15;
                            }
                            if(identifier == 3){
                                greenY=selectedY;
                                greenX=15;
                            }
                            if(identifier == 4){
                                yellowY=selectedY;
                                yellowX=15;
                            }
                            canMove=false;
                        }
                        
                        if(row == selectedX & column< selectedY){
                            gridSquares[selectedY][selectedX].makeMove();
                            gridSquares[0][selectedX].move(identifier);
                            if(identifier == 1){
                                redY=0;
                                redX=selectedX;
                            }
                            if(identifier == 2){
                                blueY=0;
                                blueX=selectedX;
                            }
                            if(identifier == 3){
                                greenY=0;
                                greenX=selectedX;
                            }
                            if(identifier == 4){
                                yellowY=0;
                                yellowX=selectedX;
                            }
                            canMove=false;
                        }
                        
                        if(row == selectedX & column> selectedY){
                            gridSquares[selectedY][selectedX].makeMove();
                            gridSquares[15][selectedX].move(identifier);
                            if(identifier == 1){
                                redY=15;
                                redX=selectedX;
                            }
                            if(identifier == 2){
                                blueY=15;
                                blueX=selectedX;
                            }
                            if(identifier == 3){
                                greenY=15;
                                greenX=selectedX;
                            }
                            if(identifier == 4){
                                yellowY=15;
                                yellowX=selectedX;
                            }
                            canMove=false;
                        }
                    }
                    }
                }
            
        
          }
        
        
        
        
    
       }   
     }
   }
}

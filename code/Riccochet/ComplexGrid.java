/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 

import java.awt.Color;

import javax.swing.*;

/*
 *  a simple extension of JButton which allows the background colour to be set and switched back and forth with ease
 *  
 *  there are other ways of doing this, but it's a neat way to demonstrate how to create your own gui components
 *  (as well as how to use ternary operators)
 */
public class ComplexGrid extends JButton
{
    private int xcoord, ycoord;         // not used in this demo, but might be helpful in future...
    
    // constructor takes the x and y coordinates of this square
    public ComplexGrid( int xcoord, int ycoord)
    {
        super();
        this.xcoord = xcoord;
        this.ycoord = ycoord;
    }
    public void highlight(){
        //Highlights the square a robot is on when selected
        Color highlight = new Color(255, 255, 237);
        this.setBackground(highlight);
        
    }
    public void dehighlight(){
        ///Dehighlights the square when another selection is made
        Color colour = new Color(220, 202, 152);
        this.setBackground( colour);
        
    }
    public void makeMove(){
        //turns the color of the robots starting square back to the board color
        Color colour = new Color(220, 202, 152);
        this.setBackground( colour);
        this.setForeground( colour);
    }
    public void move(int identifier){
        //makes the robot visual in its new location 
        if(identifier == 1){
            this.setForeground(Color.red);
        }
        if(identifier == 2){
            this.setForeground(Color.blue);
        }
        if(identifier == 3){
            this.setForeground(Color.green);
        }
        if(identifier == 4){
            this.setForeground(Color.yellow);
        }
    }
    //Loads game board and robots
    public void setColor( int decider, int decider2, int redX, int redY, int blueX, int blueY, int greenX, int greenY, int yellowX, int yellowY)
    {
        Color colour = new Color(220, 202, 152);
        this.setBackground( colour);
        if(decider==2 & decider2==1){
          this.setText("X");
          this.setForeground(Color.red);
          }
        if(decider==redY & decider2==redX){
          this.setText("O");
          this.setForeground(Color.red);
          }
        if(decider==blueY & decider2==blueX){
          this.setText("O");
          this.setForeground(Color.blue);
          }
        if(decider==greenY & decider2==greenX){
          this.setText("O");
          
          this.setForeground(Color.green);
          }
        if(decider==yellowY & decider2==yellowX){
          this.setText("O");
          
        this.setForeground(Color.yellow);
          }
        if(decider==0 & decider2==5){
          
          this.setBorder(BorderFactory.createMatteBorder(
                                    0, 0, 0, 2, Color.black));
          }
        if(decider== 0 & decider2==6){
          
          
          this.setBorder(BorderFactory.createMatteBorder(
                                    0, 2, 0, 0, Color.black));
          }
        if(decider== 0 & decider2==9){
          
          
          this.setBorder(BorderFactory.createMatteBorder(
                                    0, 2, 0, 0, Color.black));
          }
        if(decider== 0 & decider2==8){
          
          
          this.setBorder(BorderFactory.createMatteBorder(
                                    0, 0, 0, 2, Color.black));
          }
        if(decider==15 & decider2==6){
          
          this.setBorder(BorderFactory.createMatteBorder(
                                    0, 0, 0, 2, Color.black));
          }
        if(decider== 15 & decider2==7){
          
          
          this.setBorder(BorderFactory.createMatteBorder(
                                    0, 2, 0, 0, Color.black));
          }
        if(decider== 15 & decider2==11){
          
          
          this.setBorder(BorderFactory.createMatteBorder(
                                    0, 2, 0, 0, Color.black));
          }
        if(decider== 15 & decider2==10){
          
          
          this.setBorder(BorderFactory.createMatteBorder(
                                    0, 0, 0, 2, Color.black));
          }
        if(decider== 1 & decider2==4){
          
          this.setText("/");
          
          this.setForeground(Color.green);
          }
        if(decider== 12 & decider2==6){
          
          this.setText("/");
          
          this.setForeground(Color.green);
          }
        if(decider== 13 & decider2==1){
          
          this.setText("/");
          
          this.setForeground(Color.red);
          }
        if(decider== 7 & decider2==11){
          
          this.setText("/");
          
          this.setForeground(Color.red);
          }
        if(decider== 7 & decider2==5){
          
          this.setText("\\");
          
          this.setForeground(Color.yellow);
          }
        if(decider== 12 & decider2==9){
          
          this.setText("\\");
          
          this.setForeground(Color.yellow);
          }
        if(decider== 14 & decider2==11){
          
          this.setText("\\");
          
          this.setForeground(Color.blue);
          }
        if(decider== 2 & decider2==14){
          
          this.setText("\\");
          
          this.setForeground(Color.blue);
          }
        if(decider== 1 & decider2==10){
          
          
          this.setBorder(BorderFactory.createMatteBorder(
                                    3, 3, 0, 0, Color.black));
          }
        if(decider== 3 & decider2==1){
          //top/left
          
          this.setBorder(BorderFactory.createMatteBorder(
                                    3, 3, 0, 0, Color.black));
          }
        if(decider== 13 & decider2==3){
          //top/left
          
          this.setBorder(BorderFactory.createMatteBorder(
                                    3, 3, 0, 0, Color.black));
          }  
        if(decider== 13 & decider2==9){
          //top/left
          
          this.setBorder(BorderFactory.createMatteBorder(
                                    3, 3, 0, 0, Color.black));
          }     
        if(decider== 7 & decider2==7){
          //top/left
          
          this.setBorder(BorderFactory.createMatteBorder(
                                    3, 3, 0, 0, Color.black));
          }     
        if(decider== 6 & decider2==2){
          //top/right
          
          this.setBorder(BorderFactory.createMatteBorder(
                                    3, 0, 0, 3, Color.black));
          }
        if(decider== 6 & decider2==13){
          //top/right
          
          this.setBorder(BorderFactory.createMatteBorder(
                                    3, 0, 0, 3, Color.black));
          }
        if(decider== 7 & decider2==8){
          //top/right
          
          this.setBorder(BorderFactory.createMatteBorder(
                                    3, 0, 0, 3, Color.black));
          }
        if(decider== 11 & decider2==12){
          //top/right
          
          this.setBorder(BorderFactory.createMatteBorder(
                                    3, 0, 0, 3, Color.black));
          }
        if(decider== 14 & decider2==5){
          //top/right
          
          this.setBorder(BorderFactory.createMatteBorder(
                                    3, 0, 0, 3, Color.black));
          }
        if(decider== 10 & decider2==7){
          //top/right
          
          this.setBorder(BorderFactory.createMatteBorder(
                                    3, 0, 0, 3, Color.black));
          }
        if(decider== 4 & decider2==6){
          //Bottom/Right
          
          this.setBorder(BorderFactory.createMatteBorder(
                                    0, 0, 3, 3, Color.black));
          }
        if(decider== 4 & decider2==8){
          //Bottom/Right
          
          this.setBorder(BorderFactory.createMatteBorder(
                                    0, 0, 3, 3, Color.black));
          }  
        if(decider== 9 & decider2==10){
          //Bottom/Right
          
          this.setBorder(BorderFactory.createMatteBorder(
                                    0, 0, 3, 3, Color.black));
          }
        if(decider== 12 & decider2==3){
          //Bottom/Right
          
          this.setBorder(BorderFactory.createMatteBorder(
                                    0, 0, 3, 3, Color.black));
          }
        if(decider== 8 & decider2==8){
          //Bottom/Right
          
          this.setBorder(BorderFactory.createMatteBorder(
                                    0, 0, 3, 3, Color.black));
          }
        if(decider== 6 & decider2==3){
          //Bottom/left
          
          this.setBorder(BorderFactory.createMatteBorder(
                                    0, 3, 3, 0, Color.black));
          }
        if(decider==5  & decider2==13){
          //Bottom/left
          
          this.setBorder(BorderFactory.createMatteBorder(
                                    0, 3, 3, 0, Color.black));
          }
        if(decider== 9 & decider2==2){
          //Bottom/left
          
          this.setBorder(BorderFactory.createMatteBorder(
                                    0, 3, 3, 0, Color.black));
          }
        if(decider== 11 & decider2==13){
          //Bottom/left
          
          this.setBorder(BorderFactory.createMatteBorder(
                                    0, 3, 3, 0, Color.black));
          }
        if(decider== 8 & decider2==7){
          //Bottom/left
          
          this.setBorder(BorderFactory.createMatteBorder(
                                    0, 3, 3, 0, Color.black));
          }        
        
        
        if(decider==5 & decider2==0){
          
          this.setBorder(BorderFactory.createMatteBorder(
                                    0, 0, 2, 0, Color.black));
          }
        if(decider== 6 & decider2==0){
          
          
          this.setBorder(BorderFactory.createMatteBorder(
                                    2, 0, 0, 0, Color.black));
          }
        
        if(decider==10 & decider2==0){
          
          this.setBorder(BorderFactory.createMatteBorder(
                                    0, 0, 2, 0, Color.black));
          }
        if(decider== 11 & decider2==0){
          
          
          this.setBorder(BorderFactory.createMatteBorder(
                                    2, 0, 0, 0, Color.black));
          }
        if(decider==12 & decider2==15){
          
          this.setBorder(BorderFactory.createMatteBorder(
                                    0, 0, 2, 0, Color.black));
          }
        if(decider== 13 & decider2==15){
          
          
          this.setBorder(BorderFactory.createMatteBorder(
                                    2, 0, 0, 0, Color.black));
          }
        if(decider==2 & decider2==15){
          
          this.setBorder(BorderFactory.createMatteBorder(
                                    0, 0, 2, 0, Color.black));
          }
        if(decider== 3 & decider2==15){
          
          
          this.setBorder(BorderFactory.createMatteBorder(
                                    2, 0, 0, 0, Color.black));
          }
    }
    
    
    
}


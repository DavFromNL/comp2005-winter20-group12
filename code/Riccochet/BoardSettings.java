/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ricochet.robots;

import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;

public class BoardSettings extends JFrame {
    
    private JPanel Panel = new JPanel();
    int size = 2;
    
    public BoardSettings(){
        
        super("Board Settings");
        
        GridLayout grid = new GridLayout(size+1,0,0,15);
        
        JLabel label1 = new JLabel();
        
        RicochetRobots.SetUpScreen(this, grid, Panel, label1, "Board Settings",80);
        
        BoardSettingsButtons[] buttons = new BoardSettingsButtons[size];
        
        for(int i = 0; i < size; i++){
            buttons[i] = new BoardSettingsButtons();
            Panel.add(buttons[i]);
            buttons[i].setBackground(Color.ORANGE);
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 60));
            switch(i){
                
                case 0:
                    
                    buttons[i].setText("Color Palette");
                    
                    break;
                
                case 1:
                    buttons[i].setText("Board Types");
                    break;
                    
                
                
                
            }
            
        }
        
        add(Panel);
        setVisible(true);
        
    }
    
}

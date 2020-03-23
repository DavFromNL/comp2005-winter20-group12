/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ricochet.robots;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
 
public class BoardSettingsButtons extends JButton implements ActionListener {
    
    public BoardSettingsButtons(){
        
        this.addActionListener(this);
        
    }
    
    public void actionPerformed(ActionEvent event){
        
        String name = event.getActionCommand();
        
        switch(name){
            
            case "Color Palette":
                new ColorPalette();
                break;
                
            case "Board Types":
                new BoardTypes();
                break;
                
           
        }
    }
    
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

//This is what happens when you click on each button which is of type PlayerSettings.
public class PlayerSettingsButtons extends JButton implements ActionListener {
    
    public PlayerSettingsButtons(){
    
    this.addActionListener(this);
}
    
    public void actionPerformed(ActionEvent event){
        
        String name = event.getActionCommand();
        
        switch(name){
            
            case "Number Of Players":
                new NumberOfPlayers();
                break;
                
            case "Difficulty":
                new Difficulty();
                break;
                
            case "Hint":
                new Hint();
                break;
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ricochet.robots;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
 
public class SaveButton extends JButton implements ActionListener  {
    
   
    
    public SaveButton(){
        this.addActionListener(this);
        
    }
    
    
    
    public void actionPerformed(ActionEvent event){
    
        try
        {   
            
            BufferedWriter writer = new BufferedWriter(new FileWriter("SaveFile.txt"));
            writer.write(""+Board.greenX);
            writer.newLine();
            writer.write(""+Board.greenY);
            writer.newLine();
            writer.write(""+Board.blueX);
            writer.newLine();
            writer.write(""+Board.blueY);
            writer.newLine();
            writer.write(""+Board.redX);
            writer.newLine();
            writer.write(""+Board.redY);
            writer.newLine();
            writer.write(""+Board.yellowX);
            writer.newLine();
            writer.write(""+Board.yellowY);
            
            writer.close();
            
            JOptionPane.showMessageDialog(null,"Game is Saved","Saved",1);
            
        }
        catch(Exception e)
        {
            
        }
        
        
        
        
    
    }
    
}

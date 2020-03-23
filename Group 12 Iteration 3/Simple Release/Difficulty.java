/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ricochet.robots;

import javax.swing.JPanel;

import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Difficulty extends JFrame {
    
    private JPanel Panel = new JPanel();
     int size = 6;
    JButton Normal;
    JButton Hard;
    
    public Difficulty(){
        
        super("Difficulty");
        
        GridLayout grid = new GridLayout(size+1,0,0,15);
        
        JLabel label1 = new JLabel();
        
        RicochetRobots.SetUpScreen(this, grid, Panel, label1, "Select Difficulty",50);
        
        Normal = new JButton();
        Hard = new JButton();
        
        JLabel label2 = new JLabel();
        JLabel label3 = new JLabel();
        
        Normal.setBackground(Color.ORANGE);
        Normal.setFont(new Font("Arial", Font.PLAIN, 60));
        Normal.setText("Normal");
        
        Hard.setBackground(Color.ORANGE);
        Hard.setFont(new Font("Arial", Font.PLAIN, 60));
        Hard.setText("Hard");
       
        Panel.add(label1);
        
        Panel.add(label2);
        Panel.add(Normal);
        Panel.add(label3);
        Panel.add(Hard);
        
        Normal.addActionListener(new ActionListener(){
            
            Data data = new Data();
            
            public void actionPerformed(ActionEvent event){
                
                if(event.getSource() == Normal)
                {
                    
                    data.SetNormalDifficulty();
                    JOptionPane.showMessageDialog(null,"Difficulty is set to Normal","Saved",1);
                    
                }
                
            }
        
        });
        
        Hard.addActionListener(new ActionListener(){
            
            Data data = new Data();
            
            public void actionPerformed(ActionEvent event){
                
                if(event.getSource() == Hard){
                    
                    data.SetHardDifficulty();
                    JOptionPane.showMessageDialog(null,"Difficulty is set to Hard","Saved",1);
                    
                }
                
            }
        
        });
        
        
        add(Panel);
        setVisible(true);
        
    }
    
}

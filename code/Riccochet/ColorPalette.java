//Creates window that allows user to select colour palette

import java.awt.*;
import javax.swing.*;
import java.awt.Image.*;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorPalette extends JFrame {
 
    private JPanel Panel = new JPanel();
    int size = 3;
    JButton Default;
    JButton GrayScale;
    JButton BlueOrange;
    
    
    public ColorPalette()
    {
        super("Color Palette");
        
        GridLayout grid = new GridLayout(size+1,0,0,20);
        
        JLabel label1 = new JLabel();
        
        RicochetRobots.SetUpScreen(this, grid, Panel, label1, "Color Palette",70);
        
        Default = new JButton();
        GrayScale = new JButton();
        BlueOrange = new JButton();
        System.out.println("System out");
        
        try{
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            Image image = ImageIO.read(classLoader.getResource("res/grayscale.png"));
            Image image2 = ImageIO.read(classLoader.getResource("res/nighttime.png"));
            GrayScale.setIcon(new ImageIcon(image));
            BlueOrange.setIcon(new ImageIcon(image2));
        }
        catch(Exception e){
            System.out.println("Image not found");
            
        }
        
        Default.setBackground(Color.ORANGE);
        Default.setFont(new Font("Arial", Font.PLAIN, 60));
        
        Default.setText("Default Palette");
        
        Panel.add(Default);
        Panel.add(GrayScale);
        Panel.add(BlueOrange);
        
        Default.addActionListener(new ActionListener(){
            
            Data data = new Data();
            
            public void actionPerformed(ActionEvent event){
                
                if(event.getSource() == Default)
                {
                    
                    data.SetNormalPalette();
                    JOptionPane.showMessageDialog(null,"Color Palette is Default","Saved",1);
                    
                }
                
            }
        
        });
        
        GrayScale.addActionListener(new ActionListener(){
            
            Data data = new Data();
            
            public void actionPerformed(ActionEvent event){
                
                if(event.getSource() == GrayScale)
                {
                    
                    data.SetGrayScalePalette();
                    JOptionPane.showMessageDialog(null,"Color Palette is GrayScale","Saved",1);
                    
                }
                
            }
        
        });
        
        BlueOrange.addActionListener(new ActionListener(){
            
            Data data = new Data();
            
            public void actionPerformed(ActionEvent event){
                
                if(event.getSource() == BlueOrange)
                {
                    
                    data.SetBlueOrangePalette();
                    JOptionPane.showMessageDialog(null,"Color Palette is blue/orange","Saved",1);
                    
                }
                
            }
        
        });
        
        add(Panel);
        setVisible(true);
        
    }
    
}

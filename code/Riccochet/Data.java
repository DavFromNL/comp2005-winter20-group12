/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ricochet.robots;

//Data class which stores all the data about what options the player has selected or chosen. 
//This class can also be used to take required option chosen by the user and implement somwthing.
//When the user updates the data, this class updates it and saves it.
public class Data 
{
    private int NumberOfPlayers = 1;
    
    private enum DifficultyLevels{Normal, Hard}
    
    private enum Hints{On, Off}
    
    private enum BoardTypes{Simple, Complex}
    
    private enum PaletteTypes{Normal,GrayScale,BlueOrange}
    
    private PaletteTypes Palette = PaletteTypes.Normal;
    
    private DifficultyLevels difficulty = DifficultyLevels.Normal;
    
    private Hints hint = Hints.On;
    
    private BoardTypes Board = BoardTypes.Simple;
    
    private int GreenX = 0, GreenY = 0, BlueX = 0, BlueY = 0, RedX = 0, RedY = 0, YellowX = 0, YellowY = 0;
    
    public void SetNumberOfPlayers(int value){
    
        NumberOfPlayers = value;
        
    }
    
    public int GetNumberOfPlayers(){
        
        return NumberOfPlayers;
    }
    
    public DifficultyLevels GetDifficultyLevels(){
        
        return difficulty;
        
    }
    
    public void SetNormalDifficulty(){
        
        difficulty = DifficultyLevels.Normal;
        
    }
    
    public void SetHardDifficulty(){
        
        difficulty = DifficultyLevels.Hard;
        
    }
    
    public Hints GetHintSetting(){
        
        return hint;
    }
    
    public void TurnOnHints(){
        
        hint = Hints.On;
    }
    
    public void TurnOffHints(){
        
        hint = Hints.Off;
    }
    
    public BoardTypes GetCurrentBoardType(){
        
        return Board;
    }
    
    public void SetSimpleBoard(){
        
        Board = BoardTypes.Simple;
    }
    
    public void SetComplexBoard(){
        
        Board = BoardTypes.Complex;
    }
    
    public void SetNormalPalette(){
        
        Palette = PaletteTypes.Normal;
        
    }
    
    public void SetGrayScalePalette(){
        
        Palette = PaletteTypes.GrayScale;
    }
    
    public void SetBlueOrangePalette(){
        
        Palette = PaletteTypes.BlueOrange;
        
    }
    
    public PaletteTypes GetCurrentPalette(){
        
        return Palette;
    }
    
    public void GreenXPos(int pos){
        
        GreenX = pos;
        
    }
    
    public void GreenYPos(int pos){
        
        GreenY = pos;
        
    }
    
    public void BlueXPos(int pos){
        
        BlueX = pos;
        
    }
    public void BlueYPos(int pos){
        
        BlueY = pos;
        
    }
    public void RedXPos(int pos){
        
        RedX = pos;
        
    }
    
    public void RedYPos(int pos){
        
        RedY = pos;
        
    }
    public void YellowXPos(int pos){
        
        YellowX = pos;
        
    }
    public void yellowYPos(int pos){
        
        YellowY = pos;
        
    }
    
   public int GetGreenX(){
       
       return GreenX;
       
   }
   
   public int GetGreenY(){
       
       return GreenY;
       
   }
   
   public int GetBlueX(){
       
       return BlueX;
       
   }
   
   public int GetBlueY(){
       
       return BlueY;
       
   }
   
   public int GetRedX(){
       
       return RedX;
       
   }
   
   public int GetRedY(){
       
       return RedY;
       
   }
   
   public int GetYellowX(){
       
       return YellowX;
       
   }
   
   public int GetYellowY(){
       
       return YellowY;
       
   }
}

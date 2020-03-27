import java.io.BufferedReader;
import java.io.FileReader;
public class LoadGame 
{
    public void Loading(){
        
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("SaveFile.txt"));
            Board.greenX = Integer.parseInt(reader.readLine());
            Board.greenY = Integer.parseInt(reader.readLine());
            Board.blueX = Integer.parseInt(reader.readLine());
            Board.blueY = Integer.parseInt(reader.readLine());
            Board.redX = Integer.parseInt(reader.readLine());
            Board.redY = Integer.parseInt(reader.readLine());
            Board.yellowX = Integer.parseInt(reader.readLine());
            Board.yellowY = Integer.parseInt(reader.readLine());
            Data.SetPalette(Integer.parseInt(reader.readLine()));
            Data.SetBoardType(Integer.parseInt(reader.readLine()));
            
            reader.close();
            
        }catch(Exception e){
            
            
            
        }
        
    }
    

}



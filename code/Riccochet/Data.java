//Data class which stores all the data about what options the player has selected or chosen. 
//This class can also be used to take required option chosen by the user and implement somwthing.
//When the user updates the data, this class updates it and saves it.
import java.awt.Color;

public class Data {
    private int NumberOfPlayers = 1;

    private enum DifficultyLevels {
        Normal, Hard
    }

    private enum Hints {
        On, Off
    }

    private enum BoardTypes {
        Simple, Complex
    }

    private enum PaletteTypes {
        Normal, GrayScale, BlueOrange
    }

    private static PaletteTypes Palette = PaletteTypes.Normal;

    private DifficultyLevels difficulty = DifficultyLevels.Normal;

    private Hints hint = Hints.On;

    private static BoardTypes Board = BoardTypes.Simple;

    private int GreenX = 0, GreenY = 0, BlueX = 0, BlueY = 0, RedX = 0, RedY = 0, YellowX = 0, YellowY = 0;

    private static Color[] normalPalette = { new Color(220, 202, 152), Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW,
            Color.ORANGE, Color.BLACK };
    private static Color[] blueOrangePalette = { new Color(97, 76, 191), new Color(72, 69, 154),
            new Color(53, 50, 131), new Color(37, 53, 105), new Color(30, 43, 88), new Color(120, 75, 134),
            Color.BLACK };
    private static Color[] grayScalePalette = { Color.WHITE, new Color(90, 101, 113), new Color(138, 147, 157), new Color(162, 169, 175), new Color(0, 0, 0), new Color(224, 227, 232), Color.BLACK };

    public static void SetNumberOfPlayers(int value) {

        NumberOfPlayers = value;

    }

    public int GetNumberOfPlayers() {

        return NumberOfPlayers;
    }

    public DifficultyLevels GetDifficultyLevels() {

        return difficulty;

    }

    public void SetNormalDifficulty() {

        difficulty = DifficultyLevels.Normal;

    }

    public void SetHardDifficulty() {

        difficulty = DifficultyLevels.Hard;

    }

    public Hints GetHintSetting() {

        return hint;
    }

    public void TurnOnHints() {

        hint = Hints.On;
    }

    public void TurnOffHints() {

        hint = Hints.Off;
    }

    public BoardTypes GetCurrentBoardType() {

        return Board;
    }

    public void SetSimpleBoard() {

        Board = BoardTypes.Simple;
    }

    public void SetComplexBoard() {

        Board = BoardTypes.Complex;
    }

    public static void SetBoardType(int q) {
        if (q == 1) { Board = BoardTypes.Simple;
        }
        if (q == 2) { Board = BoardTypes.Complex;
        }
    }

    public static int GetBoardType(){
        if(Board == BoardTypes.Simple){return 1;}
        return 2;
    }

    public static void SetPalette(int q){
        if(q == 1){Palette = PaletteTypes.Normal;}
        if(q == 2){Palette = PaletteTypes.GrayScale;}
        if(q == 3){Palette = PaletteTypes.BlueOrange;}
    }
    public static int GetCurrentPalette(){
        if(Palette == PaletteTypes.GrayScale){return 2;}
        if(Palette == PaletteTypes.BlueOrange){return 3;}
        return 1;
    }

    public static Color[] GetPalette() {
        if (Palette == PaletteTypes.BlueOrange) {
            return blueOrangePalette;
        }
        if (Palette == PaletteTypes.GrayScale) {
            return grayScalePalette;
        }
        return normalPalette;
    }

    public void GreenXPos(int pos) {

        GreenX = pos;

    }

    public void GreenYPos(int pos) {

        GreenY = pos;

    }

    public void BlueXPos(int pos) {

        BlueX = pos;

    }

    public void BlueYPos(int pos) {

        BlueY = pos;

    }

    public void RedXPos(int pos) {

        RedX = pos;

    }

    public void RedYPos(int pos) {

        RedY = pos;

    }

    public void YellowXPos(int pos) {

        YellowX = pos;

    }

    public void yellowYPos(int pos) {

        YellowY = pos;

    }

    public int GetGreenX() {

        return GreenX;

    }

    public int GetGreenY() {

        return GreenY;

    }

    public int GetBlueX() {

        return BlueX;

    }

    public int GetBlueY() {

        return BlueY;

    }

    public int GetRedX() {

        return RedX;

    }

    public int GetRedY() {

        return RedY;

    }

    public int GetYellowX() {

        return YellowX;

    }

    public int GetYellowY() {

        return YellowY;

    }
}

/*
    Data class which stores all the data about what options the player has selected or chosen. 
    This class can also be used to take required option chosen by the user and implement something.
    When the user updates the data, this class updates it and saves it.
*/
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

    private PaletteTypes Palette = PaletteTypes.Normal;

    private DifficultyLevels difficulty = DifficultyLevels.Normal;

    private Hints hint = Hints.On;

    private BoardTypes Board = BoardTypes.Simple;

    public void SetNumberOfPlayers(final int value) {

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

    public void SetNormalPalette() {

        Palette = PaletteTypes.Normal;

    }

    public void SetGrayScalePalette() {

        Palette = PaletteTypes.GrayScale;
    }

    public void SetBlueOrangePalette() {

        Palette = PaletteTypes.BlueOrange;

    }

    public PaletteTypes GetCurrentPalette() {

        return Palette;
    }

}

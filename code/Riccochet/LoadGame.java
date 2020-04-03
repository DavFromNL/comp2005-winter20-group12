/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class LoadGame

{
    private boolean filefound = false;

    public void Loading() {

        try {
            BufferedReader reader = new BufferedReader(new FileReader("SaveFile.txt"));
            if (NormalType()) {
                Board.greenX = Integer.parseInt(reader.readLine());
                Board.greenY = Integer.parseInt(reader.readLine());
                Board.blueX = Integer.parseInt(reader.readLine());
                Board.blueY = Integer.parseInt(reader.readLine());
                Board.redX = Integer.parseInt(reader.readLine());
                Board.redY = Integer.parseInt(reader.readLine());
                Board.yellowX = Integer.parseInt(reader.readLine());
                Board.yellowY = Integer.parseInt(reader.readLine());
            } else {
                ComplexBoard.greenX = Integer.parseInt(reader.readLine());
                ComplexBoard.greenY = Integer.parseInt(reader.readLine());
                ComplexBoard.blueX = Integer.parseInt(reader.readLine());
                ComplexBoard.blueY = Integer.parseInt(reader.readLine());
                ComplexBoard.redX = Integer.parseInt(reader.readLine());
                ComplexBoard.redY = Integer.parseInt(reader.readLine());
                ComplexBoard.yellowX = Integer.parseInt(reader.readLine());
                ComplexBoard.yellowY = Integer.parseInt(reader.readLine());

            }

            Scores.P1Scores = Integer.parseInt(reader.readLine());
            Scores.P2Scores = Integer.parseInt(reader.readLine());
            Scores.P3Scores = Integer.parseInt(reader.readLine());
            Scores.P4Scores = Integer.parseInt(reader.readLine());

            List<String> list1 = new ArrayList<>();

            for (int i = 0; i < 4; i++) {

                list1.add(reader.readLine());

            }

            SetValues(list1);

            filefound = true;

            reader.close();

        } catch (Exception e) {

            filefound = false;
        }

    }

    private void SetValues(List<String> list1) {
        BiddingSystem.Bids.clear();

        for (int i = 0; i < list1.size(); i++) {

            if (!list1.get(i).equals("")) {
                int space = list1.get(i).lastIndexOf(" ");

                String name = list1.get(i).substring(0, space);

                int value = Integer.parseInt(list1.get(i).substring(space + 1));

                BiddingSystem.Bids.put(name, value);

            }

        }

    }

    public void LoadBoard() {

        try {
            BufferedReader reader = new BufferedReader(new FileReader("Board.txt"));

            Data.SetBoardType(Integer.parseInt(reader.readLine()));

            filefound = true;

            reader.close();
        } catch (Exception e) {
            filefound = false;

        }
    }

    private boolean NormalType() {

        try {
            BufferedReader reader = new BufferedReader(new FileReader("Board.txt"));

            if (Integer.parseInt(reader.readLine()) == 1) {
                reader.close();
                return true;
            } else {

                reader.close();
                return false;
            }

        }

        catch (Exception e) {
            System.out.println("fd");
            return false;
        }

    }

    public boolean IsFilFound() {

        return filefound;
    }

}

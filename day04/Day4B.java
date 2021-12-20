package com.company.day04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day4B {

    public String[] bingoNumbers;//string tab for numbers
    public ArrayList<BingoTableB> bingoTables;//A hashMap for Tables and boolean flag of change

    public void collectData(String path) {

        bingoTables = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String data;
            String numbers = "";

            bingoNumbers = br.readLine().split(",");

            while ((data = br.readLine()) != null) {

                while (data != null && !data.equals("")) {
                    numbers += " " + data;
                    data = br.readLine();
                }

                if (!numbers.equals(""))
                    bingoTables.add(new BingoTableB(numbers));

                numbers = "";
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startThelottery() {
        for (int i = 0; i < bingoNumbers.length; i++) {

            int number = Integer.parseInt(bingoNumbers[i]);

            ArrayList<BingoTableB> tablesToRemove = new ArrayList<>();

            for (BingoTableB bt : bingoTables) {
                int index;

                if ((index = bt.getBingoTable().indexOf(number)) != -1) {
                    bt.changeStateOfIndex(index);
                    if (i > 3 && checkForWinner(bt) && bingoTables.size()!=1) {
                        tablesToRemove.add(bt);
                    }
                }

            }

            for(BingoTableB btB: tablesToRemove){
                bingoTables.remove(btB);
            }
            if(checkForWinner(bingoTables.get(0))&&bingoTables.size()==1){
                System.out.println(countTheWinner(number,bingoTables.get(0)));
                return;
            }
        }
    }

    private boolean checkForWinner(BingoTableB bt) {
        return bt.check();
    }

    private int countTheWinner(int lastDrawn, BingoTableB theWinner) {
        return lastDrawn * theWinner.countPoints();
    }


}

class BingoTableB {
    private ArrayList<Integer> bingoTable;

    private ArrayList<ArrayList<Boolean>> bingoState;//tab for state of bingo numbers

    public BingoTableB(String numbers) {
        String[] table = numbers.split("\\s+");
        bingoTable = new ArrayList();

        for (String s : table) {
            if (!s.equals("")) {
                bingoTable.add(Integer.parseInt(s));
            }
        }

        bingoState = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            bingoState.add(new ArrayList<>());
            for (int j = 0; j < 5; j++) {
                bingoState.get(i).add(false);
            }
        }

    }

    public void changeStateOfIndex(int index) {
        int row = index / 5;
        int column = index % 5;
        bingoState.get(row).set(column, true);
    }

    public int countPoints() {
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!bingoState.get(i).get(j))
                    sum += bingoTable.get(i * 5 + j);
            }
        }

        return sum;
    }

    public boolean check() {
        for (ArrayList<Boolean> state : bingoState) {
            if (!state.contains(false)) return true;
        }

        if (
                bingoState.get(0).get(0) && bingoState.get(1).get(0) && bingoState.get(2).get(0) && bingoState.get(3).get(0) && bingoState.get(4).get(0) ||
                        bingoState.get(0).get(1) && bingoState.get(1).get(1) && bingoState.get(2).get(1) && bingoState.get(3).get(1) && bingoState.get(4).get(1) ||
                        bingoState.get(0).get(2) && bingoState.get(1).get(2) && bingoState.get(2).get(2) && bingoState.get(3).get(2) && bingoState.get(4).get(2) ||
                        bingoState.get(0).get(3) && bingoState.get(1).get(3) && bingoState.get(2).get(3) && bingoState.get(3).get(3) && bingoState.get(4).get(3) ||
                        bingoState.get(0).get(4) && bingoState.get(1).get(4) && bingoState.get(2).get(4) && bingoState.get(3).get(4) && bingoState.get(4).get(4)
        ) return true;

        return false;
    }

    public ArrayList<Integer> getBingoTable() {
        return bingoTable;
    }
}

package com.company.day07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

//Method based on abs ranges

public class Day7 {

    private TreeMap<Integer, Integer> rangeAndXArray;
    private int[] numbersTab;
    private int min;
    private int xSum;
    private int nSum;

    public void collectData(String path) {

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String[] numbers = br.readLine().split(",");

            rangeAndXArray = new TreeMap<>();


            for (String number : numbers) {
                int nr = Integer.parseInt(number);
                try {
                    rangeAndXArray.put(nr, rangeAndXArray.get(nr) + 1);
                } catch (NullPointerException e) {
                    rangeAndXArray.put(nr, 1);
                }
            }
            numbersTab = new int[rangeAndXArray.size()];

            int i = 0;
            xSum = 0;
            min = Integer.MAX_VALUE;

            for (Map.Entry<Integer, Integer> entry : rangeAndXArray.entrySet()) {
                xSum += entry.getValue();
                nSum += entry.getKey() * entry.getValue();
                numbersTab[i++] = entry.getKey() * entry.getValue();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int doTheMath() {
        int tempXSum = 0;

        int tempNSum = 0;

        int counter = 0;

        int ratio;

        int prevRangeStart;

        for (Map.Entry<Integer, Integer> entry : rangeAndXArray.entrySet()) {

            prevRangeStart = entry.getKey();

            tempXSum += entry.getValue();

            tempNSum += numbersTab[counter++];

            ratio = tempXSum * 2 - xSum;

            do {
                int newDistance = ratio * prevRangeStart - 2 * tempNSum + nSum;

                if (newDistance < min)
                    min = newDistance;

            } while (!rangeAndXArray.containsKey(++prevRangeStart) && prevRangeStart < rangeAndXArray.size());
        }
        return min;
    }

}

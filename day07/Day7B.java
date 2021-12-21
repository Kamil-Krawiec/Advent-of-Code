package com.company.day07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Day7B {

    private TreeMap<Integer, Integer> rangeAndXArray;
    private int min;
    private int max;

    public void collectData(String path) {

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String[] numbers = br.readLine().split(",");

            rangeAndXArray = new TreeMap<>();

            max = Integer.MIN_VALUE;

            for (String number : numbers) {
                int nr = Integer.parseInt(number);
                try {
                    rangeAndXArray.put(nr, rangeAndXArray.get(nr) + 1);
                    if (nr > max) max = nr;

                } catch (NullPointerException e) {
                    rangeAndXArray.put(nr, 1);
                    if (nr > max) max = nr;
                }
            }

            min = Integer.MAX_VALUE;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int doTheMath() {
        int distance;
        for (int i = 0; i < max; i++) {
            distance = 0;

            for (Map.Entry<Integer, Integer> entry : rangeAndXArray.entrySet()) {
                distance += (1 +Math.abs(i - entry.getKey())) * Math.abs(i - entry.getKey())*entry.getValue() / 2;
            }

            if (distance < min) min = distance;
        }
        return min;
    }

}

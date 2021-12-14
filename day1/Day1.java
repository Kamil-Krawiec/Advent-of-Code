package com.company.day1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day1 {


    public static int ex2(ArrayList<Integer> inputData) {
        int counterOfIncreasedMeasurements = 0;
        int sum1;
        int sum2;
        for (int i = 2; i < inputData.size() - 1; i++) {
            sum1 = inputData.get(i - 2) + inputData.get(i - 1) + inputData.get(i);
            sum2 = inputData.get(i - 1) + inputData.get(i) + inputData.get(i + 1);

            if (sum1 < sum2) counterOfIncreasedMeasurements++;
        }
        return counterOfIncreasedMeasurements;
    }

    public static ArrayList<Integer> collectData(String path) {
        ArrayList<Integer> inputData = new ArrayList<>();

        try (Scanner sc = new Scanner(new File(path))) {
            while (sc.hasNextLine()) {
                int currentMeasurement = Integer.parseInt(sc.nextLine());
                inputData.add(currentMeasurement);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return inputData;
    }

    public static void main(String[] args) {
        ArrayList<Integer> data = collectData("/Users/kamil/Desktop/Advent of code/src/com/company/day1/input.txt");
        System.out.println(ex2(data));
    }


}

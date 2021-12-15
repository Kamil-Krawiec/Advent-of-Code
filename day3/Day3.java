package com.company.day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Day3 {

    public static ArrayList<String> collectDataEX1(String path) {
        ArrayList<String> binaryData = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String data = "";

            while ((data = br.readLine()) != null) {
                binaryData.add(data);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return binaryData;
    }

    public static int countGammaEpsilonEX1(ArrayList<String> data) {
        String gamma = "", epsilon = "";
        for (int i = 0; i < data.get(0).length(); i++) {

            int countOne = 0;

            for (String binaryNumber : data) {
                if (binaryNumber.charAt(i) == '1') countOne++;
            }

            if (countOne > (data.size() / 2)) {
                gamma += "1";
                epsilon += "0";
            } else {
                gamma += "0";
                epsilon += "1";
            }

        }
        return Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2);
    }


    public static String countOxygenGeneratorEX2(ArrayList<String> data, String flag) {
        Map<String, Integer> rate = new HashMap<>();
        String ratio = "";

        for (int i = 0; i < data.get(0).length(); i++) {
            rate.put("1", 0);
            rate.put("0", 0);

            for (String binaryNumber : data) {
                if (binaryNumber.charAt(i) == '1') rate.put("1", 1 + rate.get("1"));
                else rate.put("0", 1 + rate.get("0"));
            }
            if (flag.equals("oxygen")) {
                if (rate.get("1") >= rate.get("0")) {
                    ratio += "1";
                } else {
                    ratio += "0";
                }
            } else {
                if (rate.get("1") >= rate.get("0")) {
                    ratio += "0";
                } else {
                    ratio += "1";
                }
            }


            String temp = ratio;

            data.removeIf(s -> !s.startsWith(temp));
            if (data.size() == 1) return data.get(0);

        }
        return "NOT FOUND ";
    }

    public static int countCo2OxygenEX2(String oxygen, String co2) {
        System.out.println(oxygen + " " + co2);
        return Integer.parseInt(co2, 2) * Integer.parseInt(oxygen, 2);
    }


    public static void main(String[] args) {
        System.out.println(countGammaEpsilonEX1(collectDataEX1("/Users/kamil/Desktop/Advent of code/src/com/company/day3/input.txt")));
        System.out.println(countCo2OxygenEX2(countOxygenGeneratorEX2(collectDataEX1("/Users/kamil/Desktop/Advent of code/src/com/company/day3/input.txt"), "oxygen"),
                countOxygenGeneratorEX2(collectDataEX1("/Users/kamil/Desktop/Advent of code/src/com/company/day3/input.txt"), "co2")));

    }


}

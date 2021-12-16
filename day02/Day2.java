package com.company.day02;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day2 {

    public static Map<String, Integer> collectDataEX1(String path) {
        Map<String,Integer> movesData = new HashMap<>();

        movesData.put("forward",0);
        movesData.put("down",0);
        movesData.put("up",0);

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String key;

            int value;

            String data="";

            while ((data=br.readLine())!= null) {
                key = data.split(" ")[0];
                value=Integer.parseInt(data.split(" ")[1]);
                movesData.put(key,movesData.get(key)+value);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return movesData;
    }
    public static int calculatePositionEX1(Map<String,Integer> moves){
        int pos = moves.get("up")-moves.get("down");
        pos*=moves.get("forward");
        return pos;
    }

    public static int collectAndProcesDataEX2(String path) {
        int aim = 0,horizontal=0,depth=0;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String key;
            int value;
            String data="";

            while ((data=br.readLine())!= null) {
                key = data.split(" ")[0];
                value=Integer.parseInt(data.split(" ")[1]);

                if(key.equals("down")){
                    aim+=value;
                }else if(key.equals("up")){
                    aim-=value;
                }else{
                    horizontal+=value;
                    depth+=aim*value;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return depth*horizontal;
    }

    public static void main(String[] args){
        System.out.println(calculatePositionEX1(collectDataEX1("/Users/kamil/Desktop/Advent of code/src/com/company/day2/input.txt")));
        System.out.println(collectAndProcesDataEX2("/Users/kamil/Desktop/Advent of code/src/com/company/day2/input.txt"));

    }
}

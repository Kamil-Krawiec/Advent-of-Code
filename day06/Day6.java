package com.company.day06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;


public class Day6 {
    private long[] fishes;

    public void collectData(String path) {
        fishes = new long[9];

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String[] temp = br.readLine().split(",");

            for(String day: temp){
                fishes[Integer.parseInt(day)]+=1;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BigInteger copulate(int days){
        while(days>0){
            long firstDay=fishes[0];

            fishes[0]=fishes[1];
            fishes[1]=fishes[2];
            fishes[2]=fishes[3];
            fishes[3]=fishes[4];
            fishes[4]=fishes[5];
            fishes[5]=fishes[6];
            fishes[6]=fishes[7]+firstDay;
            fishes[7]=fishes[8];
            fishes[8]=firstDay;

            days--;
        }
        BigInteger sum= BigInteger.valueOf(0);
        for(int i=0;i<9;i++){
            sum= sum.add(BigInteger.valueOf(fishes[i]));
        }
        return sum;
    }

}

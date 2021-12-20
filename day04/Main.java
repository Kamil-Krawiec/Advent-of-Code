package com.company.day04;


public class Main {

    public static void main(String[] args) {
        Day4 d4 = new Day4();
        d4.collectData("/Users/kamil/Desktop/Advent of code/src/com/company/day04/input.txt");
        System.out.println(d4.startThelottery());
        Day4B d4B = new Day4B();
        d4B.collectData("/Users/kamil/Desktop/Advent of code/src/com/company/day04/input.txt");
        d4B.startThelottery();
    }
}

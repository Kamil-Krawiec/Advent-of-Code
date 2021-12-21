package com.company.day07;


public class Main {

    public static void main(String[] args) {
        Day7 d7 = new Day7();
        d7.collectData("/Users/kamil/Desktop/Advent of code/src/com/company/day07/input.txt");
        System.out.println(d7.doTheMath());

        Day7B b7 = new Day7B();
        b7.collectData("/Users/kamil/Desktop/Advent of code/src/com/company/day07/input.txt");
        System.out.println(b7.doTheMath());
    }
}

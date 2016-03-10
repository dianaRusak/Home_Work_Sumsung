package com.company;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;

public class Main {

    public static void main(String[] args) {
        int i;
        int b [] = new int [10];
        int g = 0;
        long startTimes = System.nanoTime();
        for (i = 0; i < b.length; i++){
            Arrays.fill(b, 0, b.length, g);
            g++;
        }
        System.out.print ("Время ArrayList ");
        System.out.println(System.nanoTime() - startTimes);
        long startTime = System.nanoTime();
        List<Integer> linkedList = new LinkedList<>();
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        linkedList.add(5);
        linkedList.add(6);
        linkedList.add(7);
        linkedList.add(8);
        linkedList.add(9);
        linkedList.add(10);
        linkedList.add(11);
        System.out.print ("Время LinkedList ");
        System.out.println(System.nanoTime() - startTime);
    }
}

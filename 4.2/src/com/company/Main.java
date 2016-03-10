package com.company;

import java.text.Collator;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.print("Сортировка по количеству сраниц: ");
        //Количество страниц книги
        int[] intArr = {5, 9, 1, 10};
        Arrays.sort(intArr);
        System.out.println(Arrays.toString(intArr));

        //сортировка по автору
        System.out.print("Сортировка по автору: ");
        LinkedList list = new LinkedList();
        list.add("Гоголь");
        list.add("Достоевский");
        list.add("Грибоедов");
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Collator.getInstance().compare(o1, o2);
            }
        });
        System.out.print(list);
        System.out.println();
        System.out.print("Сортировка по названию книги: ");
        //сортировка по названию
        LinkedList list1 = new LinkedList();
        list1.add("Мёртвые души");
        list1.add("Идиот");
        list1.add("Горе от ума");
        Collections.sort(list1, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Collator.getInstance().compare(o1, o2);
            }
        });
        System.out.print(list1);

    }
}

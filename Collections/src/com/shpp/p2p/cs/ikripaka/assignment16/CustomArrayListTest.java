package com.shpp.p2p.cs.ikripaka.assignment16;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CustomArrayListTest {

    @Test
    void add() {
    }

    @Test
    void clear() {
        CustomArrayList<Character> customArrayList = new CustomArrayList<>(2);
        System.out.println("- Add 10 numbers: ");

        for (int i = 0; i < 10; i++) {
            char letter = (char) ('0' + i);
            System.out.print(letter + " ");
            customArrayList.add(letter);
        }
        customArrayList.remove(5);
        customArrayList.trimToSize();
        customArrayList.clear();
        Assertions.assertSame(0, customArrayList.size());
    }

    @Test
    void contains() {
        CustomArrayList<Character> customArrayList = new CustomArrayList<>(2);
        System.out.println("- Add 10 numbers: ");

        for (int i = 0; i < 10; i++) {
            char letter = (char) ('0' + i);
            System.out.print(letter + " ");
            customArrayList.add(letter);
        }
        customArrayList.remove(1);
        Assertions.assertFalse(customArrayList.contains('1'));
    }

    @Test
    void indexOf() {
        CustomArrayList<Character> customArrayList = new CustomArrayList<>(2);
        System.out.println("- Add 10 numbers: ");

        for (int i = 0; i < 10; i++) {
            char letter = (char) ('0' + i);
            System.out.print(letter + " ");
            customArrayList.add(letter);
        }
        Assertions.assertSame(5, customArrayList.indexOf('4'));
    }

    @Test
    void isEmpty() {
        CustomArrayList<Character> customArrayList = new CustomArrayList<>(2);
        System.out.println("- Add 10 numbers: ");

        for (int i = 0; i < 10; i++) {
            char letter = (char) ('0' + i);
            System.out.print(letter + " ");
            customArrayList.add(letter);
        }
        customArrayList.clear();
        customArrayList.trimToSize();
        Assertions.assertTrue(customArrayList.isEmpty());
    }

    @Test
    void set() {
        CustomArrayList<Character> customArrayList = new CustomArrayList<>(2);
        System.out.println("- Add 10 numbers: ");

        for (int i = 0; i < 10; i++) {
            char letter = (char) ('0' + i);
            System.out.print(letter + " ");
            customArrayList.add(letter);
        }
        customArrayList.set(4, 'I');
        Assertions.assertSame('I', customArrayList.get(4));
    }

    @Test
    void size() {
        CustomArrayList<Character> customArrayList = new CustomArrayList<>(20);
        System.out.println("- Add 2 numbers: ");

        for (int i = 0; i < 2; i++) {
            char letter = (char) ('0' + i);
            System.out.print(letter + " ");
            customArrayList.add(letter);
        }
        customArrayList.trimToSize();
        Assertions.assertSame(2, customArrayList.size());
    }

    @Test
    void toArray() {
        CustomArrayList<Character> customArrayList = new CustomArrayList<>(5);
        System.out.println("- Add 5 numbers: ");

        char[] actual = new char[5];
        for (int i = 0; i < 5; i++) {
            char letter = (char) ('0' + i);
            System.out.print(letter + " ");
            actual[i] = letter;
            customArrayList.add(letter);
        }
        Assertions.assertArrayEquals(new char[]{'0','1','2','3','4'}, actual);

    }

    @Test
    void generalTest(){
        CustomArrayList<Character> customArrayList = new CustomArrayList<>();

        for (int i = 0; i < 27; i++) {
            char letter = (char) ('a' + i);
            customArrayList.add(letter);
        }
        System.out.println(Arrays.toString(customArrayList.toArray()));

        System.out.println(customArrayList.indexOf('f'));

        System.out.println("foreach");
        for(char symbol: customArrayList){
            System.out.print(symbol + " ");
        }

        System.out.println("Delete half of the ArrayList data");
        for (int i = customArrayList.size() / 2; i < customArrayList.size(); ) {
            customArrayList.remove(i);
        }
        System.out.println(Arrays.toString(customArrayList.toArray()));
        customArrayList.trimToSize();
        for (int i = 0; i < customArrayList.size(); i++) {
            char symbol = (char) ('0' + i);
            customArrayList.set(i, symbol);
        }
        System.out.println(Arrays.toString(customArrayList.toArray()));
        customArrayList.clear();
        customArrayList.trimToSize();
        Assertions.assertSame(0, customArrayList.size());
    }
}
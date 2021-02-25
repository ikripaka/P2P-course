package com.shpp.p2p.cs.ikripaka.assignment16;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * CustomLinkedList tester
 */
class CustomLinkedListTest {

    @Test
    void isEmpty() {
        CustomLinkedList<Character> customLinkedList = new CustomLinkedList<>();
        System.out.println("- Add 10 numbers: ");
        LinkedList<Integer> list = new LinkedList<>();
        list.add(4);
        list.add(5);
        list.add(0);
        System.out.println(list.get(0));
        for (int i = 0; i < 10; i++) {
            char letter = (char) ('0' + i);
            System.out.print(letter + " ");
            customLinkedList.add(letter);
        }
        customLinkedList.add(0, 'f');
        customLinkedList.clear();
        Assertions.assertTrue(customLinkedList.isEmpty());
    }

    @Test
    void get() {
        CustomLinkedList<Character> customLinkedList = new CustomLinkedList<>();
        System.out.println("- Add 10 numbers: ");
        for (int i = 0; i < 10; i++) {
            char letter = (char) ('0' + i);
            System.out.print(letter + " ");
            customLinkedList.add(letter);
        }
        System.out.println(Arrays.toString(customLinkedList.toArray()));
        Assertions.assertSame('4', customLinkedList.get(4));
    }

    @Test
    void getFirst() {
        CustomLinkedList<Character> customLinkedList = new CustomLinkedList<>();
        System.out.println("- Add 10 numbers: ");
        for (int i = 0; i < 10; i++) {
            char letter = (char) ('0' + i);
            System.out.print(letter + " ");
            customLinkedList.add(letter);
        }
        Assertions.assertSame('0', customLinkedList.getFirst());
    }

    @Test
    void getLast() {
        CustomLinkedList<Character> customLinkedList = new CustomLinkedList<>();
        System.out.println("- Add 10 numbers: ");
        for (int i = 0; i < 10; i++) {
            char letter = (char) ('0' + i);
            System.out.print(letter + " ");
            customLinkedList.add(letter);
        }
        Assertions.assertSame('9', customLinkedList.getLast());
    }

    @Test
    void contains() {
        CustomLinkedList<Character> customLinkedList = new CustomLinkedList<>();
        System.out.println("- Add 10 numbers: ");
        for (int i = 0; i < 10; i++) {
            char letter = (char) ('0' + i);
            System.out.print(letter + " ");
            customLinkedList.add(letter);
        }
        Assertions.assertTrue(customLinkedList.contains('7'));
    }

    @Test
    void indexOf() {
        CustomLinkedList<Character> customLinkedList = new CustomLinkedList<>();
        System.out.println("- Add 10 numbers: ");
        for (int i = 0; i < 10; i++) {
            char letter = (char) ('0' + i);
            System.out.print(letter + " ");
            customLinkedList.add(letter);
        }
        Assertions.assertSame(4,customLinkedList.indexOf('4'));
    }

    @Test
    void generalTest() {
        CustomLinkedList<Character> customLinkedList = new CustomLinkedList<>();
        System.out.println("- Add 26 letters: ");

        for (int i = 0; i < 26; i++) {
            char letter = (char) ('a' + i);
            System.out.print(letter + " ");
            customLinkedList.add(letter);
        }

        System.out.println();
        System.out.println("- Show conversion to array: " + Arrays.toString(customLinkedList.toArray()));
        System.out.println("- Delete first 13 letters: ");

        for (int i = 0; i < 13; i++) {
            System.out.print(customLinkedList.removeFirst() + " ");
        }

        System.out.println();
        System.out.println("- Delete 13 letters from the end: ");

        for (int i = 0; i < 13; i++) {
            System.out.print(customLinkedList.removeLast() + " ");
        }
        customLinkedList.clear();

        System.out.println();
        System.out.println("- Add 10 numbers in char alternately ( 1 - from the beginning, 2 - from the end) : ");
        System.out.println();

        for(int i = 0; i < 10; i++){
            char letter = (char) ('0' + i);
            System.out.print(letter + " ");
            if(i % 2 == 0) {
                customLinkedList.addFirst(letter);
            }else{
                customLinkedList.addLast(letter);
            }
        }
        System.out.println();
        System.out.println("- Remove element with index 5:" + customLinkedList.remove(5));
        System.out.println("- Test contains method (contain '1'?) : " + customLinkedList.contains(1));

        Assertions.assertSame(9, customLinkedList.size());
    }

    @Test
    void set() {
        CustomLinkedList<Character> customLinkedList = new CustomLinkedList<>();
        System.out.println("- Add 10 numbers: ");
        for (int i = 0; i < 10; i++) {
            char letter = (char) ('0' + i);
            System.out.print(letter + " ");
            customLinkedList.add(letter);
        }
        customLinkedList.set(9, 'I');
        Assertions.assertSame('I',customLinkedList.get(9));
    }

    @Test
    void toArray() {
        CustomLinkedList<Character> customLinkedList = new CustomLinkedList<>();
        System.out.println("- Add 10 numbers: ");
        char[] actual = new char[5];
        for (int i = 0; i < 5; i++) {
            char letter = (char) ('0' + i);
            System.out.print(letter + " ");
            actual[i] = letter;
            customLinkedList.add(letter);
        }
        Assertions.assertArrayEquals(new char[]{'0','1','2','3','4'}, actual);
    }
}
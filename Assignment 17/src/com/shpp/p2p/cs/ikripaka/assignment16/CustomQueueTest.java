package com.shpp.p2p.cs.ikripaka.assignment16;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests CustomQueue
 */
class CustomQueueTest {

    @Test
    void testAddingAndPolling() {
        CustomQueue<Character> customQueue = new CustomQueue<>();

        System.out.println("- Add 27 letters: ");
        for (int i = 0; i < 27; i++) {
            char letter = (char) ('a' + i);
            customQueue.add(letter);
        }

        System.out.println("- Poll 17 elements: ");
        char[] actual = new char[17];
        for (int i = 0; i < 17; i++) {
            char oneLetter = customQueue.poll();
            actual[i] = oneLetter;
            System.out.print(oneLetter + " ");
        }
        char[] expected = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q'};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void testPeek() {
        CustomQueue<Character> customQueue = new CustomQueue<>();

        System.out.println("- Add 10 numbers: ");
        for (int i = 0; i < 10; i++) {
            char letter = (char) ('0' + i);
            System.out.print(letter + " ");
            customQueue.add(letter);
        }
        System.out.println("Peek " + customQueue.peek());

        Assertions.assertSame( '0', customQueue.peek());
    }

    @Test
    void testRemoving() {
        CustomQueue<Character> customQueue = new CustomQueue<>();
        char[] expected, actual;
        System.out.println("- Add 10 numbers: ");
        for (int i = 0; i < 10; i++) {
            char letter = (char) ('0' + i);
            System.out.print(letter + " ");
            customQueue.add(letter);
        }
        for (int i = 0; i < 5; i++) {
            customQueue.poll();
        }

        expected = new char[]{'5', '6', '7', '8', '9'};
        actual = new char[5];

        System.out.println();
        System.out.println("Items are not deleted: ");
        for (int i = 0; i < 5; i++) {
            char oneLetter = customQueue.poll();
            actual[i] = oneLetter;
            System.out.print(oneLetter + " ");
        }
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void testForEmpty() {
        CustomQueue<Character> customQueue = new CustomQueue<>();
        Assertions.assertTrue(customQueue.isEmpty());
    }
}
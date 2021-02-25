package com.shpp.p2p.cs.ikripaka.assignment16;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests CustomStack
 */
class CustomStackTest {
    @Test
    void testAddingAndPolling() {
        CustomStack<Character> customStack = new CustomStack<>();

        System.out.println("- Add 27 letters: ");
        for (int i = 0; i < 27; i++) {
            char letter = (char) ('a' + i);
            customStack.push(letter);
        }

        System.out.println("- Poll 17 elements: ");
        char[] actual = new char[27];
        int i = 0;
        for (Character oneLetter : customStack) {
            actual[i++] = oneLetter;
            System.out.print(oneLetter + " ");
        }
        char[] expected = new char[]{'{', 'z', 'y', 'x', 'w', 'v', 'u', 't', 's', 'r', 'q', 'p', 'o',
                'n', 'm', 'l', 'k', 'j', 'i', 'h', 'g', 'f', 'e', 'd', 'c', 'b', 'a'};
        System.out.println(actual);
        System.out.println(expected);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void testPeek() {
        CustomStack<Character> customQueue = new CustomStack<>();

        System.out.println("- Add 10 numbers: ");
        for (int i = 0; i < 10; i++) {
            char letter = (char) ('0' + i);
            System.out.print(letter + " ");
            customQueue.push(letter);
        }
        System.out.println("Peek " + customQueue.peek());

        Assertions.assertSame( '9', customQueue.peek());
    }

    @Test
    void testRemoving() {
        CustomStack<Character> customQueue = new CustomStack<>();
        char[] expected, actual;
        System.out.println("- Add 10 numbers: ");
        for (int i = 0; i < 10; i++) {
            char letter = (char) ('0' + i);
            System.out.print(letter + " ");
            customQueue.push(letter);
        }
        for (int i = 0; i < 5; i++) {
            customQueue.pop();
        }

        expected = new char[]{'4', '3', '2', '1', '0'};
        actual = new char[5];

        System.out.println();
        System.out.println("Items are not deleted: ");
        for (int i = 0; i < 5; i++) {
            char oneLetter = customQueue.pop();
            actual[i] = oneLetter;
            System.out.print(oneLetter + " ");
        }
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void testForEmpty() {
        CustomStack<Character> customQueue = new CustomStack<>();
        Assertions.assertTrue(customQueue.isEmpty());
    }
}
package com.shpp.p2p.cs.ikripaka.assignment16;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

class CustomPriorityQueueTest {

    @Test
    void add() {
        CustomPriorityQueue<Integer> custom = new CustomPriorityQueue<>();
        PriorityQueue<Integer> original = new PriorityQueue<>();
        for(int i = 0; i < 20; i++){
            custom.add(i);
            original.add(i);
        }
        custom.add(890);
        original.add(890);
        System.out.println("Original peek: " + original.peek() + "  Custom peek: " + custom.peek());
        Assertions.assertSame(original.peek(), custom.peek());
    }

    @Test
    void poll() throws Exception {
        CustomPriorityQueue<Integer> custom = new CustomPriorityQueue<>();
        PriorityQueue<Integer> original = new PriorityQueue<>();
        for(int i = 0; i < 20; i++){
            custom.add(i);
            original.add(i);
        }
        for (int i = 0; i < 7; i++){
            custom.poll();
            original.poll();
        }
        System.out.println("Original toString: " + original.toString());
        System.out.println("Custom toString: " + custom.toString());
        Assertions.assertSame(original.toString(), custom.toString());
    }

    @Test
    void rightSort(){
        int[] array = {56,23,2,0,123,34,12,100,99,55};
        CustomPriorityQueue<Integer> custom = new CustomPriorityQueue<>();
        PriorityQueue<Integer> original = new PriorityQueue<>();
        for(int i = 0; i < array.length; i++){
            custom.add(array[i]);
            original.add(array[i]);
        }
        System.out.println("Original toString: " + original.toString() + "  Custom toString: " + custom.toString());

    }

    @Test
    void removeAll() {
        CustomPriorityQueue<Integer> custom = new CustomPriorityQueue<>();
        PriorityQueue<Integer> original = new PriorityQueue<>();
        for(int i = 0; i < 20; i++){
            custom.add(i);
            original.add(i);
        }
        custom.removeAll();
        original.removeAll(original);
        System.out.println("Original toString: " + original.toString() + "  Custom toString: " + custom.toString());
        Assertions.assertSame(original.isEmpty(), custom.isEmpty());
    }

    @Test
    void contains() throws Exception {
        CustomPriorityQueue<Integer> custom = new CustomPriorityQueue<>();
        PriorityQueue<Integer> original = new PriorityQueue<>();
        for(int i = 0; i < 20; i++){
            custom.add(i);
            original.add(i);
        }
        System.out.println("Original contains: " + original.contains(5) + "  Custom contains: " + custom.contains(5));
        Assertions.assertSame(original.contains(5), custom.contains(5));
    }

    @Test
    void iterator() {
        CustomPriorityQueue<Integer> custom = new CustomPriorityQueue<>();
        PriorityQueue<Integer> original = new PriorityQueue<>();
        for(int i = 0; i < 20; i++){
            custom.add(i);
            original.add(i);
        }
        for (Object i : custom){
            System.out.print(i + " ");
        }
        System.out.println();
        for (Object i : original){
            System.out.print(i + " ");
        }
        Assertions.assertSame(original.size(), custom.size());
    }
}
package com.shpp.p2p.cs.ikripaka.assignment16;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

class CustomHashMapTest {

    @Test
    void put() {
        HashMap<String, Double> original = new HashMap<>();
        CustomHashMap<String, Double> custom = new CustomHashMap<>();

        String[] names = {"Ilya", "hanna", "pumma", "adidas", "onix", "father", "Anna", "karma", "masha", "July"};
        for (int i = 0; i < 10; i++) {
            custom.put(names[i], 5 * 4 / 7.5 * i);
            original.put(names[i], 5 * 4 / 7.5 * i);
        }
        System.out.println("Original " + original.toString());
        System.out.println("Custom " + custom.toString());
        System.out.println("Original size: " + original.size());
        System.out.println("Custom size: " + custom.size());
        Assertions.assertSame(original.size(), custom.size());
    }

    @Test
    void changeElementsValue() {
        HashMap<Integer, Integer> original = new HashMap<>();
        CustomHashMap<Integer, Integer> custom = new CustomHashMap<>();
        for (int i = 0; i < 100; i++) {
            custom.put(i, 60 / 20 * i);
            original.put(i, 60 / 20 * i);
        }
        for (int i = 20; i < 50; i++) {
            custom.putIfAbsent(i, i);
            original.putIfAbsent(i, i);
        }

        System.out.println("Original 25th el: " + original.get(25));
        System.out.println("Custom 25th el: " + custom.get(25));
        Assertions.assertSame(original.get(25), custom.get(25));
    }

    @Test
    void replace (){
        HashMap<Integer, Integer> original = new HashMap<>();
        CustomHashMap<Integer, Integer> custom = new CustomHashMap<>();
        for (int i = 0; i < 100; i++) {
            custom.put(i, 60 / 20 * i);
            original.put(i, 60 / 20 * i);
        }
        for(int i = 70; i < 80; i++){
            custom.replace(i, i);
            original.replace(i, i);
        }
        for(int i = 75; i < 85; i++){
            custom.replace(i, i, 1000);
            original.replace(i, i, 1000);
        }
        System.out.println("Original 84th el: " + original.get(84));
        System.out.println("Custom 84th el: " + custom.get(84));
    }

    @Test
    void remove(){
        HashMap<Integer, Integer> original = new HashMap<>();
        CustomHashMap<Integer, Integer> custom = new CustomHashMap<>();
        for (int i = 0; i < 100; i++) {
            custom.put(i, 60 / 20 * i);
            original.put(i, 60 / 20 * i);
        }
        System.out.println("Custom remove 100: " + custom.remove(100));
        System.out.println("Original remove 100: " + original.remove(100));
        Assertions.assertSame(original.containsKey(100), custom.containsKey(100));
    }

    @Test
    void getOrDefault(){
        HashMap<Integer, Integer> original = new HashMap<>();
        CustomHashMap<Integer, Integer> custom = new CustomHashMap<>();
        for (int i = 0; i < 100; i++) {
            custom.put(i, 60 / 20 * i);
            original.put(i, 60 / 20 * i);
        }
        System.out.println("Custom getOrDefault: " + custom.getOrDefault(100, 1));
        System.out.println("Original getOrDefault: " + original.getOrDefault(100, 1));
        Assertions.assertSame(original.getOrDefault(100, 1), custom.getOrDefault(100, 1));
        System.out.println("Custom getOrDefault: " + custom.getOrDefault(3, 1));
        System.out.println("Original getOrDefault: " + original.getOrDefault(3, 1));
        Assertions.assertSame(original.getOrDefault(3, 1), custom.getOrDefault(3, 1));
    }
}
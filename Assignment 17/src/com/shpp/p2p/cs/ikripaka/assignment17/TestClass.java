package com.shpp.p2p.cs.ikripaka.assignment17;

import java.util.Arrays;
import java.util.StringTokenizer;

public class TestClass {
    public static void main(String[] args) {
        CustomPriorityQueue<Double> queue = new CustomPriorityQueue<>(Double.class);
        StringTokenizer tokenizer = new StringTokenizer(args[0], ",");
        //Random rand = new Random();

        double timeBefore = System.currentTimeMillis();
        System.out.println(timeBefore);
        while (tokenizer.hasMoreTokens()) {
            queue.add(Double.parseDouble(tokenizer.nextToken()));//(float) ((Float.parseFloat(tokenizer.nextToken()))/pow(10, rand.nextInt(7))));
        }
       /* for(int i = 500; i > 0; i--){
            queue.poll();
        }
        for(int i = 200; i > 0; i--){
            queue.add(rand.nextFloat());
        }
        while (!queue.isEmpty()){
            queue.poll();
        }*/

        System.out.println(Arrays.toString(queue.sort()));
        double timeAfter = System.currentTimeMillis();
        System.out.println(timeAfter);
        System.out.println((timeAfter - timeBefore));
        System.currentTimeMillis();
    }
}

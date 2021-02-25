package com.shpp.p2p.cs.ikripaka.assignment3;

import com.shpp.cs.a.console.TextProgram;

/**
 * This class solves Douglas exercise
 */
public class Assignment3Part2 extends TextProgram {

    public void run() {
        int[] numbersHailstones = new int[2];
        println(" Введи 0 или число < 0, если хочешь выйти с программы.");

        while (true) {

            println("---  ---  ---  ---  ---  ---");
            numbersHailstones = enterNumber(numbersHailstones);
            if (numbersHailstones[0] <= 0) {
                println("--   - -   --   - -   --   - -");
                println("Ты вышел из программы");
                break;
            }
            douglasExercise(numbersHailstones);
        }
        println("Конец.");
    }

    /**
     * @return calculated number in the expression for even numbers
     */
    private int expressionForEvenNumbers(int[] numbersHailstone) {
        numbersHailstone[1] = numbersHailstone[0] * 3 + 1;
        return numbersHailstone[1];
    }

    /**
     * @return calculated number in the expression for odd numbers
     */
    private int expressionForOddNumbers(int[] numbersHailstone) {
        numbersHailstone[1] = numbersHailstone[0] / 2;
        return numbersHailstone[1];
    }

    /**
     * input number
     */
    private int[] enterNumber(int[] numbersHailstones) {
        numbersHailstones[0] = readInt("Введи число: ");
        return numbersHailstones;
    }

    /**
     * solves Douglas exercise
     */
    private void douglasExercise(int[] numbersHailstones) {
        while (numbersHailstones[0] != 1) {
            if (numbersHailstones[0] % 2 != 0) {
                numbersHailstones[1] = expressionForEvenNumbers(numbersHailstones);
                println(numbersHailstones[0] + " - нечетное, значит нужно умножить на 3 и добавить 1, получим " + numbersHailstones[1]);
                numbersHailstones[0] = numbersHailstones[1];
            } else {
                numbersHailstones[1] = expressionForOddNumbers(numbersHailstones);
                println(numbersHailstones[0] + " - четное, значит нужно поделить на 2, получим " + numbersHailstones[1]);
                numbersHailstones[0] = numbersHailstones[1];
            }
        }
    }
}

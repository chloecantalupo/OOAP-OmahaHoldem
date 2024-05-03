package org.example;

import java.util.Random;

public class TimidStrategy implements GameplayStrategy {
    TimidStrategy() {
    }

    public int getStratAction() {
        Random rand = new Random();
        int option = rand.nextInt(6) - 4;
        if (option < 0) option = -1; // higher chance for a fold
        return option;
    }
}

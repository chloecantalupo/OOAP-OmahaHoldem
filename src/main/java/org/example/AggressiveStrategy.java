package org.example;

import java.util.Random;

public class AggressiveStrategy implements GameplayStrategy {
    AggressiveStrategy() {
    }

    public int getStratAction() {
        Random rand = new Random();
        return rand.nextInt(6) - 1; // higher chance for a raise
    }
}

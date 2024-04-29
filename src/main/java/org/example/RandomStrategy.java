package org.example;

import java.util.Random;

public class RandomStrategy implements GameplayStrategy {
    RandomStrategy() {
    }

    @Override
    public int getStratAction() {
        Random rand = new Random();
        return rand.nextInt(3) - 1;
    }
}

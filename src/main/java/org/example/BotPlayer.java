package org.example;

import java.util.Random;

public class BotPlayer extends Player {
    // private GameplayStrategy strat;
    public BotPlayer(String name, int startingChips) {
        super(name, startingChips);
    }

    @Override
    public int getAction() {
        if (!this.getDealtIn()) {
            return -1; // You don't get to bet if you've already folded
        }

        // For bot players, decide their action
        Random rand = new Random();
        int actionDecision = rand.nextInt(3) - 1;
        int option = rand.nextInt(6) - 1;
        if (option >= 1) { // raise
            option = rand.nextInt(20) + 1; // raise amount
        }
        if (option == -1) {
            this.clearHand(); // Fold
            this.setDealtIn(false);
        }
        return option;
    }
}

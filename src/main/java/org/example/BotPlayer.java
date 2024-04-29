package org.example;

import java.util.Random;

public class BotPlayer extends Player {
    private GameplayStrategy strat;
    public BotPlayer(String name, int startingChips) {
        super(name, startingChips);
        Random rand = new Random();
        int strategy = rand.nextInt(2);
        switch (strategy) {
            case 0:
                this.strat = new RandomStrategy();
                break;
            case 1:
                this.strat = new AggressiveStrategy();
                break;
        }
    }

    @Override
    public int getAction() {
        int option = strat.getStratAction();

        if (option == -1) {
            this.clearHand(); // Fold
            this.setDealtIn(false);
        }
        if (option >= 1) {
            if (this.getChips() != 0) {
                Random rand = new Random();
                option = rand.nextInt(this.getChips() * 2); // raise by a random legal amount
                if (option > this.getChips()) {
                    option = this.getChips(); // chance of going all in whenever the bot raises
                }
            } else {
                option = 0;
            }
        }
        return option;
    }
}

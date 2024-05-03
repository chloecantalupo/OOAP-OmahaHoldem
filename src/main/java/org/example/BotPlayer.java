package org.example;

import java.util.Random;

public class BotPlayer extends Player {
    private GameplayStrategy strat;
    public BotPlayer(String name, int startingChips) {
        super(name, startingChips);
        Random rand = new Random();
        int strategy = rand.nextInt(3);
        switch (strategy) {
            case 0:
                this.strat = new RandomStrategy();
                break;
            case 1:
                this.strat = new AggressiveStrategy();
                break;
            case 2:
                this.strat = new TimidStrategy();
                break;
        }
    }

    @Override
    public int getAction(int cBet) {
        int option = strat.getStratAction();

        if (option == -1) {
            this.clearHand(); // Fold
            this.setDealtIn(false);
        }
        if (option >= 1) {
            if (this.getChips() != 0) {
                Random rand = new Random();
                option = rand.nextInt(this.getChips() * 2); // raise by a legal amount (with chance to go all in)
                if (option + cBet > this.getChips()) {
                    option = this.getChips() - cBet; // go all in
                }
            } else {
                option = 0;
            }
        }
        return option;
    }
}

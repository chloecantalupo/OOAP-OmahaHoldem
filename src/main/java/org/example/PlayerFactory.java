package org.example;

public class PlayerFactory {
    public static Player getHumanPlayer(String name, int startingChips) {
        return new Player(name, startingChips);
    }

    // Placeholder for when we figure out how we want to implement CPU players, perhaps as a subclass of Player?
//    public Player getBotPlayer(String name) {
//        return new Bot(name);
//    }
}

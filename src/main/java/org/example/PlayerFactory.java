package org.example;

public class PlayerFactory {
    public static Player getHumanPlayer(String name, int startingChips) {
        return new Player(name, startingChips);
    }

    public static Player getBotPlayer(String name, int startingChips) {
        return new BotPlayer(name, startingChips);
    }
}

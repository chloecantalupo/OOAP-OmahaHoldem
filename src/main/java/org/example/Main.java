package org.example;

public class Main {
    public static void main(String args[]) {
        OmahaHoldemGame game = new OmahaHoldemGame.Builder().withPlayerCount(2).withStartingChips(500).build();
        game.startGame();
        System.exit(0);
    }
}

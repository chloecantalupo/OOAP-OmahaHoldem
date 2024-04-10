package org.example;

public class Main {
    public static void main(String args[]) {
        OmahaHoldemGame game = new OmahaHoldemGame(2, 100, 5);
        game.startGame();
        System.exit(0);
    }
}

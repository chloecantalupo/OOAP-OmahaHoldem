package org.example;
import org.example.FindBestHand;
import java.util.ArrayList;
import java.util.List;

public class OmahaHoldemGame {
    private Deck deck;
    private List<Player> players;

    private List<Card> communityCards;
    private int pot;
    private int currentBet;

    public OmahaHoldemGame(int playerCount, int startingChips) {
        // Initialize the game components
        this.deck = new Deck();
        this.players = new ArrayList<>();
        this.communityCards = new ArrayList<>();
        this.pot = 0;
        this.currentBet = 0;

        // Create players and their give them their starting chips
        for (int i = 0; i < playerCount; i++) {
            this.players.add(PlayerFactory.getBotPlayer("Player " + i, startingChips));
        }
    }

    private OmahaHoldemGame() {
    }

    public static class Builder {
        private int playerCount;
        private int startingChips;

        public Builder withPlayerCount(int playerCount) {
            this.playerCount = playerCount;
            return this;
        }

        public Builder withStartingChips(int startingChips) {
            this.startingChips = startingChips;
            return this;
        }

        public OmahaHoldemGame build() {
            OmahaHoldemGame game = new OmahaHoldemGame();
            game.deck = new Deck();
            game.players = new ArrayList<>();
            game.communityCards = new ArrayList<>();
            game.pot = 0;
            game.currentBet = 0;

            game.players.add(PlayerFactory.getHumanPlayer("You", startingChips));
            for (int i = 1; i < playerCount; i++) {
                game.players.add(PlayerFactory.getBotPlayer("Player " + i, startingChips));
            }

            return game;
        }

        public OmahaHoldemGame fullBotTable() {
            OmahaHoldemGame game = new OmahaHoldemGame();
            game.deck = new Deck();
            game.players = new ArrayList<>();
            game.communityCards = new ArrayList<>();
            game.pot = 0;
            game.currentBet = 0;

            for (int i = 0; i < 8; i++) {
                game.players.add(PlayerFactory.getBotPlayer("Player " + i, 100));
            }

            return game;
        }

        public OmahaHoldemGame twoBotTable() {
            OmahaHoldemGame game = new OmahaHoldemGame();
            game.deck = new Deck();
            game.players = new ArrayList<>();
            game.communityCards = new ArrayList<>();
            game.pot = 0;
            game.currentBet = 0;

            for (int i = 0; i < 2; i++) {
                game.players.add(PlayerFactory.getBotPlayer("Player " + i, 100));
            }

            return game;
        }
    }

    public void startGame() {
        System.out.println("Welcome to Omaha Hold'em!");
        while (!isGameOver()) {
            System.out.println("Dealing 4 cards to each player...");
            deal();
            playPreFlop();
            if (checkRemainingPlayers()) {
                System.out.println("Everyone else folded!");
                prepareForNextHand();
                continue;
            }
            System.out.println("Dealing the flop...");
            playFlop();
            if (checkRemainingPlayers()) {
                System.out.println("Everyone else folded!");
                prepareForNextHand();
                continue;
            }
            System.out.println("Dealing the turn...");
            playTurn();
            if (checkRemainingPlayers()) {
                System.out.println("Everyone else folded!");
                prepareForNextHand();
                continue;
            }
            System.out.println("Dealing the river...");
            playRiver();
            if (checkRemainingPlayers()) {
                System.out.println("Everyone else folded!");
                prepareForNextHand();
                continue;
            }
            System.out.println("Let's see who won!");
            showdown();
            prepareForNextHand();
        }
        gameOver();
    }

    // True: Only one player is still in the hand, everyone else has folded
    // False: More than one player is still in the hand
    private boolean checkRemainingPlayers() {
        int nonFoldedPlayerCount = 0;
        for (Player p : this.players) {
            if (p.getDealtIn()) {
                nonFoldedPlayerCount++;
            }
        }
        return nonFoldedPlayerCount == 1;
    }

    // Main logic of betting, players take turns deciding to check, call, raise or fold
    private void doPlayerBetting() {
        // TODO: Player actions need to be checked for validity, such as checking when there's a current bet
        int lastRaised = 0;
        int actionPlayer = 0;

        // Continue to allow player betting until the players have all called or checked
        do {
            displayGame();
            int playerBet = this.players.get(actionPlayer).getAction();

            if (playerBet == 0 && currentBet != 0 && this.players.get(actionPlayer).getChips() >= currentBet) { // Call
                // currentBet stays the same, but the extra chips added to the player's bet to call are added to the pot
                this.pot += this.players.get(actionPlayer).call(currentBet);
            }
            if (playerBet > 0) { // Raise
                lastRaised = actionPlayer;
                // Player pays in the amount they need to call + their raised value
                this.pot += this.players.get(actionPlayer).raise(currentBet, playerBet);
                // currentBet only increases by the raised value
                this.currentBet += playerBet;
            }

            // advance play to the next player
            actionPlayer = (actionPlayer + 1) % this.players.size();
            // play continues until it returns back to the player who last raised, or the first player if no one raises
        } while (actionPlayer != lastRaised && !checkRemainingPlayers());

        // set currentBet back to 0 for future betting rounds
        this.currentBet = 0;
    }

    private void deal() {
        this.deck.shuffle();
        // Deal in each player
        for (Player p : this.players) {
            if (p.getChips() > 0) { // Only deal in players that still have chips
                p.dealIn();
            }
        }
        for (int i = 0; i < 4; i++) {
            for (Player p : this.players) {
                if (p.getDealtIn()) {
                    p.receiveCard(this.deck.dealCard());
                }
            }
        }
    }

    private void playPreFlop() {
        doPlayerBetting();
    }

    private void playFlop() {
        // Deal flop
        communityCards.add(deck.dealCard());
        communityCards.add(deck.dealCard());
        communityCards.add(deck.dealCard());

        doPlayerBetting();
    }

    private void playTurn() {
        // Deal turn
        communityCards.add(deck.dealCard());

        doPlayerBetting();
    }

    private void playRiver() {
        // Deal river
        communityCards.add(deck.dealCard());

        doPlayerBetting();
    }

    private void showdown() {
    }

    private void prepareForNextHand() {
        // Get a fresh deck and shuffle it
        this.deck.reset();

        Player winner = determineWinner();
        winner.winPot(pot);
        System.out.println(winner.getName() + " has won a pot of " + this.pot + " chips!!!");
        this.pot = 0;
        this.communityCards.clear();

        for (Player p : players) {
            p.clearHand();
        }
    }

    private Player determineWinner() {
        // TODO: complex method of determining who won the hand
        // for now, just return the first player that is still dealt in
        List<Player> playersStillInHand = null;

        for (Player p : this.players) {
            if (p.getDealtIn()) {
                playersStillInHand.add(p);
            }
        }
        if ( playersStillInHand != null){
            throw new IllegalStateException("No players still dealt in after hand has concluded.");
        }
        Player winningPlayer = FindBestHand.findWinner(playersStillInHand, this.communityCards);
        System.out.println(winningPlayer.getName() + " has won the hand!");
        return winningPlayer;
    }

    private boolean isGameOver() {
        int playersStillIn = 0;

        for (Player p : this.players) {
            if (p.getChips() > 0) {
                playersStillIn++;
            }
        }

        return playersStillIn == 1;
    }

    private void gameOver() {
        System.out.println("Game Over!");
        Player winner = players.get(0);
        // Find the winner
        for (Player p : this.players) {
            if (p.getChips() != 0) {
                winner = p;
                break;
            }
        }
        System.out.println("The winner is " + winner.getName());
    }

    @Override
    public String toString() {
        String gameState = "";
        for (Player p : this.players) {
            gameState = gameState.concat("\n" + p.toString());
        }
        gameState = gameState.concat("\nPot: " + this.pot + "\nCommunity Cards: " + this.communityCards);
        return gameState;
    }

    public void displayGame() {
        System.out.println(this);
    }
    // Additional methods for handling player actions, updating the UI, etc.
}

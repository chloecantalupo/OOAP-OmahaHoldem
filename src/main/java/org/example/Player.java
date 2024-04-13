package org.example;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final List<Card> hand;  // Player's hand
    private int chips;             // Player's chip count
    private int currentBet;     // Amount of chips player has currently bet, used to handle calls after already betting some chips for the round
    private final String name;           // Player's name
    private boolean dealtIn; // Whether the player is active in the current hand or not

    public Player(String name, int startingChips) {
        this.name = name;
        this.chips = startingChips;
        this.currentBet = 0;
        this.hand = new ArrayList<>();
        this.dealtIn = false;
    }

    public void receiveCard(Card card) {
        if (hand.size() < 4) {  // Ensure only 4 cards are dealt as per Omaha Hold'em rules
            hand.add(card);
        } else {
            throw new IllegalStateException("Cannot receive more cards, hand is full.");
        }
    }

    public void placeBet(int amount) {
        if (amount > chips) {
            System.out.println("Warning: Cannot bet more chips than the player has.");
        }
        chips -= amount;  // Subtract the bet amount from the player's chips
    }

    public int call(int calledValue) {
        // Bet the amount of chips necessary to reach the calledValue
        // returns the amount of extra chips paid in order to call
        int extraBet = calledValue - this.currentBet;
        this.placeBet(extraBet);
        this.currentBet = calledValue;
        return extraBet;
    }

    public int raise(int calledValue, int amountRaised) {
        // Bet the amount of chips necessary to reach the calledValue, PLUS the amount raised on top of that
        // returns the amount of extra chips paid in order to call + raise
        int extraBet = call(calledValue) + amountRaised;
        // call(calledValue) above already called placeBet() for the call, only need to remove the extra amount for raise
        this.placeBet(amountRaised);
        this.currentBet = calledValue + amountRaised;
        return extraBet;
    }

    public void clearHand() {
        hand.clear();
    }

    // Getter and setter methods
    public List<Card> getHand() {
        return hand;
    }

    public int getChips() {
        return chips;
    }

    public void setChips(int chips) {
        this.chips = chips;
    }

    public void setDealtIn(boolean dealt) {
        this.dealtIn = dealt;
    }

    public String getName() {
        return name;
    }

    public int getCurrentBet() {
        return currentBet;
    }

    public boolean getDealtIn() {
        return dealtIn;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", chips=" + chips +
                ", hand=" + hand +
                '}';
    }

    public void dealIn() {
        this.dealtIn = true;
        this.currentBet = 0;
    }

    public int getAction() {
        if (!this.dealtIn) {
            return -1; // You don't get to bet if you've already folded
        }

        // For human players, prompt for their action
        int option = UI.promptAction(this);
        if (option == -1) {
            this.clearHand(); // Fold
            this.dealtIn = false;
        }
        return option;
    }

    public void winPot(int pot) {
        this.chips += pot;
    }


}

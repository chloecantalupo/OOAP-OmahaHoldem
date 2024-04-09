import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<Card>();
        initializeDeck();
    }

    private void initializeDeck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card dealCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("Cannot deal from an empty deck.");
        }
        return cards.remove(cards.size() - 1);
    }

    // Nested Card class
    public static class Card {
        private final Suit suit;
        private final Rank rank;

        public Card(Suit suit, Rank rank) {
            this.suit = suit;
            this.rank = rank;
        }

        public Suit getSuit() {
            return suit;
        }

        public Rank getRank() {
            return rank;
        }

        @Override
        public String toString() {
            return rank + " of " + suit;
        }
    }

    // Enum for Suit
    public enum Suit {
        CLUBS, DIAMONDS, HEARTS, SPADES
    }

    // Enum for Rank
    public enum Rank {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
    }

    // Main method for testing
    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();

        for (int i = 0; i < 5; i++) {
            System.out.println(deck.dealCard());
        }
    }
}

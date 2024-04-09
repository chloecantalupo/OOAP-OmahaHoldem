import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Deck.Card> hand;  // Player's hand
    private int chips;             // Player's chip count
    private String name;           // Player's name

    public Player(String name, int startingChips) {
        this.name = name;
        this.chips = startingChips;
        this.hand = new ArrayList<>();
    }

    public void receiveCard(Deck.Card card) {
        if (hand.size() < 4) {  // Ensure only 4 cards are dealt as per Omaha Hold'em rules
            hand.add(card);
        } else {
            throw new IllegalStateException("Cannot receive more cards, hand is full.");
        }
    }

    public void placeBet(int amount) {
        if (amount > chips) {
            throw new IllegalArgumentException("Cannot bet more chips than the player has.");
        }
        chips -= amount;  // Subtract the bet amount from the player's chips
    }

    // Call to match the current bet
    public void call(int currentBet) {
        placeBet(currentBet);
    }

    // Raise the current bet
    public void raise(int raiseAmount, int currentBet) {
        int totalAmount = raiseAmount + currentBet;
        placeBet(totalAmount);
    }

    // Player chooses to fold
    public void fold() {
        hand.clear();  // Clear the player's hand
    }

    // Reset the player's hand for a new round
    public void newRound() {
        hand.clear();
    }

    // Getter and setter methods
    public List<Deck.Card> getHand() {
        return hand;
    }

    public int getChips() {
        return chips;
    }

    public void setChips(int chips) {
        this.chips = chips;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", chips=" + chips +
                ", hand=" + hand +
                '}';
    }
}

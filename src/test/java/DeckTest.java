import org.example.Deck;
import org.junit.jupiter.api.Test;

public class DeckTest {
    @Test
    void testDeckDeal() {
        Deck unshuffled_deck = new Deck();

        for (int i = 0; i < 14; i++) {
            System.out.println(unshuffled_deck.dealCard());
        }
    }

    @Test
    void TestDeckShuffleAndDeal() {
        Deck deck = new Deck();
        deck.shuffle();

        for (int i = 0; i < 5; i++) {
            System.out.println(deck.dealCard());
        }
    }
}

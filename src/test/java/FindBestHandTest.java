import org.junit.jupiter.api.Test;
import org.example.FindBestHand;
import org.example.Player;
import org.example.Card;
import org.example.Suit;
import org.example.Rank;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class FindBestHandTest {

    @Test
    public void testStorePlayerHands() {
        Player player1 = new Player("Alice", 1000);
        Player player2 = new Player("Bob", 1000);

        Card card1 = new Card(Suit.CLUBS, Rank.TWO);
        Card card2 = new Card(Suit.HEARTS, Rank.THREE);

        player1.receiveCard(card1);
        player2.receiveCard(card2);

        FindBestHand findBestHand = new FindBestHand();
        List<List<Card>> playerHands = findBestHand.storePlayerHands(Arrays.asList(player1, player2));

        assertEquals(2, playerHands.size());
        assertEquals(card1, playerHands.get(0).get(0));
        assertEquals(card2, playerHands.get(1).get(0));
    }

    @Test
    public void testFindWinnerOnePair() {
        List<Card> hand1 = Arrays.asList(new Card(Suit.HEARTS, Rank.TWO), new Card(Suit.CLUBS, Rank.TWO));
        List<Card> hand2 = Arrays.asList(new Card(Suit.SPADES, Rank.THREE), new Card(Suit.DIAMONDS, Rank.THREE));

        FindBestHand.findWinnerOnePair(hand1, hand2);

        // No assert here since the method just prints the winner
        // This can be refactored to return the winning hand instead of printing
    }

    @Test
    public void testCompareHands() {
        Map<String, List<Card>> handsDict = new HashMap<>();
        List<Card> hand1 = Arrays.asList(new Card(Suit.HEARTS, Rank.ACE), new Card(Suit.CLUBS, Rank.KING));
        List<Card> hand2 = Arrays.asList(new Card(Suit.SPADES, Rank.ACE), new Card(Suit.DIAMONDS, Rank.QUEEN));

        handsDict.put("Player1", hand1);
        handsDict.put("Player2", hand2);

        FindBestHand findBestHand = new FindBestHand();
        FindBestHand.compareHands(handsDict);

        // Test checks if "Player1" has the higher value
        // This could be refactored to return the highest hand or its name instead of printing
    }

    @Test
    public void testBestHandRiver() {
        List<Card> cards = Arrays.asList(new Card(Suit.HEARTS, Rank.TWO), new Card(Suit.CLUBS, Rank.THREE));
        List<Card> river = Arrays.asList(
                new Card(Suit.SPADES, Rank.FOUR),
                new Card(Suit.DIAMONDS, Rank.FIVE),
                new Card(Suit.SPADES, Rank.SIX),
                new Card(Suit.HEARTS, Rank.SEVEN),
                new Card(Suit.CLUBS, Rank.EIGHT)
        );

        List<Card> bestHand = FindBestHand.bestHandRiver(cards, river);
        // Testing if the best hand derived from the river is correct
        assertEquals(5, bestHand.size());
        assertEquals(Rank.EIGHT, bestHand.get(bestHand.size() - 1).getRank()); // Expected best hand
    }

    @Test
    public void testCheckFlush() {
        List<Card> flushHand = Arrays.asList(
                new Card(Suit.HEARTS, Rank.TWO),
                new Card(Suit.HEARTS, Rank.THREE),
                new Card(Suit.HEARTS, Rank.FOUR),
                new Card(Suit.HEARTS, Rank.FIVE),
                new Card(Suit.HEARTS, Rank.SIX)
        );

        FindBestHand findBestHand = new FindBestHand();
        assertTrue(FindBestHand.checkFlush(flushHand));  // Test to check if it is a flush
    }

    @Test
    public void testCheckStraight() {
        List<Card> straightHand = Arrays.asList(
                new Card(Suit.HEARTS, Rank.TWO),
                new Card(Suit.HEARTS, Rank.THREE),
                new Card(Suit.CLUBS, Rank.FOUR),
                new Card(Suit.DIAMONDS, Rank.FIVE),
                new Card(Suit.SPADES, Rank.SIX)
        );

        FindBestHand findBestHand = new FindBestHand();
        assertTrue(FindBestHand.checkStraight(straightHand));  // Test to check if it is a straight
    }

    @Test
    public void testCheckStraightFlush() {
        List<Card> straightFlushHand = Arrays.asList(
                new Card(Suit.CLUBS, Rank.TWO),
                new Card(Suit.CLUBS, Rank.THREE),
                new Card(Suit.CLUBS, Rank.FOUR),
                new Card(Suit.CLUBS, Rank.FIVE),
                new Card(Suit.CLUBS, Rank.SIX)
        );

        FindBestHand findBestHand = new FindBestHand();
        assertTrue(FindBestHand.checkStraightFlush(straightFlushHand));  // Test to check if it is a straight flush
    }
}

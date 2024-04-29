import org.example.Card;
import org.example.FindBestHandHelper;
import org.example.Rank;
import org.example.Suit;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class FindBestHandHelperTest {

    @Test
    public void findHigherStraight_returnsHigherStraightHand() {
        List<Card> hand1 = Arrays.asList(new Card(Suit.CLUBS, Rank.TWO), new Card(Suit.CLUBS, Rank.THREE));
        List<Card> hand2 = Arrays.asList(new Card(Suit.CLUBS, Rank.FOUR), new Card(Suit.CLUBS, Rank.FIVE));
        assertEquals(hand2, FindBestHandHelper.findHigherStraight(hand1, hand2));
    }

    @Test
    public void findHigherStraight_returnsNullWhenHandsAreEqual() {
        List<Card> hand1 = Arrays.asList(new Card(Suit.CLUBS, Rank.TWO), new Card(Suit.CLUBS, Rank.THREE));
        List<Card> hand2 = Arrays.asList(new Card(Suit.CLUBS, Rank.TWO), new Card(Suit.CLUBS, Rank.THREE));
        assertNull(FindBestHandHelper.findHigherStraight(hand1, hand2));
    }

    @Test
    public void findHigherPair_returnsHigherPairHand() {
        List<Card> hand1 = Arrays.asList(new Card(Suit.CLUBS, Rank.TWO), new Card(Suit.HEARTS, Rank.TWO));
        List<Card> hand2 = Arrays.asList(new Card(Suit.CLUBS, Rank.THREE), new Card(Suit.HEARTS, Rank.THREE));
        assertEquals(hand2, FindBestHandHelper.findHigherPair(hand1, hand2));
    }

    @Test
    public void findHigherPair_returnsNullWhenNoPairs() {
        List<Card> hand1 = Arrays.asList(new Card(Suit.CLUBS, Rank.TWO), new Card(Suit.HEARTS, Rank.THREE));
        List<Card> hand2 = Arrays.asList(new Card(Suit.CLUBS, Rank.FOUR), new Card(Suit.HEARTS, Rank.FIVE));
        assertNull(FindBestHandHelper.findHigherPair(hand1, hand2));
    }

    @Test
    public void findHigherThreeOfAKind_returnsHigherThreeOfAKindHand() {
        List<Card> hand1 = Arrays.asList(new Card(Suit.CLUBS, Rank.TWO), new Card(Suit.HEARTS, Rank.TWO), new Card(Suit.DIAMONDS, Rank.TWO));
        List<Card> hand2 = Arrays.asList(new Card(Suit.CLUBS, Rank.THREE), new Card(Suit.HEARTS, Rank.THREE), new Card(Suit.DIAMONDS, Rank.THREE));
        assertEquals(hand2, FindBestHandHelper.findHigherThreeOfAKind(hand1, hand2));
    }

    @Test
    public void findHigherThreeOfAKind_returnsNullWhenNoThreeOfAKind() {
        List<Card> hand1 = Arrays.asList(new Card(Suit.CLUBS, Rank.TWO), new Card(Suit.HEARTS, Rank.THREE), new Card(Suit.DIAMONDS, Rank.FOUR));
        List<Card> hand2 = Arrays.asList(new Card(Suit.CLUBS, Rank.FIVE), new Card(Suit.HEARTS, Rank.SIX), new Card(Suit.DIAMONDS, Rank.SEVEN));
        assertNull(FindBestHandHelper.findHigherThreeOfAKind(hand1, hand2));
    }
}

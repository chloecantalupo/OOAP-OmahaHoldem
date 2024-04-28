import org.junit.jupiter.api.Test;
import org.example.Card;
import org.example.Rank;
import org.example.Suit;
import org.example.GenerateHandCombinations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CombinationsTest {

    @Test
    public void generateCombinations_withSevenCards_returnsExpectedCombinations() {
        List<Card> allCards = Arrays.asList(
                new Card(Suit.HEARTS, Rank.TWO),
                new Card(Suit.CLUBS, Rank.THREE),
                new Card(Suit.SPADES, Rank.FOUR),
                new Card(Suit.DIAMONDS, Rank.FIVE),
                new Card(Suit.SPADES, Rank.SIX),
                new Card(Suit.HEARTS, Rank.SEVEN),
                new Card(Suit.CLUBS, Rank.EIGHT)
        );

        List<List<Card>> combinations = GenerateHandCombinations.generateCombinations(allCards);

        assertEquals(21, combinations.size());
    }

    @Test
    public void generateCombinations_withLessThanSevenCards_throwsException() {
        List<Card> allCards = Arrays.asList(
                new Card(Suit.HEARTS, Rank.TWO),
                new Card(Suit.CLUBS, Rank.THREE),
                new Card(Suit.SPADES, Rank.FOUR)
        );

        assertThrows(IllegalArgumentException.class, () -> GenerateHandCombinations.generateCombinations(allCards));
    }

    @Test
    public void generateCombinations_withMoreThanSevenCards_throwsException() {
        List<Card> allCards = Arrays.asList(
                new Card(Suit.HEARTS, Rank.TWO),
                new Card(Suit.CLUBS, Rank.THREE),
                new Card(Suit.SPADES, Rank.FOUR),
                new Card(Suit.DIAMONDS, Rank.FIVE),
                new Card(Suit.SPADES, Rank.SIX),
                new Card(Suit.HEARTS, Rank.SEVEN),
                new Card(Suit.CLUBS, Rank.EIGHT),
                new Card(Suit.CLUBS, Rank.NINE)
        );

        assertThrows(IllegalArgumentException.class, () -> GenerateHandCombinations.generateCombinations(allCards));
    }
}
import org.example.FindBestHand;
import org.example.Player;
import org.example.Card;
import org.example.Suit;
import org.example.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindWinnerTest{

    private Player player1;
    private Player player2;
    private List<Card> river;

    @Test
    @DisplayName("Winner is correctly determined when one player has a higher hand")
    public void winnerIsCorrectlyDeterminedWhenOnePlayerHasHigherHand() {
        player1 = new Player("Player 1", 100);
        player1.receiveCard(new Card(Suit.HEARTS, Rank.ACE));
        player1.receiveCard(new Card(Suit.CLUBS, Rank.KING));

        player2 = new Player("Player 2", 100);
        player2.receiveCard(new Card(Suit.SPADES, Rank.ACE));
        player2.receiveCard(new Card(Suit.DIAMONDS, Rank.QUEEN));

        List<Card> river = Arrays.asList(
                new Card(Suit.SPADES, Rank.TWO),
                new Card(Suit.DIAMONDS, Rank.FIVE),
                new Card(Suit.SPADES, Rank.SIX),
                new Card(Suit.HEARTS, Rank.SEVEN),
                new Card(Suit.CLUBS, Rank.EIGHT)
);
        List<Player> players = Arrays.asList(player1, player2);

        Player winner = FindBestHand.findWinner(players, river);
        assertEquals(player1, winner);
    }

    @Test
    @DisplayName("Winner is correctly determined when both players have the same hand")
    public void winnerIsCorrectlyDeterminedWhenBothPlayersHaveSameHand() {
        player1 = new Player("Player 1", 100);
        player1.receiveCard(new Card(Suit.HEARTS, Rank.ACE));
        player1.receiveCard(new Card(Suit.CLUBS, Rank.KING));

        player2 = new Player("Player 2", 100);

        List<Card> river = Arrays.asList(
                new Card(Suit.SPADES, Rank.TWO),
                new Card(Suit.DIAMONDS, Rank.FIVE),
                new Card(Suit.SPADES, Rank.SIX),
                new Card(Suit.HEARTS, Rank.SEVEN),
                new Card(Suit.CLUBS, Rank.EIGHT)
        );
        player2.receiveCard(new Card(Suit.HEARTS, Rank.ACE));
        player2.receiveCard(new Card(Suit.CLUBS, Rank.KING));
        Player winner = FindBestHand.findWinner(Arrays.asList(player1, player2), river);
        assertEquals(player1, winner);
    }

    @Test
    @DisplayName("Winner is correctly determined when there are no players")
    public void winnerIsCorrectlyDeterminedWhenThereAreNoPlayers() {
        player1 = new Player("Player 1", 100);
        player1.receiveCard(new Card(Suit.HEARTS, Rank.ACE));
        player1.receiveCard(new Card(Suit.CLUBS, Rank.KING));

        player2 = new Player("Player 2", 100);
        player2.receiveCard(new Card(Suit.SPADES, Rank.ACE));
        player2.receiveCard(new Card(Suit.DIAMONDS, Rank.QUEEN));

        List<Card> river = Arrays.asList(
                new Card(Suit.SPADES, Rank.TWO),
                new Card(Suit.DIAMONDS, Rank.FIVE),
                new Card(Suit.SPADES, Rank.SIX),
                new Card(Suit.HEARTS, Rank.SEVEN),
                new Card(Suit.CLUBS, Rank.EIGHT)
        );
        Player winner = FindBestHand.findWinner(Arrays.asList(), river);
        assertEquals(null, winner);
    }
}
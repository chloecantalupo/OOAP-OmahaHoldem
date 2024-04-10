import org.example.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {
    @Test
    void testPlayer() {
        Player p = PlayerFactory.getHumanPlayer("Player 1", 100);
        p.setChips(100);
        p.receiveCard(new Card(Suit.HEARTS, Rank.ACE));
        p.clearHand();
        p.call(10);
        assertEquals(p.getChips(), 90);
    }
}

import org.example.OmahaHoldemGame;
import org.junit.jupiter.api.Test;

public class GameTest {
    @Test
    void testBasic2PlayerGame() {
        OmahaHoldemGame game = new OmahaHoldemGame(2, 100, 5);
        game.startGame();
    }
}

import org.example.OmahaHoldemGame;
import org.junit.jupiter.api.Test;

public class GameTest {
    @Test
    void testBasic2PlayerGame() {
        OmahaHoldemGame game = new OmahaHoldemGame(2, 100);
        game.startGame();
    }

    @Test
    void testFullBotGame() {
        OmahaHoldemGame game = new OmahaHoldemGame.Builder().fullBotTable();
        game.startGame();
    }

    @Test
    void testTwoBotGame() {
        OmahaHoldemGame game = new OmahaHoldemGame.Builder().twoBotTable();
        game.startGame();
    }

}

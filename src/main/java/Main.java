import com.gtako.dev.controller.GameService;
import com.gtako.dev.model.Game;

public class Main {

    private static final GameService gameService = new GameService();

    public static void main(String[] args) {

        // Initialize the game
        Game game = gameService.initializeGame("Treasure_Hunt_2.txt");

        if (game != null) {
            System.out.println("initial map");
            System.out.println(game.toString());
        }

        // Execute Adventurers moves
        gameService.run(game);

        // Generate Final Result


    }
}

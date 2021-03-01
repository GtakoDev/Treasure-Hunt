import com.gtako.dev.controller.GameService;
import com.gtako.dev.model.Game;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Classe qui execute le programme
 *
 * @author Guillaume T.
 */
public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final GameService gameService = new GameService();

    public static void main(String[] args) {
        Game game = gameService.initializeGame("Treasure_Hunt_2.txt");

        if (game != null) {
            logger.info(String.format("Carte Initiale : %n%s", game.toString()));

            gameService.run(game);

            gameService.generateResult(game);
        } else {
            logger.error("Une erreur est survenue lors de l'initialisation du jeu");
        }
    }
}

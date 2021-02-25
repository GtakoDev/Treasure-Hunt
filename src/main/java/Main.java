import com.gtako.dev.controller.GameService;
import com.gtako.dev.model.Map;

public class Main {

    private static final GameService gameService = new GameService();

    public static void main(String[] args) {

        Map map = gameService.initializeGame("Treasure_Hunt_2.txt");

        if (map != null) {
            System.out.println(map.toString());
        }


    }
}

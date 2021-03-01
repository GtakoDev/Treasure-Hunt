package com.gtako.dev.controller;

import com.gtako.dev.model.Adventurer;
import com.gtako.dev.model.Box;
import com.gtako.dev.model.Game;
import com.gtako.dev.model.Orientation;
import com.gtako.dev.utils.AppConst;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Map;
import java.util.Scanner;

/**
 * Classe qui définit les méthodes concernant le jeu
 *
 * @author Guillaume T.
 */
public class GameService {
    private static final Logger logger = LogManager.getLogger(GameService.class);
    private final MapService mapService = new MapService();
    private final AdventurerService adventurerService = new AdventurerService();
    private final BoxService boxService = new BoxService();
    private final OrientationService orientationService = new OrientationService();

    /**
     * Initialize le jeu a partir d'un fichier et le retourne
     *
     * @param configFileName nom du fichier
     * @return le jeu
     */
    public Game initializeGame(String configFileName) {
        logger.debug("Début de l'initialisation de la partie.");
        Game game = new Game();

        try (Scanner scanner = new Scanner(new File(AppConst.RESOURCE_DIR + configFileName))) {
            while (scanner.hasNext()) {

                String currentLine = scanner.nextLine().replaceAll("\\s+", "");

                if (currentLine.startsWith("C")) {
                    String[] dimensions = currentLine.split("-");
                    int width = Integer.parseInt(dimensions[1]);
                    int height = Integer.parseInt(dimensions[2]);
                    game = new Game(width, height);
                }

                if (currentLine.startsWith("M")) {
                    String[] coordinates = currentLine.split("-");
                    int positionX = Integer.parseInt(coordinates[1]);
                    int positionY = Integer.parseInt(coordinates[2]);
                    mapService.setMoutain(game.getMap(), positionX, positionY);

                }

                if (currentLine.startsWith("T")) {
                    String[] parts = currentLine.split("-");
                    int positionX = Integer.parseInt(parts[1]);
                    int positionY = Integer.parseInt(parts[2]);
                    int treasureCount = Integer.parseInt(parts[3]);
                    mapService.setTreasure(game.getMap(), positionX, positionY, treasureCount);
                }

                if (currentLine.startsWith("A")) {
                    // adventurer
                    String[] parts = currentLine.split("-");
                    String name = parts[1];
                    int posX = Integer.parseInt(parts[2]);
                    int posY = Integer.parseInt(parts[3]);
                    Orientation adventurerOrientation = orientationService.getOrientationFromLetter(parts[4]);
                    String moveSequence = parts[5];
                    Adventurer adventurer = new Adventurer(name, posX, posY, adventurerOrientation, moveSequence);
                    mapService.addAdventurer(game.getAdventurers(), adventurer);
                    game.getMap()[posX][posY].setAdventurer(adventurer);
                }

            }
            logger.debug("Fin de l'initialisation de la partie.");
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
            return null;
        }

        return game;
    }

    /**
     * Execute les mouvements de chaque aventuriers jusqu'a au tour par tour
     *
     * @param game le jeu
     */
    public void run(Game game) {

        logger.info("Début de la phase de jeu.");
        boolean endOfTheGame = false;

        while (!endOfTheGame) {

            for (Map.Entry<Adventurer, Boolean> entry : game.getAdventurers().entrySet()) {

                String moves = entry.getKey().getMovements();

                switch (moves.charAt(0)) {
                    case 'A' -> adventurerService.moveForward(entry.getKey(), game);
                    case 'D' -> adventurerService.turnRight(entry.getKey());
                    case 'G' -> adventurerService.turnLeft(entry.getKey());
                    default -> throw new RuntimeException("Une lettre non reconnue à été détectée dans le mouvement du personnage. Elle sera ignorée.");
                }

                if (moves.length() == 1) {
                    entry.getKey().setMovements("");
                    entry.setValue(false);
                } else {
                    entry.getKey().setMovements(moves.substring(1));
                }
            }

            logger.info(String.format("%n%s",game.toString()));
            endOfTheGame = game.getAdventurers().values().stream().allMatch(Boolean.FALSE::equals);

        }

        logger.info("Fin de la phase de jeu.");
    }

    /**
     * Génère un fichier contenant le résultat final de la partie joué
     * 
     * @param game
     */
    public void generateResult(Game game) {
        logger.debug("Début de la génération du fichier de résultat du jeu.");
        try {
            FileWriter fileWriter = new FileWriter(AppConst.RESOURCE_DIR + "Treasure_Hunt_Result.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            Box[][] gameMap = game.getMap();

            printWriter.println("# {C comme Carte} - {Nb. de case en largeur} - {Nb. de case en hauteur}");
            printWriter.printf("C - %d - %d%n", game.getWidth(), game.getHeight());

            printWriter.println("# {M comme Montagne} - {Axe horizontal} - {Axe vertical}");
            for (int i = 0; i < game.getHeight(); i++) {
                for (int j = 0; j < game.getWidth(); j++) {
                    if (boxService.isMoutain(gameMap[j][i])) {
                        printWriter.printf("M - %d - %d%n", j, i);
                    }
                }
            }

            printWriter.println("# {T comme Trésor} - {Axe horizontal} - {Axe vertical} - {Nb. de Trésors restants}");
            for (int i = 0; i < game.getHeight(); i++) {
                for (int j = 0; j < game.getWidth(); j++) {

                    if (boxService.isTreasure(gameMap[j][i])) {
                        printWriter.printf("T - %d - %d - %d%n", j, i, boxService.getNumberOfTreasure(gameMap[j][i]));
                    }
                }
            }

            printWriter.println("# {A comme Aventurier} - {Nom de l'aventurier} - {Axe horizontal} - {Axe vertical} - {Orientation} - {Nb. de Trésors ramassés}");
            for (Adventurer a : game.getAdventurers().keySet()) {
                printWriter.printf("A - %s - %d - %d - %s - %d%n", a.getName(), a.getPositionX(), a.getPositionY(), a.getOrientation().getDirection(), a.getNumberOfCollectedTreasures());
            }

            printWriter.close();
            logger.debug("Fin de la génération du fichier de résulat du jeu.");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}

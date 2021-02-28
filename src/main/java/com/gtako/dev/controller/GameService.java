package com.gtako.dev.controller;

import com.gtako.dev.model.Adventurer;
import com.gtako.dev.model.Game;
import com.gtako.dev.model.Orientation;
import com.gtako.dev.utils.AppConst;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class GameService {

    private final MapService mapService = new MapService();
    private final AdventurerService adventurerService = new AdventurerService();
    private final BoxService boxService = new BoxService();
    private final OrientationService orientationService = new OrientationService();


    public Game initializeGame(String configFileName) {

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
                    // mapService.addAdventurer(gameGame.getAdventurers() ,new Adventurer(name, posX, posY, adventurerOrientation, moveSequence));
                    Adventurer adventurer = new Adventurer(name, posX, posY, adventurerOrientation, moveSequence);
                    mapService.addAdventurer(game.getAdventurers() , adventurer);
                    game.getMap()[posX][posY].setAdventurer(adventurer);
                }

            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
            return null;
        }

        return game;
    }

    public void run(Game game) {

        boolean endOfTheGame = false;
        System.out.println("Game Starting ...");

        while (!endOfTheGame) {

            // adventurer hasNextMove()
            for (Map.Entry<Adventurer, Boolean> entry : game.getAdventurers().entrySet()) {

                String moves = entry.getKey().getMovements();

                switch (moves.charAt(0)) {
                    case 'A'  -> adventurerService.moveForward(entry.getKey(), game);
                    case 'D' -> adventurerService.turnRight(entry.getKey());
                    case 'G' -> adventurerService.turnLeft(entry.getKey());
                    default -> throw new RuntimeException("Wrong move letter has been detected");
                }

                // entry.getKey().setMovements( moves.length() == 1 ? "" : moves.substring(1) );
                if ( moves.length() == 1 ) {
                    entry.getKey().setMovements("");
                    entry.setValue(false);
                } else {
                    entry.getKey().setMovements(moves.substring(1));
                }
            }

            System.out.println(game.toString());

            endOfTheGame = game.getAdventurers().values().stream().allMatch(Boolean.FALSE::equals);

        }

        System.out.println("Game Done");
    }
}

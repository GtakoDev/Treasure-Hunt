package com.gtako.dev.controller;

import com.gtako.dev.model.Adventurer;
import com.gtako.dev.model.Map;
import com.gtako.dev.model.Orientation;
import com.gtako.dev.utils.AppConst;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameService {

    private final MapService mapService = new MapService();
    private final AdventurerService adventurerService = new AdventurerService();
    private final BoxService boxService = new BoxService();
    private final OrientationService orientationService = new OrientationService();


    public Map initializeGame(String configFileName) {

        Map gameMap = new Map();

        try (Scanner scanner = new Scanner(new File(AppConst.RESOURCE_DIR + configFileName))) {
            while (scanner.hasNext()) {

                String currentLine = scanner.nextLine().replaceAll("\\s+", "");

                if (currentLine.startsWith("C")) {
                    String[] dimensions = currentLine.split("-");
                    gameMap = new Map(Integer.parseInt(dimensions[1]), Integer.parseInt(dimensions[2]));
                }

                if (currentLine.startsWith("M")) {
                    String[] coordinates = currentLine.split("-");
                    mapService.setMoutain(gameMap.getMap(), Integer.parseInt(coordinates[1]), Integer.parseInt(coordinates[1]));

                }

                if (currentLine.startsWith("T")) {
                    String[] parts = currentLine.split("-");
                    mapService.setTreasure(gameMap.getMap(), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
                }

                if (currentLine.startsWith("A")) {

                    // adventurer
                    String[] parts = currentLine.split("-");
                    String name = parts[1];
                    int posX = Integer.parseInt(parts[2]);
                    int posY = Integer.parseInt(parts[3]);
                    Orientation adventurerOrientation = orientationService.getOrientationFromLetter(parts[4]);
                    String moveSequence = parts[5];
                    mapService.addAdventurer(gameMap.getAdventurers() ,new Adventurer(name, posX, posY, adventurerOrientation, moveSequence));

                }

            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
            return null;
        }

        return gameMap;
    }

}

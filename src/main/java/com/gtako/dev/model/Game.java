package com.gtako.dev.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe qui définit le modèle du jeu
 *
 * @author Guillaume T.
 */
public class Game {
    private Box[][] map; // hauteur x largeur
    private int height;
    private int width;
    private Map<Adventurer, Boolean> adventurers;

    public Game(int mapWidth, int mapHeight) {
        initializeMap(mapWidth, mapHeight);
    }

    public Game() {
        initializeMap(1, 1);
    }

    /*****************************
     * METHODES GETTERS ET SETTERS
     *****************************/

    public Box[][] getMap() {
        return map;
    }

    public Map<Adventurer, Boolean> getAdventurers() {
        return adventurers;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    /*****************************
     * METHODES PRIVE
     *****************************/

    private void initializeMap(int width, int height) {
        this.height = height;
        this.width = width;
        this.adventurers = new HashMap<>();

        fillMap();
    }

    private void fillMap() {
        this.map = new Box[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                map[i][j] = new Box(0);
            }
        }
    }

    /*****************************
     * METHODES OVERRIDE
     *****************************/

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                sb.append(" [ ").append(this.map[j][i].toString()).append(" ]\t\t\t");
            }

            sb.append("\n");
        }

        sb.append("\n");

        for (Map.Entry<Adventurer, Boolean> entry : adventurers.entrySet()) {
            Adventurer adventurer = entry.getKey();
            Boolean canMove = entry.getValue();
            sb.append(adventurer).append(" peut bouger :").append(canMove);
        }

        return sb.toString();
    }
}

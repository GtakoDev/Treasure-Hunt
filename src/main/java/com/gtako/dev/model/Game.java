package com.gtako.dev.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

    private Box[][] map; // hauteur x largeur
    private int height;
    private int width;
    // private List<Adventurer> adventurers;
    private Map<Adventurer, Boolean> adventurers;


    public Game(int mapWidth, int mapHeight) {
        initializeMap(mapWidth, mapHeight);
    }

    public Game() {
        initializeMap(1, 1);
    }

    private void initializeMap(int width, int height) {
        this.height = height;
        this.width = width;
        // this.adventurers = new ArrayList<>();
        this.adventurers = new HashMap<>();

        fillMap();
    }

    private void fillMap() {
        this.map = new Box[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                map[i][j] = new Box(i, j, 0);
            }
        }
    }

    public Box[][] getMap() {
        return map;
    }

    public void setMap(Box[][] map) {
        this.map = map;
    }

    /*public List<Adventurer> getAdventurers() {
        return adventurers;
    }*/

    public Map<Adventurer, Boolean> getAdventurers() {
        return adventurers;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

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

        /*for (Adventurer adventurer :
                adventurers) {
            sb.append(adventurer.toString());

        }*/

        for (Map.Entry<Adventurer, Boolean> entry : adventurers.entrySet()) {
            Adventurer adventurer = entry.getKey();
            Boolean canMove = entry.getValue();
            sb.append(adventurer).append(" can move :").append(canMove);
        }

        return sb.toString();
    }
}

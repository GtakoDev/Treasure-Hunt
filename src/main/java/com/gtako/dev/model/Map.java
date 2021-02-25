package com.gtako.dev.model;

import java.util.ArrayList;
import java.util.List;

public class Map {

    private Box[][] map; // hauteur x largeur
    private int height;
    private int width;
    private List<Adventurer> adventurers;

    public Map(int width, int height) {
        initializeMap(width, height);
    }

    public Map() {
        initializeMap(1,1);
    }

    private void initializeMap(int width, int height) {
        this.height = height;
        this.width = width;
        this.adventurers = new ArrayList<>();

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

    public List<Adventurer> getAdventurers() {
        return adventurers;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                sb.append(" [ ").append(this.map[j][i].toString()).append(" ]");
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}

package com.gtako.dev.model;

public class Map {

    private Box[][] map; // hauteur x largeur
    private final int height;
    private final int width;

    public Map(int width, int height) {
        this.height = height;
        this.width = width;

        initializeMap();
    }

    private void initializeMap() {
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
}

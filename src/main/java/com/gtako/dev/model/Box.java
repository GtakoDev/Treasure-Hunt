package com.gtako.dev.model;

public class Box {
    private int positionX;
    private int positionY;
    private int terrainType; // -1 = Mountain, 0 = Plain, > 0 = number of treasures

    public Box(int positionX, int positionY, int terrainType) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.terrainType = terrainType;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getTerrainType() {
        return terrainType;
    }

    public void setTerrainType(int terrainType) {
        this.terrainType = terrainType;
    }

    @Override
    public String toString() {
        if (terrainType == -1) {
            return "M";
        } else if (terrainType == 0) {
            return "•"; // Alt + 7
        } else if (terrainType > 0) {
            return "T";
        }

        return "";
    }

}

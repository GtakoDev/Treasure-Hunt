package com.gtako.dev.model;

public class Box {
    private int positionX;
    private int positionY;
    private int terrainType; // -1 = Mountain, 0 = Plain, > 0 = number of treasures
    private Adventurer adventurer;

    public Box(int positionX, int positionY, int terrainType) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.terrainType = terrainType;
        this.adventurer = null;
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

    public Adventurer getAdventurer() {
        return adventurer;
    }

    public void setAdventurer(Adventurer adventurer) {
        this.adventurer = adventurer;
    }

    @Override
    public String toString() {
        if (adventurer != null) {
            return "A(" + adventurer.getName() + ")";
        } else if (terrainType == -1) {
            return "M";
        } else if (terrainType == 0) {
            return "•"; // Alt + 7
        } else if (terrainType > 0) {
            return "T(" + terrainType + ")" ;
        }

        return "";
    }

}

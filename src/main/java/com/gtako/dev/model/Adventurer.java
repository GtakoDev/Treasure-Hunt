package com.gtako.dev.model;

public class Adventurer {
    private String name;
    private int positionX;
    private int positionY;
    private int numberOfCollectedTreasures;
    private Orientation orientation;
    private String movements;

    public Adventurer(String name, int positionX, int positionY, Orientation orientation, String movements) {
        this.name = name;
        this.positionX = positionX;
        this.positionY = positionY;
        this.orientation = orientation;
        this.movements = movements;
        this.numberOfCollectedTreasures = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public String getMovements() {
        return movements;
    }

    public void setMovements(String movements) {
        this.movements = movements;
    }

    public int getNumberOfCollectedTreasures() {
        return numberOfCollectedTreasures;
    }

    public void setNumberOfCollectedTreasures(int numberOfCollectedTreasures) {
        this.numberOfCollectedTreasures = numberOfCollectedTreasures;
    }

    @Override
    public String toString() {
        return "Adventurer{" +
                "name='" + name + '\'' +
                ", positionX=" + positionX +
                ", positionY=" + positionY +
                ", numberOfCollectedTreasures=" + numberOfCollectedTreasures +
                ", orientation=" + orientation +
                ", movements='" + movements + '\'' +
                '}';
    }
}

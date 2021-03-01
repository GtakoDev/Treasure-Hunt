package com.gtako.dev.model;

/**
 * Classe qui définit le modèle d'un Aventurier
 *
 * @author Guillaume T.
 */
public class Adventurer {
    private final String name;
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

    /*****************************
     * GETTERS ET SETTERSs
     *****************************/

    public String getName() {
        return name;
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

    /*****************************
     * METHODES OVERRIDE
     *****************************/

    @Override
    public String toString() {
        return "Adventurer{" +
                "nom='" + name + '\'' +
                ", positionX=" + positionX +
                ", positionY=" + positionY +
                ", nombre de trésors collectés=" + numberOfCollectedTreasures +
                ", orientation=" + orientation +
                ", séquence de mouvements='" + movements + '\'' +
                '}';
    }
}

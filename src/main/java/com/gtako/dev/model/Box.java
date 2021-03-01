package com.gtako.dev.model;

/**
 * Classe qui définit le modèle d'un case de la carte
 *
 * @author Guillaume T.
 */
public class Box {
    private int terrainType; // -1 = Mountain, 0 = Plain, > 0 = number of treasures
    private Adventurer adventurer;

    public Box(int terrainType) {
        this.terrainType = terrainType;
        this.adventurer = null;
    }

    /*****************************
     * GETTERS ET SETTERS
     *****************************/

    public int getTerrainType() {
        return terrainType;
    }

    public void setTerrainType(int terrainType) {
        this.terrainType = terrainType;
    }

    public void setAdventurer(Adventurer adventurer) {
        this.adventurer = adventurer;
    }

    /*****************************
     * METHODES OVERRIDES
     *****************************/

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

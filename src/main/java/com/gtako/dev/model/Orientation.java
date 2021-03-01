package com.gtako.dev.model;

/**
 * Classe qui définit le modèle de l'orientation
 *
 * @author Guillaume T.
 */
public enum Orientation {
    NORTH("N"),
    EAST("E"),
    SOUTH("S"),
    WEST("W");

    String direction;

    private Orientation(String value) {
        direction = value;
    }

    public String getDirection() {
        return direction;
    }
}

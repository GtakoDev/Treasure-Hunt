package com.gtako.dev.model;

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

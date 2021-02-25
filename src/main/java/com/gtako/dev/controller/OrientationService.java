package com.gtako.dev.controller;

import com.gtako.dev.model.Orientation;

public class OrientationService {

    public Orientation getOrientationFromLetter(String orientation) {
        return switch (orientation) {
            case "N" -> Orientation.NORTH;
            case "E" -> Orientation.EAST;
            case "S" -> Orientation.SOUTH;
            case "W" -> Orientation.WEST;
            default -> throw new RuntimeException("La lettre en paramètre ne correspond a aucune orientation");
        };
    }
}

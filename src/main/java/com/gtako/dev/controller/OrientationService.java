package com.gtako.dev.controller;

import com.gtako.dev.model.Orientation;

/**
 * Classe qui regroupe des méthodes concernant l'orientation
 *
 * @author Guillaume T.
 */
public class OrientationService {

    /**
     * Retourne l'objet orientation adéquat en fonction de la lettre en paramètre.
     *
     * @param orientation lettre acceptés (N, E, S, W)
     * @return l'objet orientation
     */
    public Orientation getOrientationFromLetter(String orientation) {
        return switch (orientation) {
            case "N" -> Orientation.NORTH;
            case "E" -> Orientation.EAST;
            case "S" -> Orientation.SOUTH;
            case "W" -> Orientation.WEST;
            default -> throw new RuntimeException("La lettre en paramètre ne correspond à aucune orientation");
        };
    }
}

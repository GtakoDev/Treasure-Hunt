package com.gtako.dev.controller;

import com.gtako.dev.model.Adventurer;
import com.gtako.dev.model.Orientation;

public class AdventurerService {

    public void turnRight(Adventurer adventurer) {
        switch ( adventurer.getOrientation() ) {
            case NORTH -> adventurer.setOrientation(Orientation.EAST);
            case EAST -> adventurer.setOrientation(Orientation.SOUTH);
            case SOUTH -> adventurer.setOrientation(Orientation.WEST);
            case WEST -> adventurer.setOrientation(Orientation.NORTH);
            default -> throw new RuntimeException("La valeur de l'orientation de l'aventurier est invalide");
        }
    }

    public void turnLeft(Adventurer adventurer) {
        switch ( adventurer.getOrientation() ) {
            case NORTH -> adventurer.setOrientation(Orientation.WEST);
            case EAST -> adventurer.setOrientation(Orientation.NORTH);
            case SOUTH -> adventurer.setOrientation(Orientation.EAST);
            case WEST -> adventurer.setOrientation(Orientation.SOUTH);
            default -> throw new RuntimeException("La valeur de l'orientation de l'aventurier est invalide");
        }
    }

    public void moveForward(Adventurer adventurer) {
        switch ( adventurer.getOrientation() ) {
            case NORTH -> decrementY(adventurer);
            case EAST ->  incrementX(adventurer);
            case SOUTH -> incrementY(adventurer);
            case WEST ->  decrementX(adventurer);
            default -> throw new RuntimeException("valeur orientation invalide");
        }
    }

    private void incrementX(Adventurer adventurer) {
        adventurer.setPositionX(adventurer.getPositionX() + 1);
    }

    private void decrementX(Adventurer adventurer) {
        adventurer.setPositionX(adventurer.getPositionX() - 1);
    }

    private void incrementY(Adventurer adventurer) {
        adventurer.setPositionY(adventurer.getPositionY() + 1);
    }

    private void decrementY(Adventurer adventurer) {
        adventurer.setPositionY(adventurer.getPositionY() -1);
    }
}

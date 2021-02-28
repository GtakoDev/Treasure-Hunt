package com.gtako.dev.controller;

import com.gtako.dev.model.Adventurer;
import com.gtako.dev.model.Game;
import com.gtako.dev.model.Orientation;

public class AdventurerService {

    public void turnRight(Adventurer adventurer) {
        switch (adventurer.getOrientation()) {
            case NORTH -> adventurer.setOrientation(Orientation.EAST);
            case EAST -> adventurer.setOrientation(Orientation.SOUTH);
            case SOUTH -> adventurer.setOrientation(Orientation.WEST);
            case WEST -> adventurer.setOrientation(Orientation.NORTH);
            default -> throw new RuntimeException("La valeur de l'orientation de l'aventurier est invalide");
        }
    }

    public void turnLeft(Adventurer adventurer) {
        switch (adventurer.getOrientation()) {
            case NORTH -> adventurer.setOrientation(Orientation.WEST);
            case EAST -> adventurer.setOrientation(Orientation.NORTH);
            case SOUTH -> adventurer.setOrientation(Orientation.EAST);
            case WEST -> adventurer.setOrientation(Orientation.SOUTH);
            default -> throw new RuntimeException("La valeur de l'orientation de l'aventurier est invalide");
        }
    }

    public void moveForward(Adventurer adventurer, Game game) {
        switch (adventurer.getOrientation()) {
            case NORTH -> {
                if (canMoveNorth(adventurer, game)) {
                    game.getMap()[adventurer.getPositionX()][adventurer.getPositionY()].setAdventurer(null);
                    decrementY(adventurer);
                } else {
                    System.out.println("Impossible de se déplacer au nord");
                }
            }
            case EAST -> {
                if (canMoveEast(adventurer, game)) {
                    game.getMap()[adventurer.getPositionX()][adventurer.getPositionY()].setAdventurer(null);
                    incrementX(adventurer);
                } else {
                    System.out.println("Impossible de se déplacer a l'est");
                }

            }
            case SOUTH -> {
                if (canMoveSouth(adventurer, game)) {
                    game.getMap()[adventurer.getPositionX()][adventurer.getPositionY()].setAdventurer(null);
                    incrementY(adventurer);
                } else {
                    System.out.println("Impossible de se déplacer au sud");
                }
            }
            case WEST -> {
                if (canMoveWest(adventurer, game)) {
                    game.getMap()[adventurer.getPositionX()][adventurer.getPositionY()].setAdventurer(null);
                    decrementX(adventurer);
                } else {
                    System.out.println("Impossible de se déplacer a l'ouest");
                }
            }
            default -> throw new RuntimeException("valeur de l'orientation invalide");
        }

        game.getMap()[adventurer.getPositionX()][adventurer.getPositionY()].setAdventurer(adventurer);

        if (game.getMap()[adventurer.getPositionX()][adventurer.getPositionY()].getTerrainType() > 0) {
            int treasureCount = game.getMap()[adventurer.getPositionX()][adventurer.getPositionY()].getTerrainType();
            game.getMap()[adventurer.getPositionX()][adventurer.getPositionY()].setTerrainType(treasureCount - 1);
            adventurer.setNumberOfCollectedTreasures(adventurer.getNumberOfCollectedTreasures() + 1 );
        }
    }

    private boolean canMoveWest(Adventurer adventurer, Game game) {
        // TODO Ajouter les collision avec les autres aventuriers
        int futurePositionX = adventurer.getPositionX() - 1;
        return futurePositionX >= 0 && game.getMap()[futurePositionX][adventurer.getPositionY()].getTerrainType() >= 0;
    }

    private boolean canMoveEast(Adventurer adventurer, Game game) {
        // TODO Ajouter les collision avec les autres aventuriers
        int futurePositionX = adventurer.getPositionX() + 1;
        return futurePositionX < game.getWidth() && game.getMap()[futurePositionX][adventurer.getPositionY()].getTerrainType() >= 0;
    }

    private boolean canMoveSouth(Adventurer adventurer, Game game) {
        // TODO Ajouter les collision avec les autres aventuriers
        int futurePositionY = adventurer.getPositionY() + 1;
        return futurePositionY < game.getHeight() && game.getMap()[adventurer.getPositionX()][futurePositionY].getTerrainType() >= 0;
    }

    private boolean canMoveNorth(Adventurer adventurer, Game game) {
        // TODO Ajouter les collision avec les autres aventuriers
        int futurePositionY = adventurer.getPositionY() - 1;
        return futurePositionY >= 0 && game.getMap()[adventurer.getPositionX()][futurePositionY].getTerrainType() >= 0;
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
        adventurer.setPositionY(adventurer.getPositionY() - 1);
    }
}

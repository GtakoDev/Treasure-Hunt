package com.gtako.dev.controller;

import com.gtako.dev.model.Adventurer;
import com.gtako.dev.model.Game;
import com.gtako.dev.model.Orientation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AdventurerService {

    public static final String ERROR_ADV_ORIENTATION = "La valeur de l'orientation de l'aventurier est invalide";
    private static final Logger logger = LogManager.getLogger(AdventurerService.class);

    public void turnRight(Adventurer adventurer) {
        switch (adventurer.getOrientation()) {
            case NORTH -> adventurer.setOrientation(Orientation.EAST);
            case EAST -> adventurer.setOrientation(Orientation.SOUTH);
            case SOUTH -> adventurer.setOrientation(Orientation.WEST);
            case WEST -> adventurer.setOrientation(Orientation.NORTH);
            default -> throw new RuntimeException(ERROR_ADV_ORIENTATION);
        }
    }

    public void turnLeft(Adventurer adventurer) {
        switch (adventurer.getOrientation()) {
            case NORTH -> adventurer.setOrientation(Orientation.WEST);
            case EAST -> adventurer.setOrientation(Orientation.NORTH);
            case SOUTH -> adventurer.setOrientation(Orientation.EAST);
            case WEST -> adventurer.setOrientation(Orientation.SOUTH);
            default -> throw new RuntimeException(ERROR_ADV_ORIENTATION);
        }
    }

    public void moveForward(Adventurer adventurer, Game game) {
        switch (adventurer.getOrientation()) {
            case NORTH -> evaluateMovementNorth(adventurer, game);
            case EAST -> evaluateMovementEast(adventurer, game);
            case SOUTH -> evaluateMovementSouth(adventurer, game);
            case WEST -> evaluateMovementWest(adventurer, game);
            default -> throw new RuntimeException(ERROR_ADV_ORIENTATION);
        }

        game.getMap()[adventurer.getPositionX()][adventurer.getPositionY()].setAdventurer(adventurer);

        if (game.getMap()[adventurer.getPositionX()][adventurer.getPositionY()].getTerrainType() > 0) {
            int treasureCount = game.getMap()[adventurer.getPositionX()][adventurer.getPositionY()].getTerrainType();
            game.getMap()[adventurer.getPositionX()][adventurer.getPositionY()].setTerrainType(treasureCount - 1);
            adventurer.setNumberOfCollectedTreasures(adventurer.getNumberOfCollectedTreasures() + 1);
        }
    }

    private void evaluateMovementWest(Adventurer adventurer, Game game) {
        if (canMoveWest(adventurer, game)) {
            game.getMap()[adventurer.getPositionX()][adventurer.getPositionY()].setAdventurer(null);
            decrementX(adventurer);
        } else {
            logger.error(String.format("Impossible de déplacer l'aventurier %s a l'ouest", adventurer.getName()));
        }
    }

    private void evaluateMovementSouth(Adventurer adventurer, Game game) {
        if (canMoveSouth(adventurer, game)) {
            game.getMap()[adventurer.getPositionX()][adventurer.getPositionY()].setAdventurer(null);
            incrementY(adventurer);
        } else {
            logger.error(String.format("Impossible de déplacer l'aventurier %s au sud", adventurer.getName()));
        }
    }

    private void evaluateMovementEast(Adventurer adventurer, Game game) {
        if (canMoveEast(adventurer, game)) {
            game.getMap()[adventurer.getPositionX()][adventurer.getPositionY()].setAdventurer(null);
            incrementX(adventurer);
        } else {
            logger.error(String.format("Impossible de déplacer l'aventurier %s a l'est", adventurer.getName()));
        }
    }

    private void evaluateMovementNorth(Adventurer adventurer, Game game) {
        if (canMoveNorth(adventurer, game)) {
            game.getMap()[adventurer.getPositionX()][adventurer.getPositionY()].setAdventurer(null);
            decrementY(adventurer);
        } else {
            logger.error(String.format("Impossible de déplacer l'aventurier %s au nord", adventurer.getName()));
        }
    }

    private boolean canMoveWest(Adventurer adventurer, Game game) {
        int futurePositionX = adventurer.getPositionX() - 1;

        boolean collideWithAnotherAdventurer = game.getAdventurers().keySet().stream()
                .filter(adventurer1 -> adventurer1 != adventurer)
                .anyMatch(adventurer1 -> adventurer1.getPositionX() == futurePositionX && adventurer1.getPositionY() == adventurer.getPositionY());

        return futurePositionX >= 0 && game.getMap()[futurePositionX][adventurer.getPositionY()].getTerrainType() >= 0 && !collideWithAnotherAdventurer;
    }

    private boolean canMoveEast(Adventurer adventurer, Game game) {
        int futurePositionX = adventurer.getPositionX() + 1;

        boolean collideWithAnotherAdventurer = game.getAdventurers().keySet().stream()
                .filter(adventurer1 -> adventurer1 != adventurer)
                .anyMatch(adventurer1 -> adventurer1.getPositionX() == futurePositionX && adventurer1.getPositionY() == adventurer.getPositionY());

        return futurePositionX < game.getWidth() && game.getMap()[futurePositionX][adventurer.getPositionY()].getTerrainType() >= 0 && !collideWithAnotherAdventurer;
    }

    private boolean canMoveSouth(Adventurer adventurer, Game game) {
        int futurePositionY = adventurer.getPositionY() + 1;

        boolean collideWithAnotherAdventurer = game.getAdventurers().keySet().stream()
                .filter(adventurer1 -> adventurer1 != adventurer)
                .anyMatch(adventurer1 -> adventurer1.getPositionY() == futurePositionY && adventurer1.getPositionX() == adventurer.getPositionX());

        return futurePositionY < game.getHeight() && game.getMap()[adventurer.getPositionX()][futurePositionY].getTerrainType() >= 0 && !collideWithAnotherAdventurer;
    }

    private boolean canMoveNorth(Adventurer adventurer, Game game) {
        int futurePositionY = adventurer.getPositionY() - 1;

        boolean collideWithAnotherAdventurer = game.getAdventurers().keySet().stream()
                .filter(adventurer1 -> adventurer1 != adventurer)
                .anyMatch(adventurer1 -> adventurer1.getPositionY() == futurePositionY && adventurer1.getPositionX() == adventurer.getPositionX());

        return futurePositionY >= 0 && game.getMap()[adventurer.getPositionX()][futurePositionY].getTerrainType() >= 0 && ! collideWithAnotherAdventurer;
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

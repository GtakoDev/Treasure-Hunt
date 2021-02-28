package com.gtako.dev.controller;

import com.gtako.dev.model.Adventurer;
import com.gtako.dev.model.Box;

import java.util.List;
import java.util.Map;

public class MapService {

    public void setMoutain(Box[][] box, int positionX, int positionY) {
        box[positionX][positionY].setTerrainType(-1);
    }

    public void setTreasure(Box[][] box, int positionX, int positionY, int treasureCount) {
        if ( box[positionX][positionY].getTerrainType() < 0 ) {
            throw new RuntimeException("Can't put a treasure on a montain");
        }

        box[positionX][positionY].setTerrainType(  box[positionX][positionY].getTerrainType() + treasureCount);
    }

    /*public void addAdventurer(List<Adventurer> adventurers, Adventurer adventurer) {
        adventurers.add(adventurer);
    }*/

    public void addAdventurer(Map<Adventurer, Boolean> adventurers, Adventurer adventurer) {
        adventurers.put(adventurer, !adventurer.getMovements().isEmpty());
    }
}

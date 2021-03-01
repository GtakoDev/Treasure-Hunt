package com.gtako.dev.controller;

import com.gtako.dev.model.Adventurer;
import com.gtako.dev.model.Box;

import java.util.Map;

/**
 * Classe qui définit des méthodes concernant la carte
 *
 * @author Guillaume T.
 */
public class MapService {

    /**
     * Place une montagne sur la carte en fonction de ses coordonées
     *
     * @param box       la carte
     * @param positionX position sur l'axe horizontal
     * @param positionY position sur l'axe vertical
     */
    public void setMoutain(Box[][] box, int positionX, int positionY) {
        box[positionX][positionY].setTerrainType(-1);
    }

    /**
     * Place des trésor sur une case de la carte en fonction de leurs coordonées
     *
     * @param box           la carte
     * @param positionX     position sur l'axe horizontal
     * @param positionY     position sur l'axe vertical
     * @param treasureCount nombre de trésors à placer sur la case
     */
    public void setTreasure(Box[][] box, int positionX, int positionY, int treasureCount) {
        if (box[positionX][positionY].getTerrainType() < 0) {
            throw new RuntimeException("Impossible de place un trésor sur une montagne");
        }
        box[positionX][positionY].setTerrainType(box[positionX][positionY].getTerrainType() + treasureCount);
    }

    /**
     * Ajout d'un aventurier dans la la liste des aventuriers du jeu
     *
     * @param adventurers liste des aventuriers du jeux
     * @param adventurer  l'aventurier a ajouter
     */
    public void addAdventurer(Map<Adventurer, Boolean> adventurers, Adventurer adventurer) {
        adventurers.put(adventurer, !adventurer.getMovements().isEmpty());
    }
}

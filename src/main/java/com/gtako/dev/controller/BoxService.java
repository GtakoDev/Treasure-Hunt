package com.gtako.dev.controller;

import com.gtako.dev.model.Box;

/**
 * Classe qui définit les méthodes concernant les case de la carte
 *
 * @author Guillaume T.
 */
public class BoxService {

    /**
     * vérifie qu'une case de la carte est une montagne
     *
     * @param box une case de la carte
     * @return vrai si la case est une montagne, faux sinon
     */
    public boolean isMoutain(Box box) {
        return (-1 == box.getTerrainType());
    }

    /**
     * vérifie qu'une case de la carte est un trésor
     *
     * @param box une case de la carte
     * @return vrai si la case est un trésor, faux sinon
     */
    public boolean isTreasure(Box box) {
        return (box.getTerrainType() > 0);
    }

    /**
     * Retourne le nombre de trésors sur une case de la carte
     *
     * @param box une case de la carte
     * @return le nombre de trésors
     */
    public int getNumberOfTreasure(Box box) {
        if (isTreasure(box)) {
            return box.getTerrainType();
        }
        return 0;
    }
}

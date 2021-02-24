package com.gtako.dev.controller;

import com.gtako.dev.model.Box;

public class BoxService {

    public boolean isMoutain(Box box) {
        return ( -1 == box.getTerrainType() );
    }

    public boolean isPlain(Box box) {
        return ( 0 == box.getTerrainType() );
    }

    public boolean isTreasure(Box box) {
        return ( box.getTerrainType() > 0 );
    }

    public int getNumberOfTreasure( Box box ) {
        if ( isTreasure(box) ) {
            return box.getTerrainType();
        }
        return 0;
    }

    public boolean pickUpTreasure( Box box ) {
        if ( isTreasure(box) ) {
            box.setTerrainType(box.getTerrainType() - 1);
            return true;
        }

        return false;
    }

}

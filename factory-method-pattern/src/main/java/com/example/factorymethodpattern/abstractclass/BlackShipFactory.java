package com.example.factorymethodpattern.abstractclass;

import com.example.factorymethodpattern.after.BlackShip;
import com.example.factorymethodpattern.after.Ship;
import com.example.factorymethodpattern.after.ShipFactory;

public class BlackShipFactory extends DefaultShipFactory {

    @Override
    public Ship createShip() {
        return new BlackShip();
    }

}

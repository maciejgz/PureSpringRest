package pl.mg.doorsgame.model;

import java.util.Random;

/**
 * 
 * @author Maciej Gzik
 *
 */
public enum Prize {

    CAR("Car"), TOMATO("Tomato"), BIKE("Bike");

    private String displayName;

    private Prize(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Prize randomPrize() {
        int pick = new Random().nextInt(Prize.values().length);
        return Prize.values()[pick];
    }

}

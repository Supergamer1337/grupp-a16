package labb1.del2.gameobjects.vehicles.cars;

import javafx.scene.paint.Color;
import labb1.del2.gameobjects.vehicles.vehicleparts.Engine;
import labb1.del2.helpers.Vector2D;

public final class Volvo240 extends Car {

    private static double trimFactor = 1.25;

    public Volvo240(Vector2D pos) {
        super(pos.getX(), pos.getY(), 95.6, 50.0, Color.BLACK, "Volvo240", 4, new Engine(100));
    }

    public Volvo240() {
        this(new Vector2D());
    }

    @Override
    public String[] getHudInfo() {
        String[] specHud = new String[] {
                "Trim Factor: " + trimFactor,
                "Nr of doors: " + getNrOfDoors()
        };
        return concatenateStrArr(super.getHudInfo(), specHud);
    }

    public double speedFactor() {
        return getEngine().getPower() * 0.01 * trimFactor;
    }
}

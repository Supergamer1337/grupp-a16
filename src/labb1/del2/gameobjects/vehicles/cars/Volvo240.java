package labb1.del2.gameobjects.vehicles.cars;

import javafx.scene.paint.Color;
import labb1.del2.gameobjects.vehicles.vehicleparts.Engine;
import labb1.del2.helpers.Vector2D;

public class Volvo240 extends Car {

    private static double trimFactor = 1.25;

    public Volvo240(Vector2D pos) {
        super(pos.getX(), pos.getY(), 95.6, 50.0, Color.RED, "Volvo240", 4, new Engine(100));
    }

    public Volvo240() {
        this(new Vector2D());
    }

    @Override
    public String[] getHudInfo() {
        return new String[] {
                "Model: " + getModelName(),
                "Current speed: " + getSpeed() + "km/h",
                "Trim Factor: " + trimFactor,
                "Nr of doors: " + getNrOfDoors()
        };
    }

    public double speedFactor() {
        return getEngine().getPower() * 0.01 * trimFactor;
    }
}

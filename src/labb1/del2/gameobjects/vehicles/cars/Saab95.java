package labb1.del2.gameobjects.vehicles.cars;

import javafx.scene.paint.Color;
import labb1.del2.gameobjects.vehicles.vehicleparts.Engine;
import labb1.del2.helpers.Vector2D;

public final class Saab95 extends Car {

    private boolean turboOn;

    public Saab95(Vector2D pos) {
        super(pos.getX(), pos.getY(), 100.20, 50.0, Color.RED, "Saab95", 2, new Engine(125));
        turboOn = false;
    }

    public Saab95() {
        this(new Vector2D());
    }

    @Override
    public String[] getHudInfo() {
        return new String[] {
                "Model: " + getModelName(),
                "Engine On: " + getEngine().isTurnedOn(),
                "Current speed: " + getSpeed() + "km/h",
                "Turbo active: " + turboOn,
                "Nr of doors: " + getNrOfDoors()
        };
    }

    public double speedFactor() {
        double turbo = 1;
        if (turboOn) turbo = 1.3;
        return getEngine().getPower() * 0.01 * turbo;
    }

    public void toggleTurbo() {
        turboOn = !turboOn;
    }

}

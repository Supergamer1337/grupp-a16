package labb1.del2.gameobjects.vehicles.cars;

import javafx.scene.paint.Color;
import labb1.del2.gameobjects.vehicles.vehicleparts.Engine;
import labb1.del2.gameobjects.vehicles.vehicleparts.Flatbed;
import labb1.del2.helpers.Vector2D;


public class Scania extends Car {

    private Flatbed flatbed;

    public Scania(Vector2D pos) {
        super(pos.getX(), pos.getY(), 118.66, 75, Color.BLUE, "Scania", 2, new Engine(90));
        flatbed = new Flatbed();
    }

    @Override
    public String[] getHudInfo() {
        return new String[] {
                "Model: " + getModelName(),
                "Engine On: " + getEngine().isTurnedOn(),
                "Current speed: " + getSpeed() + "km/h",
                "Flatbed angle: " + flatbed.getFlatbedAngle(),
                "Nr of doors: " + getNrOfDoors()
        };
    }

    public double speedFactor() {
        if (flatbed.isLowered()) {
            return getEngine().getPower() * 0.01;
        }
        return 0;
    }
}

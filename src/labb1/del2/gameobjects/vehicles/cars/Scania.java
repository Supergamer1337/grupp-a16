package labb1.del2.gameobjects.vehicles.cars;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import labb1.del2.gameobjects.vehicles.vehicleparts.AngledFlatbed;
import labb1.del2.gameobjects.vehicles.vehicleparts.Engine;

import labb1.del2.helpers.Vector2D;

public final class Scania extends Car {

    private AngledFlatbed flatbed;

    public Scania(Vector2D pos) {
        super(pos.getX(), pos.getY(), 118.66, 75, Color.BLUE, "Scania", 2, new Engine(90));
        flatbed = new AngledFlatbed();
    }

    @Override
    public double speedFactor() {
        if (flatbed.isLowered()) {
            return getEngine().getPower() * 0.01;
        }
        return 0;
    }

    @Override
    public void handlePressedSpecialKeys(KeyCode key) {
        super.handlePressedSpecialKeys(key);
        if (key == KeyCode.R && getSpeed() == 0) {
            flatbed.raiseFlatbed();
        } else if (key == KeyCode.F) {
            flatbed.lowerFlatbed();
        }
    }

    @Override
    public String[] getHudInfo() {
        return new String[] {
                "Model: " + getModelName(),
                "Engine On: " + getEngine().isTurnedOn(),
                "Speed: " + getSpeed() + "km/h",
                "Flatbed angle: " + flatbed.getFlatbedAngle(),
                "Nr of doors: " + getNrOfDoors()
        };
    }



}

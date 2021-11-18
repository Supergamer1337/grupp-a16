package labb1.del2.gameobjects.vehicles.cars;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import labb1.del2.gameobjects.vehicles.vehicleparts.CarTransporter;
import labb1.del2.gameobjects.vehicles.vehicleparts.Engine;
import labb1.del2.gameobjects.vehicles.vehicleparts.SimpleFlatbed;
import labb1.del2.helpers.Vector2D;

import java.util.Arrays;

public final class TowingTruck extends Car {
    private static final int BASE_CAR_LOAD_LIMIT = 2;
    private CarTransporter ct;
    private SimpleFlatbed flatbed;
    private double pickupRadius;

    public TowingTruck(Vector2D pos) {
        super(pos.getX(), pos.getY(), 323, 75, Color.TURQUOISE, "Towing Truck", 2, new Engine(80));
        ct = new CarTransporter(BASE_CAR_LOAD_LIMIT);
        flatbed = new SimpleFlatbed();
        pickupRadius = 20;
    }

    @Override
    public double speedFactor() {
        if (flatbed.isLowered()) {
            return 0.01 * getEngine().getPower();
        }
        return 0;
    }

    @Override
    public String[] getHudInfo() {
        return new String[] {
                "Model: " + getModelName(),
                "Engine On: " + getEngine().isTurnedOn(),
                "Speed: " + getSpeed() + "km/h",
                "Nr of doors: " + getNrOfDoors(),
                "Ramp lowered: " + flatbed.isLowered(),
                "Loaded cars: " + Arrays.toString(ct.getCarNames())
        };
    }

    public boolean loadCar(Car car) {
        if (flatbed.isLowered() && isWithinRange(car.getPosV())) {
            ct.loadCar(car);
            return true;
        }
        return false;
    }

    private boolean isWithinRange(Vector2D pos) {
        if (pos.getX() - getPosX() < pickupRadius && pos.getY() - getPosY() < pickupRadius) {
            return true;
        }
        return false;
    }

    public void raiseRamp() {
        flatbed.raiseFlatbed();
    }

    public void lowerRamp() {
        if (getSpeed() == 0) {
            flatbed.lowerFlatbed();
        }
    }
}

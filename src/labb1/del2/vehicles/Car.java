package labb1.del2.vehicles;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import labb1.del2.controllers.CarController;
import labb1.del2.controllers.IControllable;
import labb1.del2.vehicleparts.Engine;
import labb1.del2.vehicleparts.IEngine;
import labb1.del2.vehicleparts.VehicleBody;

public abstract class Car extends Vehicle implements IEngine {

    private final int nrOfDoors;

    /**
     * Creates a new Car object
     * @param rect Rectangle representing the car
     * @param color Color of the car
     * @param modelName Name of the car model
     * @param nrOfDoors The amount of doors the car has
     */
    Car(Rectangle rect, Color color, String modelName, int nrOfDoors) {
        super(new VehicleBody(rect, color), modelName);
        this.nrOfDoors = nrOfDoors;
    }

    @Override
    public final void incrementSpeed(double amount) {
        if(!isTurnedOn()) {
            throw new IllegalStateException("Can't increment speed unless engine is turned on");
        }
        setSpeed(Math.min(getSpeed() + speedFactor() * amount, getPower()));
    }

    @Override
    public final void decrementSpeed(double amount) {
        setSpeed(Math.max(getSpeed() - speedFactor() * amount, 0));
    }

    @Override
    public String[] getHudInfo() {
        return new String[] {
                "Model: " + getModelName(),
                "Engine On: " + isTurnedOn(),
                String.format("Speed: %.0fkm/h", getSpeed()),
                "Nr of doors: " + getNrOfDoors()
        };
    }

    public final int getNrOfDoors() {
        return nrOfDoors;
    }

    @Override
    IControllable getController() {
        return new CarController(this);
    }
}

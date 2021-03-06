package labb1.del2.vehicles;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import labb1.del2.controllers.CarController;
import labb1.del2.controllers.IControllable;
import labb1.del2.utils.StringHelper;
import labb1.del2.vehicleparts.VehicleBody;

public abstract class Car extends Vehicle {

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

    public abstract double getPower();

    public abstract double speedFactor();

    public abstract boolean isTurnedOn();

    public abstract void turnOn();

    public abstract void turnOff();

    @Override
    public final void decrementSpeed(double amount) {
        setSpeed(Math.max(getSpeed() - speedFactor() * amount, 0));
    }

    @Override
    public final String[] getHudInfo() {
        String[] baseInfo = new String[] {
                "Model: " + getModelName(),
                "Engine On: " + isTurnedOn(),
                String.format("Speed: %.0fkm/h", getSpeed()),
                "Nr of doors: " + getNrOfDoors()
        };
        return StringHelper.concatenateStrArr(baseInfo, specificHudInfo());
    }

    protected abstract String[] specificHudInfo();

    /**
     * Gets the number of doors the car has.
     * @return The number of doors on the car.
     */
    public final int getNrOfDoors() {
        return nrOfDoors;
    }

    @Override
    IControllable getController() {
        return new CarController(this);
    }


    public abstract void toggleOn();
}

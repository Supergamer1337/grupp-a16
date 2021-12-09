package labb2.after.model.vehicles;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import labb1.del2.vehicleparts.VehicleBody;
import labb2.after.model.vehicleparts.CarAppearance;
import labb2.after.model.vehicleparts.CarPhysics;

public abstract class Car implements IVehicle {

    private final String modelName;
    private final CarPhysics physics;
    private final CarAppearance appearance;

    /**
     * Creates a new Car object
     * @param modelName Name of the car model
     */
    Car(String modelName, CarPhysics physics, CarAppearance appearance) {
        this.modelName = modelName;
        this.physics = physics;
        this.appearance = appearance;
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

    public void setColor(Color color) {
        appearance.setColor(color);
    }

    @Override
    public final void decrementSpeed(double amount) {
        setSpeed(Math.max(getSpeed() - speedFactor() * amount, 0));
    }

    public abstract void toggleOn();
}

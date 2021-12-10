package labb2.after.model.vehicles;

import javafx.scene.paint.Color;
import labb2.after.model.vehicleparts.CarAppearance;
import labb2.after.model.vehicleparts.CarPhysics;

public abstract class Car implements IVehicle {

    private static final double TURN_SPEED_CONSTANT = 100;

    private final String modelName;
    private final CarPhysics physics;
    private final CarAppearance appearance;
    private final double turnSpeed;

    /**
     * Creates a new Car object
     * @param modelName Name of the car model
     */
    Car(String modelName, CarPhysics physics, CarAppearance appearance) {
        this.modelName = modelName;
        this.physics = physics;
        this.appearance = appearance;
        turnSpeed = TURN_SPEED_CONSTANT * Math.PI / getWidth();
    }

    public final void incrementSpeed(double amount) {
        if(!isTurnedOn()) {
            throw new IllegalStateException("Can't increment speed unless engine is turned on");
        }
        physics.increaseSpeedWithLimit(speedFactor() * amount, getPower());
    }

    public final void decrementSpeed(double amount) {
        physics.reduceSpeedWithLimit(speedFactor() * amount, 0);
    }

    @Override
    public void move(double dTime) {
        physics.move(dTime);
    }

    @Override
    public void accelerate(double amount) {
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException("Accelerate amount must be between 0 and 1");
        }
        incrementSpeed(amount);
    }

    @Override
    public void decelerate(double amount) {
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException("Decelerate amount must be between 0 and 1");
        }
        decrementSpeed(amount);
    }

    public abstract double getPower();

    public abstract double speedFactor();

    public abstract boolean isTurnedOn();

    public abstract void turnOn();

    public abstract void turnOff();

    public abstract void toggleOn();

    public void setColor(Color color) {
        appearance.setColor(color);
    }

    @Override
    public String getModelName() {
        return modelName;
    }

    public double getWidth() {
        return physics.getWidth();
    }

    public double getHeight() {
        return physics.getHeight();
    }

    public double getWeight() {
        return physics.getWeight();
    }

    public double getSpeed() {
        return physics.getSpeed();
    }

    public double getRotation() {
        return physics.getDirection().asRadians();
    }

    public void setRotation(double newRotation) {
        physics.setRotation(newRotation);
    }

    @Override
    public void turnRight(double dTime) {
        physics.turn(dTime * turnSpeed);
    }

    @Override
    public void turnLeft(double dTime) {
        physics.turn(dTime * -turnSpeed);
    }

    @Override
    public double getX() {
        return physics.getX();
    }

    @Override
    public double getY() {
        return physics.getY();
    }
}

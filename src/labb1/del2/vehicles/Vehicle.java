package labb1.del2.vehicles;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import labb1.del2.IMovable;
import labb1.del2.controllers.IControllable;
import labb1.del2.vehicleparts.VehicleBody;

public abstract class Vehicle implements IMovable {

    private final String modelName;
    private final VehicleBody body;

    /**
     * Instantiates a new Vehicle.
     * @param body The body ("chassis") of the vehicle to instantiate.
     * @param modelName The model name of the vehicle to instantiate.
     */
    Vehicle(VehicleBody body, String modelName) {
        this.body = body;
        this.modelName = modelName;
    }

    /**
     * Accelerates the vehicle by the given amount.
     * @param amount The amount to accelerate by.
     * @throws IllegalArgumentException If the given amount is outside the range of 0-1.
     */
    public final void accelerate(double amount) throws IllegalArgumentException {
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException("Accelerate amount must be between 0 and 1");
        }
        incrementSpeed(amount);
    }

    /**
     * Decelerates the vehicle by the given amount.
     * @param amount The amount to decelerate by.
     * @throws IllegalArgumentException If the given amount is outside the range of 0-1.
     */
    public final void decelerate(double amount) throws IllegalArgumentException {
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException("Decelerate amount must be between 0 and 1");
        }
        decrementSpeed(amount);
    }

    @Override
    public final void move(double dTime) {
        body.move(dTime);
    }

    @Override
    public final void turnRight(double dTime) {
        body.turnRight(dTime);
    }

    @Override
    public final void turnLeft(double dTime) {
        body.turnLeft(dTime);
    }

    /**
     * Gets the info related to the current state of the vehicle.
     * @return A string array containing the current state of the vehicle.
     */
    public abstract String[] getHudInfo();

    /**
     * Increases the speed of the vehicle.
     * @param amount The amount to increase the speed by.
     */
    public abstract void incrementSpeed(double amount);

    /**
     * Decreases the speed of the vehicle.
     * @param amount The amount to decrease the speed by.
     */
    public abstract void decrementSpeed(double amount);

    /**
     * Gets the current color of the vehicle.
     * @return The color of the vehicle.
     */
    public final Color getColor() {
        return body.getColor();
    }

    /**
     * Changes the color of the vehicle.
     * @param color The new color of the vehicle.
     */
    public final void setColor(Color color) {
        body.setColor(color);
    }

    /**
     * Gets the model name of the vehicle.
     * @return The model name of the vehicle.
     */
    public final String getModelName() {
        return modelName;
    }

    /**
     * Gets the <a href="https://docs.oracle.com/javase/8/javafx/api/javafx/scene/shape/Rectangle.html">Rectangle</a> of the vehicle.
     * @return The rectangle of the vehicle.
     */
    public final Rectangle getRect() {
        return body.getRect();
    }

    /**
     * Gets the x-coordinate of the vehicle.
     * @return The x-coordinate of the vehicle.
     */
    public final double getPosX() {
        return body.getPosX();
    }

    /**
     * Gets the y-coordinate of the vehicle.
     * @return The y-coordinate of the vehicle.
     */
    public final double getPosY() {
        return body.getPosY();
    }

    /**
     * Gets the width of the vehicle.
     * @return The width of the vehicle.
     */
    public final double getWidth() {
        return body.getWidth();
    }

    /**
     * Gets the height of the vehicle.
     * @return The height of the vehicle.
     */
    public final double getHeight() {
        return body.getHeight();
    }

    /**
     * Gets the current speed of the vehicle.
     * @return The current speed of the vehicle.
     */
    public final double getSpeed() {
        return body.getSpeed();
    }

    /**
     * Sets the speed of the vehicle.
     * @param speed The speed to set the vehicle to.
     */
    public final void setSpeed(double speed) {
        body.setSpeed(speed);
    }

    /**
     * Gets the current turn speed of the vehicle.
     * @return The current turn speed of the vehicle.
     */
    public final double getTurnSpeed() {
        return body.getTurnSpeed();
    }

    /**
     * Gets the current rotation of the vehicle.
     * @return The current rotation of the vehicle.
     */
    public final double getRotation() {
        return body.getRotation();
    }

    /**
     * Sets the rotation of the vehicle.
     * @param rotation The rotation to set the vehicle to.
     */
    public final void setRotation(double rotation) {
        body.setRotation(rotation);
    }

    /**
     * Gets the controller of the vehicle.
     * @return The controller of the vehicle.
     */
    abstract IControllable getController();
}

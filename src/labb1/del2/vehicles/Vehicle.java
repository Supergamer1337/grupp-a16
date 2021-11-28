package labb1.del2.vehicles;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import labb1.del2.IMovable;
import labb1.del2.controllers.IControllable;
import labb1.del2.vehicleparts.VehicleBody;

public abstract class Vehicle implements IMovable {

    private final String modelName;
    private final VehicleBody body;

    Vehicle(VehicleBody body, String modelName) {
        this.body = body;
        this.modelName = modelName;
    }

    public final void accelerate(double amount) throws IllegalArgumentException {
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException("Accelerate amount must be between 0 and 1");
        }
        incrementSpeed(amount);
    }

    public final void decelerate(double amount) throws IllegalArgumentException {
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException("Decelerate amount must be between 0 and 1");
        }
        decrementSpeed(amount);
    }

    @Override
    public void move(double dTime) {
        body.move(dTime);
    }

    @Override
    public void turnRight(double dTime) {
        body.turnRight(dTime);
    }

    @Override
    public void turnLeft(double dTime) {
        body.turnLeft(dTime);
    }

    public abstract String[] getHudInfo();

    public abstract void incrementSpeed(double amount);
    public abstract void decrementSpeed(double amount);

    public final Color getColor() {
        return body.getColor();
    }
    public final String getModelName() {
        return modelName;
    }
    public final Rectangle getRect() {
        return body.getRect();
    }
    public final double getPosX() {
        return body.getPosX();
    }
    public final double getPosY() {
        return body.getPosY();
    }
    public final double getWidth() {
        return body.getWidth();
    }
    public final double getHeight() {
        return body.getHeight();
    }
    public final double getSpeed() {
        return body.getSpeed();
    }
    public final void setSpeed(double speed) {
        body.setSpeed(speed);
    }
    public final double getTurnSpeed() {
        return body.getTurnSpeed();
    }
    public final double getRotation() {
        return body.getRotation();
    }
    public final void setRotation(double rotation) {
        body.setRotation(rotation);
    }

    abstract IControllable getController();
}

package labb1.del2.gameobjects.vehicles;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import labb1.del2.View;
import labb1.del2.gameobjects.MovableObject;

public abstract class Vehicle extends MovableObject {
    private static final double TURN_SPEED_CONSTANT = 100;

    private double turnSpeed;
    private final String modelName;
    private Color color;

    protected Vehicle(double x, double y, double width, double height, double turnSpeed, Color color, String modelName) {
        super(x, y, width, height);
        this.turnSpeed = turnSpeed;
        this.modelName = modelName;
        this.color = color;
        getRect().setFill(color);
    }

    protected Vehicle(double x, double y, double width, double height, Color color, String modelName) {
        this(x, y, width, height, 0, color, modelName);
        turnSpeed = TURN_SPEED_CONSTANT * Math.PI / getWidth();
    }

    public abstract String[] getHudInfo();

    public final void accelerate(double amount) throws IllegalArgumentException {
        if (0 <= amount && amount <= 1) {
            incrementSpeed(amount);
        } else {
            throw new IllegalArgumentException("Accelerate amount must be between 0 and 1");
        }
    }

    public final void decelerate(double amount) throws IllegalArgumentException {
        if (0 <= amount && amount <= 1) {
            decrementSpeed(amount);
        } else {
            throw new IllegalArgumentException("Decelerate amount must be between 0 and 1");
        }
    }

    public abstract void incrementSpeed(double amount);

    public abstract void decrementSpeed(double amount);

    public final void turnRight(double dTime) {
        if(getSpeed() != 0) {
            setRotation(getRotation() + turnSpeed * dTime);
        }
    }
    public final void turnLeft(double dTime) {
        if(getSpeed() != 0) {
            setRotation(getRotation() - turnSpeed * dTime);
        }
    }

    public void handleReleasedSpecialKeys(KeyCode key) {}
    public void handlePressedSpecialKeys(KeyCode key) {
        switch(key) {
            case W -> accelerate(1);
            case S -> decelerate(1);
            case A -> turnLeft(View.getDeltaTime());
            case D -> turnRight(View.getDeltaTime());
        }
    }

    public final Color getColor() {
        return color;
    }
    public final String getModelName() {
        return modelName;
    }
}

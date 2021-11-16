package labb1del2.movables.cars;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import labb1del2.helpers.Vector2D;
import labb1del2.movables.Vehicle;
import javafx.scene.shape.Rectangle;

import java.awt.*;

public abstract class Car extends Vehicle {
    private static final int BASE_TURN_SPEED = 40;
    private final int nrOfDoors;

    protected Car(Vector2D pos, Point dimensions, double turnSpeed, double enginePower, Color color, String modelName, int weight, int nrOfDoors) {
        super(pos, dimensions, turnSpeed, enginePower, color, modelName, weight);
        this.nrOfDoors = nrOfDoors;
        stopEngine();
    }

    protected Car(Vector2D pos, Point dimensions, double enginePower, Color color, String modelName, int weight, int nrOfDoors) {
        this(pos, dimensions, BASE_TURN_SPEED, enginePower, color, modelName, weight, nrOfDoors);
    }

    protected Car(Point dimensions, double enginePower, Color color, String modelName, int weight, int nrOfDoors) {
        this(Vector2D.zero(), dimensions, enginePower, color, modelName, weight, nrOfDoors);
    }

    public int getNrOfDoors() { return nrOfDoors; }

    public void startEngine(){
        setSpeed(0.1);
    }
    public void stopEngine(){
        setSpeed(0);
    }

    public abstract double speedFactor();

    public void incrementSpeed(double amount){
        setSpeed(Math.min(getSpeed() + speedFactor() * amount, getEnginePower()));
    }

    public void decrementSpeed(double amount) {
        setSpeed(Math.max(getSpeed() - speedFactor() * amount, 0));
    }

    public void gas(double amount) {
        if (0 <= amount && amount <= 1) {
            incrementSpeed(amount);
        }
    }

    public void brake(double amount) {
        if (0 <= amount && amount <= 1) {
            decrementSpeed(amount);
        }
    }

    @Override
    public String toString() {
        return getModelName();
    }
}

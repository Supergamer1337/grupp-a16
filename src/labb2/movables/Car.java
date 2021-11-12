package labb2.movables;

import labb2.helpers.Vector2D;

import java.awt.*;

public abstract class Car extends Vehicle{
    private final int nrOfDoors;

    Car(Vector2D pos, Vector2D direction, double speed, double enginePower, Color color, String modelName, int nrOfDoors) {
        super(pos, direction, speed, enginePower, color, modelName);
        this.nrOfDoors = nrOfDoors;
        stopEngine();
    }

    Car(Vector2D pos, double enginePower, Color color, String modelName, int nrOfDoors) {
        this(pos, Vector2D.zero(), 0, enginePower, color, modelName, nrOfDoors);
    }

    Car(double enginePower, Color color, String modelName, int nrOfDoors) {
        this(Vector2D.zero(), enginePower, color, modelName, nrOfDoors);
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
    public void move() {
        // TODO: Implement this
    }

    @Override
    public void turnLeft() {
        // TODO: Implement this
    }

    @Override
    public void turnRight() {
        // TODO: Implement this
    }
}

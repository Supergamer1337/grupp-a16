package labb1del2.movables.cars;

import javafx.scene.paint.Color;
import labb1del2.helpers.Vector2D;
import labb1del2.movables.Vehicle;

public abstract class Car extends Vehicle {
    private static final int BASE_TURN_SPEED = 5;
    private final int nrOfDoors;

    protected Car(Vector2D pos, double turnSpeed, double enginePower, Color color, String modelName, int weight, int nrOfDoors) {
        super(pos, turnSpeed, enginePower, color, modelName, weight);
        this.nrOfDoors = nrOfDoors;
        stopEngine();
    }

    protected Car(double turnSpeed, double enginePower, Color color, String modelName, int weight, int nrOfDoors) {
        this(Vector2D.zero(), turnSpeed, enginePower, color, modelName, weight, nrOfDoors);
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

    }

    @Override
    public void turnLeft() {
        rotate(-getTurnSpeed());
    }

    @Override
    public void turnRight() {
        rotate(getTurnSpeed());
    }
}

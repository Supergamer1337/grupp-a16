package labb1;

import java.awt.*;

public abstract class Car implements Movable {
    public enum Direction {
            UP,
            RIGHT,
            DOWN,
            LEFT
    }
    private Direction currentDirection;
    private int posX, posY;
    private final int nrDoors;
    private final double enginePower;
    private double currentSpeed;
    private Color color;
    private final String modelName;

    Car(int nrDoors, Color color, double enginePower, String modelName) {
        this.nrDoors = nrDoors;
        this.color = color;
        this.enginePower = enginePower;
        this.modelName = modelName;
        currentDirection = Direction.UP;
        stopEngine();
    }

    public int getNrDoors() { return nrDoors; }
    public double getEnginePower() { return enginePower; }
    public double getCurrentSpeed() { return currentSpeed; }
    public Color getColor() { return color; }
    public Direction getCurrentDirection() { return currentDirection; }
    public int getPosX() { return posX; }
    public int getPosY() { return posY; }

    public void setColor(Color clr) { color = clr; }
    public void startEngine(){
        currentSpeed = 0.1;
    }
    public void stopEngine(){
        currentSpeed = 0;
    }

    public abstract double speedFactor();

    public void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }

    public void decrementSpeed(double amount) {
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
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
        switch (currentDirection) {
            case UP -> posY -= currentSpeed;
            case RIGHT -> posX += currentSpeed;
            case LEFT -> posX -= currentSpeed;
            case DOWN -> posY += currentSpeed;
        }
    }

    @Override
    public void turnLeft() {
        int val = getCurrentDirection().ordinal() - 1;
        if (val < 0) {
            val = Direction.values().length - 1;
        }
        currentDirection = Direction.values()[val];
    }

    @Override
    public void turnRight() {
        int index = getCurrentDirection().ordinal() + 1;
        if (index >= Direction.values().length) {
            index = 0;
        }
        currentDirection = Direction.values()[index];
    }
}

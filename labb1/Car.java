import java.awt.*;

public abstract class Car implements Movable {
    enum Direction {
            UP,
            RIGHT,
            DOWN,
            LEFT
    }
    protected Direction currentDirection;
    protected int posX, posY, nrDoors;
    protected double enginePower, currentSpeed;
    protected Color color;
    protected String modelName;

    protected Car(int nrDoors, Color color, double enginePower, String modelName) {
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
            case UP -> posY += currentSpeed;
            case RIGHT -> posX += currentSpeed;
            case LEFT -> posX -= currentSpeed;
            case DOWN -> posY -= currentSpeed;
        }
    }

    @Override
    public void turnLeft() {
        switch (currentDirection) {
            case UP -> currentDirection = Direction.LEFT;
            case RIGHT -> currentDirection = Direction.UP;
            case DOWN -> currentDirection = Direction.RIGHT;
            case LEFT -> currentDirection = Direction.DOWN;
        }
    }

    @Override
    public void turnRight() {
        switch (currentDirection) {
            case UP -> currentDirection = Direction.RIGHT;
            case RIGHT -> currentDirection = Direction.DOWN;
            case DOWN -> currentDirection = Direction.LEFT;
            case LEFT -> currentDirection = Direction.UP;
        }
    }
}

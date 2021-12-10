package labb2.after.model.vehicleparts;

import labb2.after.model.utils.Vector2D;

public class CarPhysics {
    private Vector2D pos, direction;
    private final double width, height, weight;
    private double speed;

    public CarPhysics(CarPhysics physics) {
        this.pos = physics.getPos();
        this.direction = physics.getDirection();
        this.width = physics.getWidth();
        this.height = physics.getHeight();
        this.weight = physics.getWeight();
        this.speed = physics.getSpeed();
    }

    public CarPhysics(Vector2D pos, Vector2D direction, double width, double height, double weight) {
        this.pos = pos;
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.weight = weight;
        speed = 0;
    }

    public void move(double dTime) {
        pos = pos.add(direction.getX() * dTime, direction.getY() * dTime);
    
    }

    public void turn(double degrees) {
        if(speed > 0) {
            direction = direction.add(Vector2D.angleToVector2D(degrees));
        }
    }

    public void setRotation(double degrees) {
        direction = Vector2D.angleToVector2D(degrees);
    }

    public void increaseSpeedWithLimit(double amount, double upperLimit) {
        speed = Math.min(speed + amount, upperLimit);
    }

    public void increaseSpeed(double amount) {
        speed += amount;
    }

    public void reduceSpeedWithLimit(double amount, double lowerLimit) {
        speed = Math.max(speed - amount, lowerLimit);
    }

    public void reduceSpeed(double amount) {
        speed -= amount;
    }

    public Vector2D getPos() {
        return pos.copy();
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public double getSpeed() {
        return speed;
    }

    public Vector2D getDirection() {
        return direction.copy();
    }
}

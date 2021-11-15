package labb1del2.movables;

import labb1del2.helpers.Vector2D;

public abstract class MovableObject implements Movable {
    private final static double BASE_SPEED = 0, BASE_TURN_SPEED = 5;

    private Vector2D pos, direction;
    private double speed, turnSpeed;

    public MovableObject(Vector2D pos, double speed, double turnSpeed) {
        this.pos = pos;
        this.direction = Vector2D.zero();
        this.speed = speed;
        this.turnSpeed = turnSpeed;
    }

    public MovableObject() {
        this(Vector2D.zero(), BASE_SPEED, BASE_TURN_SPEED);
    }

    public double getTurnSpeed() { return turnSpeed; }
    public Vector2D getPos() { return pos; }

    public Vector2D getDirection() { return direction; }
    public void setDirection(Vector2D direction) { this.direction = direction; }
    public void rotate(double angle) {
        direction = Vector2D.angleToVector2D(angle).add(direction);
    }

    public double getSpeed() {
        return speed;
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }
}

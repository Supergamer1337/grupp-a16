package labb2.movables;

import labb2.helpers.Vector2D;

public abstract class MovableObject implements Movable {
    private Vector2D pos, direction;
    private double speed, turnSpeed;

    public MovableObject(Vector2D pos, Vector2D direction, double speed) {
        this.pos = pos;
        this.direction = direction;
        this.speed = speed;
    }

    public MovableObject() {
        this(Vector2D.zero(), Vector2D.zero(), 0);
    }

    public double getTurnSpeed() { return turnSpeed; }
    double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

}

package labb1del2.movables;

import labb1del2.helpers.Vector2D;

public abstract class MovableObject {
    private Vector2D pos;
    private double rotation;
    private double speed, rotationSpeed;

    protected MovableObject(Vector2D pos, double speed, double rotationSpeed) {
        this.pos = pos;
        this.rotation = 0;
        this.speed = speed;
        this.rotationSpeed = rotationSpeed;
    }

    public double getRotationSpeed() { return rotationSpeed; }
    public Vector2D getPos() { return pos; }

    public double getRotation() { return rotation; }
    public void setRotation(double rotation) { this.rotation = rotation; }
    public void rotate(double angle) {
        rotation += angle;
        while(rotation > 360) {
            rotation -= 360;
        }
    }

    public double getSpeed() {
        return speed;
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void move(Vector2D amount) {
        pos = pos.add(amount);
    }
}

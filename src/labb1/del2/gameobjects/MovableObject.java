package labb1.del2.gameobjects;

import labb1.del2.helpers.Vector2D;

public abstract class MovableObject extends GameObject {

    private double speed;

    protected MovableObject(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public final void move(double dTime) {
        Vector2D direction = Vector2D.angleToVector2D(getRotation());
        getRect().setX(getPosX() + dTime * speed * direction.getX());
        getRect().setY(getPosY() + dTime * speed * direction.getY());
    }

    public final double getSpeed() {
        return speed;
    }
    public final void setSpeed(double speed) {
        this.speed = speed;
    }
}

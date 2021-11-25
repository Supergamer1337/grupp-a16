package labb1.del2.gameobjects;

import javafx.scene.shape.Rectangle;
import labb1.del2.helpers.Vector2D;

public abstract class MovableObject extends GameObject {

    private double speed;

    protected MovableObject(Rectangle rect) {
        super(rect, 0);
    }

    protected MovableObject(double x, double y, double width, double height) {
        this(new Rectangle(x, y, width, height));
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

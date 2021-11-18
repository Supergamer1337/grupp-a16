package labb1.del2.gameobjects;

import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

public abstract class GameObject {
    private static double BASE_ROTATION = 0;
    private Rectangle objectRect;
    private double rotation;

    protected GameObject(double x, double y, double width, double height, double rotation) {
        objectRect = new Rectangle(x, y, width, height);
        this.rotation = rotation;
        getRect().setRotate(getRotation() * 180 / Math.PI);
    }

    protected GameObject(double x, double y, double width, double height) {
        this(x, y, width, height, 0);
    }

    public final Rectangle getRect() {
        return objectRect;
    }
    public final double getPosX() {
        return objectRect.getX();
    }
    public final double getPosY() {
        return objectRect.getY();
    }
    public final double getWidth() {
        return objectRect.getWidth();
    }
    public final double getHeight() {
        return objectRect.getHeight();
    }
    public final double getRotation() {
        return rotation;
    }
    public final void setRotation(double rotation) {
        this.rotation = rotation;
        getRect().getTransforms().clear();
        getRect().setRotate(rotation * 180 / Math.PI);
    }
}

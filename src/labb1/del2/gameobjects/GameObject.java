package labb1.del2.gameobjects;

import javafx.scene.shape.Rectangle;

public abstract class GameObject {
    private static final double BASE_ROTATION = 0;
    private Rectangle objectRect;
    private double rotation;

    protected GameObject(Rectangle rect, double rotation) {
        this.objectRect = rect;
        this.rotation = rotation;
        getRect().setRotate(getRotation() * 180 / Math.PI);
    }

    protected GameObject(double x, double y, double width, double height, double rotation) {
        this(new Rectangle(x, y, width, height), rotation);
    }

    protected GameObject(double x, double y, double width, double height) {
        this(x, y, width, height, BASE_ROTATION);
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

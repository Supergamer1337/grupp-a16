package labb1.del2.vehicleparts;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import labb1.del2.IMovable;
import labb1.del2.utils.Vector2D;

import java.util.Objects;

public class VehicleBody implements IMovable {
    private static final double TURN_SPEED_CONSTANT = 100, DEF_ROTATION = 0, DEF_SPEED = 0;

    private Rectangle bodyRect;
    private double speed, rotation;
    private final double turnSpeed;
    private Color color;

    public VehicleBody(Rectangle rect, double speed, double rotation, Color color) {
        bodyRect = rect;
        this.rotation = rotation;
        this.color = color;
        bodyRect.setFill(color);
        this.speed = speed;
        turnSpeed = TURN_SPEED_CONSTANT * Math.PI / bodyRect.getWidth();
    }

    public VehicleBody(Rectangle rect, Color color) {
        this(rect, DEF_SPEED, DEF_ROTATION, color);
    }

    /*
    Används inte
    public VehicleBody(double x, double y, double width, double height, Color color) {
        this(new Rectangle(x,y,width,height), color);
    }
    */

    @Override
    public void move(double dTime) {
        Vector2D direction = Vector2D.angleToVector2D(getRotation());
        getRect().setX(getPosX() + dTime * speed * direction.getX());
        getRect().setY(getPosY() + dTime * speed * direction.getY());
    }

    @Override
    public void turnRight(double dTime) {
        if(getSpeed() != 0) {
            setRotation(getRotation() + turnSpeed * dTime);
        }
    }

    @Override
    public void turnLeft(double dTime) {
        if(getSpeed() != 0) {
            setRotation(getRotation() - turnSpeed * dTime);
        }
    }

    /**
     * Gets the <a href="https://docs.oracle.com/javase/8/javafx/api/javafx/scene/shape/Rectangle.html">Rectangle</a> representing the body.
     * @return The <a href="https://docs.oracle.com/javase/8/javafx/api/javafx/scene/shape/Rectangle.html">Rectangle</a> representing the body.
     */
    public final Rectangle getRect() {
        return bodyRect;
    }

    /**
     * Gets the x-coordinate of the body.
     * @return The x-coordinate of the body.
     */
    public final double getPosX() {
        return bodyRect.getX();
    }

    /**
     * Gets the y-coordinate of the body.
     * @return The y-coordinate of the body.
     */
    public final double getPosY() {
        return bodyRect.getY();
    }

    /**
     * Gets the width of the body.
     * @return The width of the body.
     */
    public final double getWidth() {
        return bodyRect.getWidth();
    }

    /**
     * Gets the height of the body.
     * @return The height of the body.
     */
    public final double getHeight() {
        return bodyRect.getHeight();
    }

    /**
     * Gets the <a href="https://docs.oracle.com/javase/7/docs/api/java/awt/Color.html">Color</a> of the body.
     * @return The <a href="https://docs.oracle.com/javase/7/docs/api/java/awt/Color.html">Color</a> of the body.
     */
    public final Color getColor() {
        return color;
    }

    /**
     * Sets the color of the body.
     * @param color The new color of the body.
     */
    public final void setColor(Color color) {
        bodyRect.setFill(color);
        this.color = color;
    }

    /**
     * Gets the turn speed of the body.
     * @return The turn speed of the body.
     */
    public double getTurnSpeed() {
        return turnSpeed;
    }

    /**
     * Gets the rotation of the body.
     * @return The rotation of the body.
     */
    public final double getRotation() {
        return rotation;
    }

    /**
     * Sets the rotation of the body.
     * @param rotation The new rotation of the body.
     */
    public final void setRotation(double rotation) {
        this.rotation = rotation;
        getRect().getTransforms().clear();
        getRect().setRotate(rotation * 180 / Math.PI);
    }

    /**
     * Gets the speed of the body.
     * @return The speed of the body.
     */
    public final double getSpeed() {
        return speed;
    }

    /**
     * Sets the speed of the body.
     * @param speed The new speed of the body.
     */
    public final void setSpeed(double speed) {
        this.speed = speed;
    }
}

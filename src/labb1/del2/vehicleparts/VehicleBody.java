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

    public VehicleBody(double x, double y, double width, double height, Color color) {
        this(new Rectangle(x,y,width,height), color);
    }

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

    public final Rectangle getRect() {
        return bodyRect;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bodyRect);
    }

    public final double getPosX() {
        return bodyRect.getX();
    }
    public final double getPosY() {
        return bodyRect.getY();
    }
    public final double getWidth() {
        return bodyRect.getWidth();
    }
    public final double getHeight() {
        return bodyRect.getHeight();
    }
    public final Color getColor() {
        return color;
    }
    public double getTurnSpeed() {
        return turnSpeed;
    }

    public final double getRotation() {
        return rotation;
    }
    public final void setRotation(double rotation) {
        this.rotation = rotation;
        getRect().getTransforms().clear();
        getRect().setRotate(rotation * 180 / Math.PI);
    }

    public final double getSpeed() {
        return speed;
    }
    public final void setSpeed(double speed) {
        this.speed = speed;
    }
}

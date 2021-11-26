package labb1.del2.vehicles;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import labb1.del2.IMovable;
import labb1.del2.controllers.IControllable;
import labb1.del2.utils.Vector2D;

public abstract class Vehicle implements IMovable {
    private static final double TURN_SPEED_CONSTANT = 100;
    private static final double BASE_ROTATION = 0;
    private Rectangle objectRect;
    private double rotation;
    private double turnSpeed;
    private final String modelName;
    private Color color;
    private double speed;


    protected Vehicle(Rectangle rect, double rotation, double turnSpeed, Color color, String modelName) {
        this.objectRect = rect;
        this.rotation = rotation;
        getRect().setRotate(getRotation() * 180 / Math.PI);
        this.turnSpeed = turnSpeed;
        this.modelName = modelName;
        this.color = color;
        getRect().setFill(color);
    }

    protected Vehicle(Rectangle rect, Color color, String modelName) {
        this(rect, 0,0, color, modelName);
        turnSpeed = TURN_SPEED_CONSTANT * Math.PI / getWidth();
    }

    protected Vehicle(double x, double y, double width, double height, Color color, String modelName) {
        this(new Rectangle(x, y, width, height), color, modelName);
    }

    public abstract String[] getHudInfo();

    public final void accelerate(double amount) throws IllegalArgumentException {
        if (0 <= amount && amount <= 1) {
            incrementSpeed(amount);
        } else {
            throw new IllegalArgumentException("Accelerate amount must be between 0 and 1");
        }
    }

    public final void decelerate(double amount) throws IllegalArgumentException {
        if (0 <= amount && amount <= 1) {
            decrementSpeed(amount);
        } else {
            throw new IllegalArgumentException("Decelerate amount must be between 0 and 1");
        }
    }

    public abstract void incrementSpeed(double amount);

    public abstract void decrementSpeed(double amount);

    public final void move(double dTime) {
        Vector2D direction = Vector2D.angleToVector2D(getRotation());
        getRect().setX(getPosX() + dTime * speed * direction.getX());
        getRect().setY(getPosY() + dTime * speed * direction.getY());
    }

    public final void turnRight(double dTime) {
        if(getSpeed() != 0) {
            setRotation(getRotation() + turnSpeed * dTime);
        }
    }
    public final void turnLeft(double dTime) {
        if(getSpeed() != 0) {
            setRotation(getRotation() - turnSpeed * dTime);
        }
    }



    public final double getSpeed() {
        return speed;
    }
    public final void setSpeed(double speed) {
        this.speed = speed;
    }

    public final Color getColor() {
        return color;
    }
    public final String getModelName() {
        return modelName;
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

    public abstract IControllable getController();
}

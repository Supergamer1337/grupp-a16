package labb1del2.movables;

import javafx.scene.paint.Color;
import labb1del2.helpers.Vector2D;

import java.awt.*;

public abstract class Vehicle extends GameObject {
    private double enginePower;
    private Color color;
    private String modelName;
    private int weight;

    protected Vehicle(Vector2D pos, Point dimensions, double turnSpeed, double enginePower, Color color, String modelName, int weight) {
        super(pos, turnSpeed, dimensions);
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        this.weight = weight;
    }

    protected Vehicle(Point dimensions, double turnSpeed, double enginePower, Color color, String modelName, int weight) {
        this(Vector2D.zero(), dimensions, turnSpeed, enginePower, color, modelName, weight);
    }

    public double getEnginePower() {
        return enginePower;
    }

    public Color getColor() {
        return color;
    }

    public String getModelName() {
        return modelName;
    }

    public void drive(double dTime) {
        double movement = getSpeed() * dTime;
        Vector2D direction = Vector2D.angleToVector2D(getRotation());
        Vector2D amount = new Vector2D(direction.getX() * movement, direction.getY() * movement);
        move(amount);
    }

    @Override
    public void turnLeft(double dTime) {
        rotate(-getRotationSpeed() * dTime);
    }

    @Override
    public void turnRight(double dTime) {
        rotate(getRotationSpeed() * dTime);
    }
}

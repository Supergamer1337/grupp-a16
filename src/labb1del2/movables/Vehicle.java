package labb1del2.movables;

import javafx.scene.paint.Color;
import labb1del2.helpers.Vector2D;

public abstract class Vehicle extends MovableObject {
    private static final double BASE_SPEED = 0;

    private double enginePower;
    private Color color;
    private String modelName;
    private int weight;

    protected Vehicle(Vector2D pos, double turnSpeed, double enginePower, Color color, String modelName, int weight) {
        super(pos, BASE_SPEED, turnSpeed);
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        this.weight = weight;
    }

    protected Vehicle(double turnSpeed, double enginePower, Color color, String modelName, int weight) {
        this(Vector2D.zero(), turnSpeed, enginePower, color, modelName, weight);
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
}

package labb2.movables;

import labb2.helpers.Vector2D;

import java.awt.*;

public abstract class Vehicle extends MovableObject {
    private double enginePower;
    private Color color;
    private String modelName;

    public Vehicle(Vector2D pos, Vector2D direction, double speed, double enginePower, Color color, String modelName) {
        super(pos, direction, speed);
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
    }

    public Vehicle(double speed, double enginePower, Color color, String modelName) {
        this(Vector2D.zero(), Vector2D.zero(), speed, enginePower, color, modelName);
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

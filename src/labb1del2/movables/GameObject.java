package labb1del2.movables;

import javafx.scene.canvas.GraphicsContext;
import labb1del2.helpers.Vector2D;

import java.awt.*;

public abstract class GameObject extends MovableObject {
    private final static double BASE_SPEED = 0;
    private Point dimensions;

    protected GameObject(Vector2D pos, double speed, double rotationSpeed, Point dimensions) {
        super(pos, speed, rotationSpeed);
        this.dimensions = dimensions;
    }

    protected GameObject(Vector2D pos, double rotationSpeed, Point dimensions) {
        this(pos, BASE_SPEED, rotationSpeed, dimensions);
    }

    //public abstract void update(double dTime);
    //public abstract void draw(GraphicsContext gc);

    public abstract void turnLeft(double dTime);
    public abstract void turnRight(double dTime);

    public Point getDimensions() {
        return dimensions;
    }
}

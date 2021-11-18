package labb1del2.movables.cars;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import labb1del2.helpers.Vector2D;

import java.awt.*;

public class Volvo240 extends Car {

    private final static double trimFactor = 1.25;

    public Volvo240(Vector2D pos) {
        super(pos, new Point(100, 50), 100.0, Color.BLACK, "Volvo240", 1234, 4);
    }

    public Volvo240() {
        this(Vector2D.zero());
    }

    public double speedFactor() {
        return getEnginePower() * 0.01 * trimFactor;
    }
}
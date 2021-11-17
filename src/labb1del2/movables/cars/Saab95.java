package labb1del2.movables.cars;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import labb1del2.helpers.Vector2D;

import java.awt.*;

public class Saab95 extends Car {

    private boolean turboOn;

    public Saab95(Vector2D pos) {
        super(pos, new Point(100, 50),125.0, Color.RED, "Saab95", 1569, 2);
        turboOn = false;
    }

    public Saab95() {
        this(Vector2D.zero());
    }

    public boolean isTurboOn() {
        return turboOn;
    }
    public void setTurboOn(){
	    turboOn = true;
    }
    public void setTurboOff(){
	    turboOn = false;
    }

    public double speedFactor() {
        double turbo = 1;
        if (turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }
}

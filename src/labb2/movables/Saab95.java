package labb2.movables;

import javafx.scene.image.Image;

import java.awt.*;

public class Saab95 extends Car {

    private Image imgCar;
    private boolean turboOn;

    public Saab95() {
        super(125.0, Color.RED, "Saab95", 2);
	    turboOn = false;
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

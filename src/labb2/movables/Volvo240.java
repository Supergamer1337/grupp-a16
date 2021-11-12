package labb2.movables;

import java.awt.*;

public class Volvo240 extends Car {

    private final static double trimFactor = 1.25;

    public Volvo240() {
        super(100.0, Color.BLACK, "Volvo240", 4);
    }

    public double speedFactor() {
        return getEnginePower() * 0.01 * trimFactor;
    }
}

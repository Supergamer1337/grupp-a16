package labb1.del2.vehicles;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import labb1.del2.utils.StringHelper;
import labb1.del2.vehicleparts.Engine;
import labb1.del2.vehicleparts.TrimmedEngine;

public final class Volvo240 extends Car {
    private static final double DEF_WIDTH = 95.6, DEF_HEIGHT = 50.0;
    private final TrimmedEngine engine;

    public Volvo240(Rectangle rect) {
        super(rect, Color.BLACK, "Volvo240", 4);
        rect.setWidth(DEF_WIDTH);
        rect.setHeight(DEF_HEIGHT);
        engine = new TrimmedEngine(100, 1.25);
    }

    public Volvo240(double x, double y) {
        this(new Rectangle(x, y, DEF_WIDTH, DEF_HEIGHT));
    }

    public Volvo240() {
        this(0, 0);
    }

    @Override
    public String[] getHudInfo() {
        String[] specHud = new String[] {
                "Trim Factor: " + engine.getTrimFactor()
        };
        return StringHelper.concatenateStrArr(super.getHudInfo(), specHud);
    }

    /**
     * Gets the speed factor.
     * @return the speed factor
     */
    public double speedFactor() {
        return engine.speedFactor();
    }

    /**
     * Gets the trim factor.
     * @return the trim factor
     */
    public double getTrimFactor() {
        return engine.getTrimFactor();
    }

    @Override
    public void turnOn() {
        engine.turnOn();
    }

    @Override
    public void turnOff() {
        engine.turnOff();
    }

    @Override
    public void toggleOn() {
        engine.toggleOn();
    }

    @Override
    public boolean isTurnedOn() {
        return engine.isTurnedOn();
    }

    @Override
    public double getPower() {
        return engine.getPower();
    }
}

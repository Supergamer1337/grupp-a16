package labb1.del2.vehicles;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import labb1.del2.controllers.IControllable;
import labb1.del2.controllers.Saab95Controller;
import labb1.del2.vehicleparts.engines.TurboEngine;

public final class Saab95 extends Car {
    private static final double DEF_WIDTH = 100.20, DEF_HEIGHT = 50;

    private final TurboEngine engine;

    public Saab95(Rectangle rect) {
        super(rect, Color.RED, "Saab95", 2);
        engine = new TurboEngine(125);
        rect.setWidth(DEF_WIDTH);
        rect.setHeight(DEF_HEIGHT);
        rect.setRotate(getRotation());
    }

    public Saab95(double x, double y) {
        this(new Rectangle(x, y, DEF_WIDTH, DEF_HEIGHT));
    }

    public Saab95() {
        this(0, 0);
    }

    @Override
    public String[] specificHudInfo() {
        return new String[] {
                "Turbo active: " + getTurboState()
        };
    }

    /**
     * Gets the turbo state.
     * @return true if turbo is on, false otherwise.
     */
    public boolean getTurboState() {
        return engine.getTurboState();
    }

    /**
     * Toggles the turbo on/off. True -> false, false -> true.
     */
    public void toggleTurbo() {
        engine.toggleTurbo();
    }

    @Override
    public void toggleOn() {
        engine.toggleOn();
    }

    @Override
    public double getPower() {
        return engine.getPower();
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
    public boolean isTurnedOn() {
        return engine.isTurnedOn();
    }

    @Override
    public double speedFactor() {
        return engine.speedFactor();
    }

    @Override
    public IControllable getController() {
        return new Saab95Controller(this);
    }

    /**
     * Turns the turbo on.
     */
    public void turboOn() {
        engine.turboOn();
    }

    /**
     * Turns the turbo off.
     */
    public void turboOff() {
        engine.turboOff();
    }
}

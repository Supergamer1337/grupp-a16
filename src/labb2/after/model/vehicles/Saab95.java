package labb2.after.model.vehicles;

import javafx.scene.paint.Color;
import labb2.after.model.vehicleparts.engines.TurboEngine;
import labb2.after.utils.Vector2D;
import labb2.after.model.vehicleparts.CarAppearance;
import labb2.after.model.vehicleparts.CarPhysics;

public final class Saab95 extends Car {
    private static final double DEF_WIDTH = 100.20, DEF_HEIGHT = 50;
    private static final int DEF_WEIGHT = 1495;

    private final TurboEngine engine;

    public Saab95(CarPhysics physics) {
        super("Saab95", new CarPhysics(physics), new CarAppearance(2, Color.RED));
        engine = new TurboEngine(125);
    }

    public Saab95(Vector2D pos, Vector2D direction) {
        this(new CarPhysics(pos.copy(), direction.copy(), DEF_WIDTH, DEF_HEIGHT, DEF_WEIGHT));
    }

    public Saab95(Vector2D pos) {
        this(pos, new Vector2D(1,0));
    }

    public Saab95() {
        this(new Vector2D());
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

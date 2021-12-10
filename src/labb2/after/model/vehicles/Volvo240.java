package labb2.after.model.vehicles;

import javafx.scene.paint.Color;
import labb1.del2.vehicleparts.engines.TrimmedEngine;
import labb2.after.utils.utils.Vector2D;
import labb2.after.model.vehicleparts.CarAppearance;
import labb2.after.model.vehicleparts.CarPhysics;

public final class Volvo240 extends Car {
    private static final double DEF_WIDTH = 95.6, DEF_HEIGHT = 50.0;
    private static final int DEF_WEIGHT = 1243;

    private final TrimmedEngine engine;

    public Volvo240(CarPhysics physics) {
        super("Volvo240", new CarPhysics(physics), new CarAppearance(4, Color.BLACK));
        engine = new TrimmedEngine(100, 1.25);
    }

    public Volvo240(Vector2D pos, Vector2D direction) {
        this(new CarPhysics(pos, direction, DEF_WIDTH, DEF_HEIGHT, DEF_WEIGHT));
    }

    public Volvo240(Vector2D pos) {
        this(pos, new Vector2D(0,1));
    }

    public Volvo240() {
        this(new Vector2D());
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

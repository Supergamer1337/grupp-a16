package labb2.after.model.vehicles;

import javafx.scene.paint.Color;
import labb1.del2.vehicleparts.engines.Engine;
import labb1.del2.vehicleparts.flatbeds.AngledFlatbed;
import labb2.after.utils.Vector2D;
import labb2.after.model.vehicleparts.CarAppearance;
import labb2.after.model.vehicleparts.CarPhysics;

public final class Scania extends Car {
    private static final double DEF_WIDTH = 118.66, DEF_HEIGHT = 75;
    private static final int DEF_WEIGHT = 18000 ;

    private final AngledFlatbed flatbed;
    private final Engine engine;

    public Scania(CarPhysics physics) {
        super("Scania", new CarPhysics(physics), new CarAppearance(2, Color.BLUE));
        flatbed = new AngledFlatbed();
        engine = new Engine(90);
    }

    public Scania(Vector2D pos, Vector2D direction) {
        this(new CarPhysics(pos, direction, DEF_WIDTH, DEF_HEIGHT, DEF_WEIGHT));
    }

    public Scania(Vector2D pos) {
        this(pos, new Vector2D(1,0));
    }

    public Scania() {
        this(new Vector2D());
    }

    @Override
    public double speedFactor() {
        if (flatbed.isLowered()) {
            return 0.01 * getPower();
        }
        return 0;
    }

    /**
     * Raises the flatbed.
     */
    public void raiseFlatbed() throws IllegalStateException {
        if (getSpeed() != 0) {
            throw new IllegalStateException("Unable to raise flatbed whilst moving");
        }
        flatbed.raiseFlatbed();
    }

    /**
     * Lowers the flatbed.
     */
    public void lowerFlatbed() {
        flatbed.lowerFlatbed();
    }

    /**
     * Gets whether the flatbed is lowered.
     * @return true if the flatbed is lowered, false otherwise.
     */
    public boolean isFlatbedLowered() {
        return flatbed.isLowered();
    }

    /**
     * Gets the current flatbed angle.
     * @return The angle of the flatbed.
     */
    public double getFlatbedAngle() {
        return flatbed.getFlatbedAngle();
    }

    /**
     * Gets the maximum angle of the flatbed.
     * @return The maximum angle of the flatbed.
     */
    public double getMaxFlatbedAngle() {
        return flatbed.getMaxAngle();
    }

    /**
     * Gets the minimum angle of the flatbed.
     * @return The minimum angle of the flatbed.
     */
    public double getMinFlatbedAngle() {
        return flatbed.getMinAngle();
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

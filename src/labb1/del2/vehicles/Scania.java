package labb1.del2.vehicles;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import labb1.del2.controllers.IControllable;
import labb1.del2.controllers.ScaniaController;
import labb1.del2.vehicleparts.flatbeds.AngledFlatbed;
import labb1.del2.vehicleparts.engines.Engine;

public final class Scania extends Car {
    private static final double DEF_WIDTH = 118.66, DEF_HEIGHT = 75;

    private final AngledFlatbed flatbed;
    private final Engine engine;

    public Scania(Rectangle rect) {
        super(rect, Color.BLUE, "Scania", 2);
        rect.setWidth(DEF_WIDTH);
        rect.setHeight(DEF_HEIGHT);
        rect.setRotate(getRotation());
        flatbed = new AngledFlatbed();
        engine = new Engine(90);
    }

    public Scania(double x, double y) {
        this(new Rectangle(x, y, DEF_WIDTH,DEF_HEIGHT));
    }

    public Scania() { this(0,0); }

    @Override
    public double speedFactor() {
        if (flatbed.isLowered()) {
            return 0.01 * getPower();
        }
        return 0;
    }

    @Override
    public String[] specificHudInfo() {
        return new String[] {
                String.format("Flatbed angle: %.2f", flatbed.getFlatbedAngle())
        };
    }

    /**
     * Raises the flatbed.
     */
    public void raiseFlatbed() {
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
    public IControllable getController() {
        return new ScaniaController(this);
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

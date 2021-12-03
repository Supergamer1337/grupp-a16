package labb1.del2.vehicleparts.engines;

import labb1.del2.vehicleparts.engines.Engine;

public class TrimmedEngine extends Engine {
    private static final double DEF_TRIM_FACTOR = 1.3;

    private final double trimFactor;

    public TrimmedEngine(double power, double trimFactor) {
        super(power);
        this.trimFactor = trimFactor;
    }

    public TrimmedEngine(double power){
        this(power, DEF_TRIM_FACTOR);
    }

    @Override
    public double speedFactor() {
        return super.speedFactor() * trimFactor;
    }

    /**
     * Gets the trim factor.
     * @return The trim factor.
     */
    public double getTrimFactor() {
        return trimFactor;
    }

    @Override
    public String toString() {
        return "TrimmedEngine{" +
                "power=" + getPower() +
                ", turnedOn=" + isTurnedOn() +
                "trimFactor=" + trimFactor +
                '}';
    }
}

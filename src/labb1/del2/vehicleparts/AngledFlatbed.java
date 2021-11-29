package labb1.del2.vehicleparts;

import labb1.del2.Main;

public class AngledFlatbed implements IFlatbed{
    private static final double DEF_MAX_Angle = 70, DEF_MIN_ANGLE = 0, rotationSpeed = 30;

    private double currentFlatbedAngle, maxAngle, minAngle;

    public AngledFlatbed(double maxAngle, double minAngle) {
        this.maxAngle = maxAngle;
        this.minAngle = minAngle;
        currentFlatbedAngle = 0;
    }

    public AngledFlatbed() {
        this(DEF_MAX_Angle, DEF_MIN_ANGLE);
    }

    @Override
    public void raiseFlatbed() {
        currentFlatbedAngle = Math.min(rotationSpeed * Main.getDeltaTime() + currentFlatbedAngle, maxAngle);
    }

    @Override
    public void lowerFlatbed() {
        currentFlatbedAngle = Math.max(currentFlatbedAngle - rotationSpeed * Main.getDeltaTime(), minAngle);
    }

    @Override
    public boolean isLowered() {
        return currentFlatbedAngle == minAngle;
    }

    public final double getFlatbedAngle() { return currentFlatbedAngle; }
    public final double getMaxAngle() { return maxAngle; }
    public final double getMinAngle() { return minAngle; }

}
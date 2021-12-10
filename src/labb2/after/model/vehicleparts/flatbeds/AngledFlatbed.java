package labb2.after.model.vehicleparts.flatbeds;

import labb2.after.model.Model;

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
        currentFlatbedAngle = Math.min(rotationSpeed * Model.getDeltaTime() + currentFlatbedAngle, maxAngle);
    }

    @Override
    public void lowerFlatbed() {
        currentFlatbedAngle = Math.max(currentFlatbedAngle - rotationSpeed * Model.getDeltaTime(), minAngle);
    }

    @Override
    public boolean isLowered() {
        return currentFlatbedAngle == minAngle;
    }

    /**
     * Gets the current angle of the flatbed.
     * @return The current angle of the flatbed.
     */
    public final double getFlatbedAngle() { return currentFlatbedAngle; }

    /**
     * Gets the maximum angle of the flatbed.
     * @return The maximum angle of the flatbed.
     */
    public final double getMaxAngle() { return maxAngle; }

    /**
     * Gets the minimum angle of the flatbed.
     * @return The minimum angle of the flatbed.
     */
    public final double getMinAngle() { return minAngle; }

}
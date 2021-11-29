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

    /**
     * Raises the flatbed.
     */
    @Override
    public void raiseFlatbed() {
        currentFlatbedAngle = Math.min(rotationSpeed * Main.getDeltaTime() + currentFlatbedAngle, maxAngle);
    }

    /**
     * Lowers the flatbed.
     */
    @Override
    public void lowerFlatbed() {
        currentFlatbedAngle = Math.max(currentFlatbedAngle - rotationSpeed * Main.getDeltaTime(), minAngle);
    }

    /**
     * Checks if the flatbed is raised.
     * @return true if the flatbed is lowered, false otherwise.
     */
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
package labb1.del2.gameobjects.vehicles.vehicleparts;

import labb1.del2.View;

public class AngledFlatbed implements IFlatbed{
    private static final double maxFlatbedAngle = 70, minFlatbedAngle = 0, rotationSpeed = 30;
    private double currentFlatbedAngle;

    public AngledFlatbed() {
        currentFlatbedAngle = 0;
    }

    @Override
    public void raiseFlatbed() {
        currentFlatbedAngle = Math.min(rotationSpeed * View.getDeltaTime() + currentFlatbedAngle, maxFlatbedAngle);

    }

    @Override
    public void lowerFlatbed() {
        currentFlatbedAngle = Math.max(currentFlatbedAngle - rotationSpeed * View.getDeltaTime(), minFlatbedAngle);
    }

    @Override
    public boolean isLowered() {
        return currentFlatbedAngle == minFlatbedAngle;
    }

    public final double getFlatbedAngle() {
        return currentFlatbedAngle;
    }
}
package labb1.del2.gameobjects.vehicles.vehicleparts;

public class AngledFlatbed implements IFlatbed{
    private static final double maxFlatbedAngle = 70, minFlatbedAngle = 0, rotationSpeed = 5;
    private double currentFlatbedAngle;

    public AngledFlatbed() {
        currentFlatbedAngle = 0;
    }

    @Override
    public void raiseFlatbed() {
        currentFlatbedAngle = Math.min(rotationSpeed + currentFlatbedAngle, maxFlatbedAngle);

    }

    @Override
    public void lowerFlatbed() {
        currentFlatbedAngle = Math.max(currentFlatbedAngle - rotationSpeed, minFlatbedAngle);
    }

    @Override
    public boolean isLowered() {
        if (currentFlatbedAngle == minFlatbedAngle) {
            return true;
        }
        return false;
    }

    public final double getFlatbedAngle() {
        return currentFlatbedAngle;
    }
}
package labb1del2.movables.parts;

public class Flatbed {
    private static final double maxFlatbedAngle = 70, minFlatbedAngle = 0;
    private double currentFlatbedAngle;

    public Flatbed() {
        currentFlatbedAngle = 0;
    }

    public void raiseFlatbed(double angle) {
        if (angle > 0)  {
            currentFlatbedAngle = Math.min(angle + currentFlatbedAngle, maxFlatbedAngle);
        }
    }

    public void lowerFlatbed(double angle) {
        if (angle > 0) {
            currentFlatbedAngle = Math.max(currentFlatbedAngle - angle, minFlatbedAngle);
        }
    }

    public double getCurrentFlatbedAngle() {
        return currentFlatbedAngle;
    }
}
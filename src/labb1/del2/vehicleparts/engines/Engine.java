package labb1.del2.vehicleparts.engines;

public class Engine implements IEngine {
    private static final double SPEED_FACTOR_CONST = 0.01;
    private final double power;
    private boolean turnedOn;

    public Engine(double power) {
        this.power = power;
    }

    @Override
    public final double getPower() {
        return this.power;
    }

    @Override
    public final boolean isTurnedOn() {
        return turnedOn;
    }

    @Override
    public final void turnOn() {
        turnedOn = true;
    }

    @Override
    public final void turnOff() {
        turnedOn = false;
    }

    @Override
    public final void toggleOn() {
        turnedOn = !turnedOn;
    }

    @Override
    public double speedFactor() {
        return SPEED_FACTOR_CONST * power;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "power=" + power +
                ", turnedOn=" + turnedOn +
                '}';
    }
}

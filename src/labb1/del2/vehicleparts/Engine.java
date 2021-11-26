package labb1.del2.vehicleparts;

public class Engine {
    private static final double DEF_ENGINE_CONST = 0.01;
    private final double power;
    private boolean turnedOn;

    public Engine(double power) {
        this.power = power;
    }

    public double speedFactor() {
        return power * DEF_ENGINE_CONST;
    }

    public final double getPower() {
        return this.power;
    }
    public final boolean isTurnedOn() {
        return turnedOn;
    }
    public final void turnOn() {
        turnedOn = true;
    }
    public final void turnOff() {
        turnedOn = false;
    }
    public final void toggleEngineOn() {
        turnedOn = !turnedOn;
    }
}

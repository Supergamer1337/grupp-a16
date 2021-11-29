package labb1.del2.vehicleparts;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Engine engine = (Engine) o;
        return Double.compare(engine.power, power) == 0 && turnedOn == engine.turnedOn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(power, turnedOn);
    }

    public final double getPower() {
        return this.power;
    }

    /**
     * Gets the engine's status.
     * @return Whether the engine is turned on or off.
     */
    public final boolean isTurnedOn() {
        return turnedOn;
    }

    /**
     * Turns on the engine.
     */
    public final void turnOn() {
        turnedOn = true;
    }

    /**
     * Turns off the engine.
     */
    public final void turnOff() {
        turnedOn = false;
    }

    /**
     * Toggles the engine's status.
     */
    public final void toggleEngineOn() {
        turnedOn = !turnedOn;
    }
}

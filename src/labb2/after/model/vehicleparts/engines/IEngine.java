package labb2.after.model.vehicleparts.engines;

public interface IEngine {
    /**
     * Turns on the engine.
     */
    void turnOn();

    /**
     * Turns off the engine.
     */
    void turnOff();

    /**
     * Toggles the engine's status.
     */
    void toggleOn();

    /**
     * Gets the engine's status.
     * @return Whether the engine is turned on or off.
     */
    boolean isTurnedOn();

    /**
     * Gets the engine's speed factor.
     * @return The engine's speed factor.
     */
    double speedFactor();

    /**
     * Gets the engine's power (max speed).
     * @return The engine's power (max speed).
     */
    double getPower();
}

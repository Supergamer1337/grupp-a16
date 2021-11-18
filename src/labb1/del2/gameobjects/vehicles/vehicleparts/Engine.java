package labb1.del2.gameobjects.vehicles.vehicleparts;

public class Engine {
    private double power;
    private boolean turnedOn;

    public Engine(double power) {
        this.power = power;
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

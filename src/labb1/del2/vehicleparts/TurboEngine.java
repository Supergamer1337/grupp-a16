package labb1.del2.vehicleparts;

public class TurboEngine extends Engine {
    private boolean turboOn;

    public TurboEngine(double power) {
        super(power);
        turboOn = false;
    }

    /**
     * Gets the turbo status.
     * @return true if turbo is on, false otherwise.
     */
    public final boolean getTurboState() {
        return turboOn;
    }

    /**
     * Toggles the turbo status. Makes false -> true and true -> false.
     */
    public final void toggleTurbo() {
        turboOn = !turboOn;
    }

    /**
     * Turns the turbo on.
     */
    public final void turboOn() {
        turboOn = true;
    }

    /**
     * Turns the turbo off.
     */
    public final void turboOff() {
        turboOn = false;
    }

    @Override
    public double speedFactor() {
        double turbo = 1;
        if (turboOn) turbo = 1.3;
        return super.speedFactor() * turbo;
    }

    @Override
    public String toString() {
        return "TurboEngine{" +
                "power=" + getPower() +
                ", turnedOn=" + isTurnedOn() +
                ", turboOn=" + turboOn +
                '}';
    }
}

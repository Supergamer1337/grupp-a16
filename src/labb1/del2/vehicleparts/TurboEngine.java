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

    @Override
    public double speedFactor() {
        double turbo = 1;
        if (turboOn) turbo = 1.3;
        return super.speedFactor() * turbo;
    }
}

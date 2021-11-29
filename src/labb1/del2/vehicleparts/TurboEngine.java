package labb1.del2.vehicleparts;

public class TurboEngine extends Engine {
    private boolean turboOn;

    public TurboEngine(double power) {
        super(power);
        turboOn = false;
    }

    public final boolean getTurboState() {
        return turboOn;
    }

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

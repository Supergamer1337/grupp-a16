package labb1.del2.gameobjects.vehicles.cars;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import labb1.del2.gameobjects.vehicles.vehicleparts.Engine;

public final class Saab95 extends Car {

    private boolean turboOn;

    public Saab95(Rectangle rect) {
        super(rect, Color.RED, "Saab95", 2, new Engine(125));
        turboOn = false;
    }

    public Saab95(double x, double y) {
        this(new Rectangle(x, y, 100.20, 50));
    }

    public Saab95() {
        this(0, 0);
    }

    @Override
    public String[] getHudInfo() {
        String[] specHud = new String[] {
                "Turbo active: " + turboOn
        };
        return concatenateStrArr(super.getHudInfo(), specHud);
    }

    public double speedFactor() {
        double turbo = 1;
        if (turboOn) turbo = 1.3;
        return getEngine().getPower() * 0.01 * turbo;
    }

    public void toggleTurbo() {
        turboOn = !turboOn;
    }

}

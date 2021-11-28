package labb1.del2.vehicles;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import labb1.del2.controllers.IControllable;
import labb1.del2.controllers.Saab95Controller;
import labb1.del2.utils.StringHelper;
import labb1.del2.vehicleparts.Engine;

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
        return StringHelper.concatenateStrArr(super.getHudInfo(), specHud);
    }

    @Override
    public double speedFactor() {
        double turbo = 1;
        if (turboOn) turbo = 1.3;
        return getEngine().getPower() * 0.01 * turbo;
    }

    public void toggleTurbo() {
        turboOn = !turboOn;
    }

    @Override
    public IControllable getController() {
        return new Saab95Controller(this);
    }
}

package labb1.del2.vehicles;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import labb1.del2.utils.StringHelper;
import labb1.del2.vehicleparts.Engine;

public final class Volvo240 extends Car {
    private static final double DEF_WIDTH = 95.6, DEF_HEIGHT = 50.0;

    private static double trimFactor = 1.25;

    public Volvo240(Rectangle rect) {
        super(rect, Color.BLACK, "Volvo240", 4, new Engine(100));
        rect.setWidth(DEF_WIDTH);
        rect.setHeight(DEF_HEIGHT);
    }

    public Volvo240(double x, double y) {
        this(new Rectangle(x, y, DEF_WIDTH, DEF_HEIGHT));
    }

    public Volvo240() {
        this(0, 0);
    }

    @Override
    public String[] getHudInfo() {
        String[] specHud = new String[] {
                "Trim Factor: " + trimFactor
        };
        return StringHelper.concatenateStrArr(super.getHudInfo(), specHud);
    }

    public double speedFactor() {
        return getEngine().getPower() * 0.01 * trimFactor;
    }
}

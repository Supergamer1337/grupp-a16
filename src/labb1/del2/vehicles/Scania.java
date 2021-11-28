package labb1.del2.vehicles;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import labb1.del2.controllers.IControllable;
import labb1.del2.controllers.ScaniaController;
import labb1.del2.utils.StringHelper;
import labb1.del2.vehicleparts.AngledFlatbed;
import labb1.del2.vehicleparts.Engine;

import java.util.Objects;

public final class Scania extends Car {
    private static final double DEF_WIDTH = 118.66, DEF_HEIGHT = 75;

    private final AngledFlatbed flatbed;

    public Scania(Rectangle rect) {
        super(rect, Color.BLUE, "Scania", 2, new Engine(90));
        rect.setWidth(DEF_WIDTH);
        rect.setHeight(DEF_HEIGHT);
        flatbed = new AngledFlatbed();
    }

    public Scania(double x, double y) {
        this(new Rectangle(x, y, DEF_WIDTH,DEF_HEIGHT));
    }

    public Scania() { this(0,0); }

    @Override
    public double speedFactor() {
        if (flatbed.isLowered()) {
            return getEnginePower() * 0.01;
        }
        return 0;
    }

    @Override
    public String[] getHudInfo() {
        String[] specHud = new String[] {
                String.format("Flatbed angle: %.2f", flatbed.getFlatbedAngle())
        };
        return StringHelper.concatenateStrArr(super.getHudInfo(), specHud);
    }

    @Override
    public IControllable getController() {
        return new ScaniaController(this);
    }

    public AngledFlatbed getFlatbed() {
        return flatbed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(flatbed);
    }
}

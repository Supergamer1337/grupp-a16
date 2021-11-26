package labb1.del2.gameobjects.vehicles.cars;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import labb1.del2.vehicleparts.AngledFlatbed;
import labb1.del2.vehicleparts.Engine;

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

    @Override
    public double speedFactor() {
        if (flatbed.isLowered()) {
            return getEngine().getPower() * 0.01;
        }
        return 0;
    }

    @Override
    public String[] getHudInfo() {
        String[] specHud = new String[] {
                String.format("Flatbed angle: %.2f", flatbed.getFlatbedAngle()),
                "Nr of doors: " + getNrOfDoors()
        };
        return concatenateStrArr(super.getHudInfo(), specHud);
    }

    public AngledFlatbed getFlatbed() {
        return flatbed;
    }
}

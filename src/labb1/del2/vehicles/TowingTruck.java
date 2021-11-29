package labb1.del2.vehicles;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import labb1.del2.controllers.IControllable;
import labb1.del2.controllers.TowingTruckController;
import labb1.del2.utils.StringHelper;
import labb1.del2.vehicleparts.Loader;
import labb1.del2.vehicleparts.Engine;
import labb1.del2.vehicleparts.SimpleFlatbed;

import java.util.Arrays;

public final class TowingTruck extends Car {
    private static final int BASE_CAR_LOAD_LIMIT = 2;
    private static final double DEF_PICKUP_RADIUS = 20, DEF_WIDTH = 323, DEF_HEIGHT = 75;
    private final Loader<Car> ct;
    private final SimpleFlatbed flatbed;
    private final double pickupRadius;

    public TowingTruck(Rectangle rect) {
        super(rect, Color.TURQUOISE, "Towing Truck", 2, new Engine(80));
        rect.setWidth(DEF_WIDTH);
        rect.setHeight(DEF_HEIGHT);
        ct = new Loader<>(BASE_CAR_LOAD_LIMIT);
        flatbed = new SimpleFlatbed();
        pickupRadius = DEF_PICKUP_RADIUS;
    }

    public TowingTruck(double x, double y) {
        this(new Rectangle(x, y, DEF_WIDTH, DEF_HEIGHT));
    }

    public TowingTruck() { this(0, 0); }

    @Override
    public double speedFactor() {
        if (!flatbed.isLowered()) {
            return 0.01 * getEnginePower();
        }
        return 0;
    }

    @Override
    public String[] getHudInfo() {
        String[] specHud = new String[] {
                "Ramp lowered: " + flatbed.isLowered(),
                "Loaded cars: " + Arrays.toString(ct.getNames())
        };
        return StringHelper.concatenateStrArr(super.getHudInfo(), specHud);
    }

    // TODO: Implement fully
    public void loadCar(Car car) {
        if (!flatbed.isLowered() && !isWithinRange(car.getPosX(), car.getPosY())) {
            throw new RuntimeException("Given car is outside of pickup range");
        }
        ct.load(car);
    }

    public Car unloadCar() {
        if (!flatbed.isLowered()) {
            throw new RuntimeException("Unable to unload car when ramp isn't lowered");
        }
        return ct.unload();
    }

    private boolean isWithinRange(double x, double y) {
        return x - getPosX() < pickupRadius && y - getPosY() < pickupRadius;
    }

    public void raiseRamp() {
        flatbed.raiseFlatbed();
    }

    public void lowerRamp() {
        flatbed.lowerFlatbed();
    }

    public boolean isFlatbedLowered() { return flatbed.isLowered(); }

    @Override
    public IControllable getController() {
        return new TowingTruckController(this);
    }
}

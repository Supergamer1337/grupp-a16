package labb1.del2.vehicles;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import labb1.del2.controllers.IControllable;
import labb1.del2.controllers.TowingTruckController;
import labb1.del2.utils.StringHelper;
import labb1.del2.vehicleparts.CarTransporter;
import labb1.del2.vehicleparts.Engine;
import labb1.del2.vehicleparts.SimpleFlatbed;
import labb1.del2.utils.Vector2D;

import java.util.Arrays;

public final class TowingTruck extends Car {
    private static final int BASE_CAR_LOAD_LIMIT = 2;
    private static final double DEF_PICKUP_RADIUS = 20, DEF_WIDTH = 323, DEF_HEIGHT = 75;
    private final CarTransporter ct;
    private final SimpleFlatbed flatbed;
    private final double pickupRadius;

    public TowingTruck(Rectangle rect) {
        super(rect, Color.TURQUOISE, "Towing Truck", 2, new Engine(80));
        rect.setWidth(DEF_WIDTH);
        rect.setHeight(DEF_HEIGHT);
        ct = new CarTransporter(BASE_CAR_LOAD_LIMIT);
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
                "Loaded cars: " + Arrays.toString(ct.getCarNames())
        };
        return StringHelper.concatenateStrArr(super.getHudInfo(), specHud);
    }

    // TODO: Implement fully
    public boolean loadCar(Car car) {
        if (flatbed.isLowered() && isWithinRange(new Vector2D(car.getPosX(), car.getPosY()))) {
            ct.loadCar(car);
            return true;
        }
        return false;
    }

    private boolean isWithinRange(Vector2D pos) {
        return pos.getX() - getPosX() < pickupRadius && pos.getY() - getPosY() < pickupRadius;
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

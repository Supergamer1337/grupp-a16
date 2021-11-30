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
    private final Loader<Car> loader;
    private final SimpleFlatbed flatbed;
    private final double pickupRadius;
    private final Engine engine;

    public TowingTruck(Rectangle rect) {
        super(rect, Color.TURQUOISE, "Towing Truck", 2);
        rect.setWidth(DEF_WIDTH);
        rect.setHeight(DEF_HEIGHT);
        loader = new Loader<>(BASE_CAR_LOAD_LIMIT);
        flatbed = new SimpleFlatbed();
        pickupRadius = DEF_PICKUP_RADIUS;
        engine = new Engine(80);
    }

    public TowingTruck(double x, double y) {
        this(new Rectangle(x, y, DEF_WIDTH, DEF_HEIGHT));
    }

    public TowingTruck() { this(0, 0); }

    @Override
    public double speedFactor() {
        if (!flatbed.isLowered()) {
            return 0.01 * getPower();
        }
        return 0;
    }


    @Override
    public String[] getHudInfo() {
        String[] specHud = new String[] {
                "Ramp lowered: " + flatbed.isLowered(),
                "Loaded cars: " + Arrays.toString(loader.getNames())
        };
        return StringHelper.concatenateStrArr(super.getHudInfo(), specHud);
    }

    // TODO: Implement fully

    /**
     * Loads a car into the towing truck.
     * @param car The car to load.
     */
    public void loadCar(Car car) {
        if (!flatbed.isLowered() && !isWithinRange(car.getPosX(), car.getPosY())) {
            throw new RuntimeException("Given car is outside of pickup range");
        }
        loader.load(car);
    }

    /**
     * Unloads last loaded car from the towing truck.
     * @return The unloaded car.
     */
    public Car unloadCar() {
        if (!flatbed.isLowered()) {
            throw new RuntimeException("Unable to unload car when ramp isn't lowered");
        }
        return loader.unload();
    }

    /**
     * Checks whether the given car is within the pickup radius of the towing truck.
     * @param x The x-coordinate of the car.
     * @param y The y-coordinate of the car.
     * @return Whether the car is within the pickup radius.
     */
    private boolean isWithinRange(double x, double y) {
        return x - getPosX() < pickupRadius && y - getPosY() < pickupRadius;
    }

    /**
     * Raises the ramp of the towing truck.
     */
    public void raiseRamp() {
        flatbed.raiseFlatbed();
    }

    /**
     * Lowers the ramp of the towing truck.
     */
    public void lowerRamp() {
        flatbed.lowerFlatbed();
    }

    /**
     * Checks whether the ramp is lowered.
     * @return Whether the ramp is lowered.
     */
    public boolean isFlatbedLowered() { return flatbed.isLowered(); }

    @Override
    public IControllable getController() {
        return new TowingTruckController(this);
    }


    @Override
    public void turnOn() {
        engine.turnOn();
    }

    @Override
    public void turnOff() {
        engine.turnOff();
    }

    @Override
    public void toggleOn() {
        engine.toggleOn();
    }

    @Override
    public boolean isTurnedOn() {
        return engine.isTurnedOn();
    }

    @Override
    public double getPower() {
        return engine.getPower();
    }

    public Loader<Car> getLoader() { return loader;}
}

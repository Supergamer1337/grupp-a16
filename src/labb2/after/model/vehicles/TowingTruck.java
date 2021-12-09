package labb2.after.model.vehicles;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import labb1.del2.controllers.IControllable;
import labb1.del2.controllers.TowingTruckController;
import labb1.del2.vehicleparts.engines.Engine;
import labb1.del2.vehicleparts.flatbeds.SimpleFlatbed;
import labb1.del2.vehicleparts.loaders.VehicleLoader;

import java.util.Arrays;
import java.util.List;

public final class TowingTruck extends Car {
    private static final int BASE_CAR_LOAD_LIMIT = 2;
    private static final double DEF_PICKUP_RADIUS = 20, DEF_WIDTH = 323, DEF_HEIGHT = 75;
    private final VehicleLoader<Car> loader;
    private final SimpleFlatbed flatbed;
    private final double pickupRadius;
    private final Engine engine;

    public TowingTruck(Rectangle rect) {
        super(rect, Color.TURQUOISE, "Towing Truck", 2);
        rect.setWidth(DEF_WIDTH);
        rect.setHeight(DEF_HEIGHT);
        rect.setRotate(getRotation());
        loader = new VehicleLoader<>(BASE_CAR_LOAD_LIMIT);
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
    protected String[] specificHudInfo() {
        return new String[] {
                "Ramp lowered: " + flatbed.isLowered(),
                "Loaded cars: " + Arrays.toString(loader.getNames())
        };
    }

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

    /**
     * Gets the last loaded car.
     * @return The last loaded car.
     */
    public List<Car> getLoaded() {
        return loader.getLoaded();
    }

    /**
     * Gets the car loaded at the given index.
     * @param index The index of the car to get.
     * @return The car loaded at the given index.
     * @throws IllegalArgumentException If the index is out of bounds.
     */
    public Car getAtIndex(int index) throws IllegalArgumentException {
        return loader.getAtIndex(index);
    }

    /**
     * Gets the names of the cars loaded.
     * @return String array of the names of the cars loaded.
     */
    public String[] getNames() {
        return loader.getNames();
    }

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
}

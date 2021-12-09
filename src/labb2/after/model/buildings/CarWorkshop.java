package labb2.after.model.buildings;

import javafx.scene.paint.Color;
import labb2.after.model.vehicleparts.loaders.Loader;
import labb2.after.model.vehicles.Car;

public class CarWorkshop<T extends Car> {

    private final Loader<T> loader;

    /**
     * Creates a new VehicleWorkshop with the given Loader and capacity.
     * @param limit The capacity of the VehicleWorkshop.
     */
    public CarWorkshop(int limit) {
        loader = new Loader<>(limit);
    }

    /**
     * Changes the color of the Vehicle at the given index.
     * @param color The new color.
     * @param index The index of the Vehicle.
     */
    public void paintJob(Color color, int index) {
        loader.getAtIndex(index).setColor(color);
    }

    /**
     * Loads a new {@link Car} into the VehicleWorkshop.
     * @param t The Vehicle to load.
     */
    public void load(T t) {
        loader.load(t);
    }

    /**
     * Unloads the latest {@link Car} from the VehicleWorkshop.
     * @return The Vehicle that was unloaded.
     */
    public T unload() {
        return loader.unload();
    }

    /**
     * Gets the vehicle at the given index.
     * @param index The index of the vehicle.
     * @return The vehicle at the given index.
     * @throws IllegalArgumentException If the index is out of bounds.
     */
    public T getAtIndex(int index) throws IllegalArgumentException {
        return loader.getAtIndex(index);
    }
}

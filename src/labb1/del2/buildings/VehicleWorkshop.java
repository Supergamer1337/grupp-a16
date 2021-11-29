package labb1.del2.buildings;

import javafx.scene.paint.Color;
import labb1.del2.vehicleparts.Loader;
import labb1.del2.vehicles.Vehicle;

public class VehicleWorkshop<T extends Vehicle> {

    private final Loader<T> loader;

    /**
     * Creates a new VehicleWorkshop with the given Loader and capacity.
     * @param limit The capacity of the VehicleWorkshop.
     */
    public VehicleWorkshop(int limit) {
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
     * Loads a new {@link Vehicle} into the VehicleWorkshop.
     * @param t The Vehicle to load.
     */
    public void load(T t) {
        loader.load(t);
    }

    /**
     * Unloads the latest {@link Vehicle} from the VehicleWorkshop.
     * @return The Vehicle that was unloaded.
     */
    public T unload() {
        return loader.unload();
    }

    public T getAtIndex(int index) throws IllegalArgumentException {
        return loader.getAtIndex(index);
    }

    public String[] getNames() {
        return loader.getNames();
    }
}

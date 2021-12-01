package labb1.del2.vehicleparts.loaders;

import labb1.del2.vehicles.Car;

import java.util.List;
import java.util.ArrayList;


public class Loader<T> {
    protected final List<T> loaded;
    private final int limit;

    public Loader(int limit) {
        loaded = new ArrayList<>();
        this.limit = limit;
    }

    /**
     * Loads an object onto the transporter.
     * @param t The object to load.
     */
    public void load(T t) {
        if (loaded.size() >= limit) {
            throw new ArrayStoreException("Unable to load more objects than the limit");
        }
        loaded.add(t);
    }

    public List<T> getLoaded() {
        return loaded;
    }

    /**
     * Removes an object from the transporter.
     * @return The object that was removed.
     */
    public T unload() {
        if(loaded.size() < 1) {
            throw new ArrayIndexOutOfBoundsException("Can't unload a nonexistent object");
        }
        return loaded.remove(loaded.size() - 1);
    }

    /**
     * Gets the object loaded at given index.
     * @param index The index of the object to get.
     * @return The object loaded at given index.
     * @throws IllegalArgumentException If the index is out of bounds.
     */
    public T getAtIndex(int index) throws IllegalArgumentException {
        if(index >= loaded.size() || index < 0) {
            throw new IllegalArgumentException("Given index is out of bounds");
        }
        return loaded.get(index);
    }

}

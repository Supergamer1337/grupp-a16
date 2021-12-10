package labb2.after.model.vehicleparts.loaders;

import labb2.after.model.vehicles.IVehicle;

public class VehicleLoader<T extends IVehicle> extends Loader<T> {
    public VehicleLoader(int limit) {
        super(limit);
    }
    /**
     * Gets the names (model names) of the objects loaded.
     * @return Array of the names of the objects loaded.
     */

    public String[] getNames() {
        String[] strings = new String[loaded.size()];
        for (int i = 0; i < loaded.size(); i++) {
            strings[i] = loaded.get(i).getModelName();
        }
        return strings;
    }
}

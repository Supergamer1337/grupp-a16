package labb1.del2.vehicleparts;

import labb1.del2.gameobjects.vehicles.cars.Car;

import java.util.ArrayList;

public final class CarTransporter {
    private ArrayList<Car> loadedCars;
    private final int limit;

    public CarTransporter(int limit) {
        loadedCars = new ArrayList<>();
        this.limit = limit;
    }

    public void loadCar(Car car) {
        if (loadedCars.size() < limit) {
            loadedCars.add(car);
        }
    }

    public Car unloadCar() {
        return loadedCars.remove(loadedCars.size() - 1);
    }

    public Car getCarAtIndex(int index) throws IllegalArgumentException {
        if(index < loadedCars.size()) {
            return loadedCars.get(index);
        }
        throw new IllegalArgumentException("CarTransporter, getCarAtIndex(): Given index is out of bounds");
    }

    public String[] getCarNames() {
        String[] carNames = new String[loadedCars.size()];
        for (int i = 0; i < loadedCars.size(); i++) {
            carNames[i] = loadedCars.get(i).getModelName();
        }
        return carNames;
    }

}

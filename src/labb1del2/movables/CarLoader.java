package labb1del2.movables;

import labb1del2.movables.cars.Car;

public class CarLoader {
    private int maxTotalWeight;
    private Car[] loadedCars;
    private double carPickupRange;


    public CarLoader(int maxTotalWeight, int maxAmountOfCars, double carPickupRange) {
        this.maxTotalWeight = maxTotalWeight;
        loadedCars = new Car[maxAmountOfCars];
        this.carPickupRange = carPickupRange;
    }

    public void loadCar(Car car) {
        
    }

    public Car getLoadedCar(int index) {
        return loadedCars[index];
    }

    public int getMaxAmountOfCars() {
        return loadedCars.length;
    }
}

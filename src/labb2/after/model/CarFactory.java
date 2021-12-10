package labb2.after.model;

import labb2.after.model.vehicles.*;

public class CarFactory {

    public static Car[] createAllCars() {
        return new Car[]{
                makeSaab95(),
                makeVolvo240(),
                makeScania()
        };
    }

    private static Scania makeScania() {
        return new Scania();
    }

    private static Volvo240 makeVolvo240() {
        return new Volvo240();
    }

    private static Saab95 makeSaab95() {
        return new Saab95();
    }

    public static Car[] createDefaultCars() {
        return new Car[]{
                makeSaab95(),
                makeVolvo240(),
                makeScania()
        };
    }
}

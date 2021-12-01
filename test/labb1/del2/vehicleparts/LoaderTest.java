package labb1.del2.vehicleparts;

import labb1.del2.vehicles.Car;
import labb1.del2.vehicles.Saab95;
import labb1.del2.vehicles.TowingTruck;
import labb1.del2.vehicles.Volvo240;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoaderTest {

    @Test
    void testLoad() {
        TowingTruck towingTruck = new TowingTruck();
        Car car = new Saab95();
        towingTruck.lowerRamp();
        towingTruck.loadCar(car);
        assertTrue(towingTruck.getLoaded().size() > 0);
    }

    @Test
    void testOverload() {
        TowingTruck towingTruck = new TowingTruck();
        Car car = new Saab95();
        Car car2 = new Volvo240();
        Car car3 = new Saab95();
        towingTruck.lowerRamp();
        towingTruck.loadCar(car);
        towingTruck.loadCar(car2);
        assertThrows(ArrayStoreException.class, () -> towingTruck.loadCar(car3));
    }

    @Test
    void testUnload() {
        TowingTruck towingTruck = new TowingTruck();
        Car car = new Saab95();
        towingTruck.lowerRamp();
        towingTruck.loadCar(car);
        towingTruck.unloadCar();
        assertEquals(0, towingTruck.getLoaded().size());
    }

    @Test
    void testGetAtIndex() {
        TowingTruck towingTruck = new TowingTruck();
        Car car = new Saab95();
        towingTruck.loadCar(car);
        assertEquals(car, towingTruck.getAtIndex(0));
    }

    @Test
    void testGetNames() {
        TowingTruck towingTruck = new TowingTruck();
        Car car = new Saab95();
        Car car2 = new Volvo240();
        towingTruck.loadCar(car);
        towingTruck.loadCar(car2);
        assertArrayEquals(new String[] { "Saab95", "Volvo240" }, towingTruck.getNames());
    }
}
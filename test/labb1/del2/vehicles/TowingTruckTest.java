package labb1.del2.vehicles;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TowingTruckTest {

    @Test
    void testSpeedFactor() {
        TowingTruck towingTruck = new TowingTruck();
        assertEquals(towingTruck.getPower() * 0.01, towingTruck.speedFactor());
        towingTruck.lowerRamp();
        assertEquals(0, towingTruck.speedFactor());
    }

    @Test
    void testGetHudInfo() {
        TowingTruck towingTruck = new TowingTruck();
        String[] info = towingTruck.getHudInfo();
        assertEquals("Model: Towing Truck", info[0]);
        assertEquals("Engine On: false", info[1]);
        assertEquals("Speed: 0km/h", info[2]);
        assertEquals("Nr of doors: 2", info[3]);
        assertEquals("Ramp lowered: false", info[4]);
    }

    @Test
    void testLoadCar() {
        TowingTruck towingTruck = new TowingTruck();
        Car car = new Saab95();
        towingTruck.loadCar(car);
    }

    @Test
    void testRaiseRamp() {
        TowingTruck towingTruck = new TowingTruck();
        towingTruck.raiseRamp();
        assertFalse(towingTruck.isFlatbedLowered());
    }

    @Test
    void testLowerRamp() {
        TowingTruck towingTruck = new TowingTruck();
        towingTruck.lowerRamp();
        assertTrue(towingTruck.isFlatbedLowered());
    }

    @Test
    void testIsFlatbedLowered() {
        TowingTruck towingTruck = new TowingTruck();
        assertFalse(towingTruck.isFlatbedLowered());
    }

    @Test
    void testGetController() {
        TowingTruck towingTruck = new TowingTruck();
        assertNotEquals(null, towingTruck.getController());
    }
}
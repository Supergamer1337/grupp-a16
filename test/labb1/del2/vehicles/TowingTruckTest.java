package labb1.del2.vehicles;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

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
        String[] info = {
                "Model: Towing Truck",
                "Engine On: false",
                "Speed: 0km/h",
                "Nr of doors: 2",
                "Ramp lowered: false",
                "Loaded cars: []"
        };
        assertArrayEquals(info, towingTruck.getHudInfo(), "Tests something");
    }

    @Test
    void testSpecificHudInfo() {
        TowingTruck towingTruck = new TowingTruck();
        String[] info = {
            "Ramp lowered: false",
            "Loaded cars: []"
        };
        assertArrayEquals(info, towingTruck.specificHudInfo());
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
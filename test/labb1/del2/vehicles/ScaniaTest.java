package labb1.del2.vehicles;

import labb1.del2.vehicleparts.AngledFlatbed;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScaniaTest {

    @Test
    void testSpeedFactor() {
        Scania scania = new Scania();
        assertEquals(scania.getEnginePower() * 0.01, scania.speedFactor());
        scania.getFlatbed().raiseFlatbed();
        assertEquals(0, scania.speedFactor());
    }

    @Test
    void testGetHudInfo() {
        Scania scania = new Scania();
        String[] info = scania.getHudInfo();
        assertEquals("Model: Scania", info[0]);
        assertEquals("Engine On: false", info[1]);
        assertEquals("Speed: 0km/h", info[2]);
        assertEquals("Nr of doors: 2", info[3]);
        assertEquals("Flatbed angle: 0.00", info[4]);
    }

    @Test
    void testGetController() {
        Scania scania = new Scania();
        assertNotEquals(scania.getController(), null);
    }

    @Test
    void testGetFlatbed() {
        Scania scania = new Scania();
        // TODO: Vet inte vad jag ska göra!
    }
}
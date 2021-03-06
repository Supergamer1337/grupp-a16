package labb1.del2.vehicles;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Saab95Test {

    @Test
    void testGetHudInfo() {
        Saab95 saab = new Saab95();
        String[] info = {
                "Model: Saab95",
                "Engine On: false",
                "Speed: 0km/h",
                "Nr of doors: 2",
                "Turbo active: false"
        };
        assertArrayEquals(info, saab.getHudInfo());
    }

    @Test
    void testSpeedFactor() {
        Saab95 saab = new Saab95();
        assertEquals(saab.getPower() * 0.01 * 1, saab.speedFactor());
        saab.toggleTurbo();
        assertEquals(saab.getPower() * 0.01 * 1.3, saab.speedFactor());
    }

    @Test
    void testGetTurboState() {
        Saab95 saab = new Saab95();
        assertFalse(saab.getTurboState());
    }

    @Test
    void testToggleTurbo() {
        Saab95 saab = new Saab95();
        saab.toggleTurbo();
        assertTrue(saab.getTurboState());
    }

    @Test
    void testGetController() {
        Saab95 saab = new Saab95();
        // TODO: Vet inte vad jag ska göra!
        assertNotEquals(saab.getController(), null);
    }
}
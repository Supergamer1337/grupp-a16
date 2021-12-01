package labb1.del2.vehicles;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScaniaTest {

    @Test
    void testSpeedFactor() {
        Scania scania = new Scania();
        assertEquals(scania.getPower() * 0.01, scania.speedFactor());
        scania.raiseFlatbed();
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
    public void testGetFlatbedAngle() {
        Scania scania = new Scania();
        assertEquals(0, scania.getFlatbedAngle());
    }

    @Test
    public void testRaiseFlatbed() {
        Scania scania = new Scania();
        double startVal = scania.getFlatbedAngle();
        scania.raiseFlatbed();
        assertTrue(startVal < scania.getFlatbedAngle());
    }

    @Test
    public void testLowerFlatbed() {
        Scania scania = new Scania();
        scania.raiseFlatbed();
        double startVal = scania.getFlatbedAngle();
        scania.lowerFlatbed();
        assertTrue(startVal > scania.getFlatbedAngle());
    }

    @Test
    public void testIsFlatbedLowered() {
        Scania scania = new Scania();
        assertTrue(scania.isFlatbedLowered());
        scania.raiseFlatbed();
        assertFalse(scania.isFlatbedLowered());
    }

    @Test
    public void testGetMaxFlatbedAngle() {
        Scania scania = new Scania();
        assertEquals(70, scania.getMaxFlatbedAngle());
    }
    @Test
    public void testGetMinFlatBedAngle() {
        Scania scania = new Scania();
        assertEquals(0, scania.getMinFlatbedAngle());
    }
}
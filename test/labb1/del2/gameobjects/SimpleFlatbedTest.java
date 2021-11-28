package labb1.del2.gameobjects;

import labb1.del2.vehicleparts.SimpleFlatbed;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SimpleFlatbedTest {
    @Test
    public void testisLowered() {
        SimpleFlatbed flatbed = new SimpleFlatbed();
        assertFalse(flatbed.isLowered());
    }

    @Test
    public void testRaiseFlatbed() {
        SimpleFlatbed flatbed = new SimpleFlatbed();
        flatbed.raiseFlatbed();
        assertFalse(flatbed.isLowered());
    }

    @Test
    public void testLowerFlatbed() {
        SimpleFlatbed flatbed = new SimpleFlatbed();
        flatbed.lowerFlatbed();
        assertTrue(flatbed.isLowered());
    }
}
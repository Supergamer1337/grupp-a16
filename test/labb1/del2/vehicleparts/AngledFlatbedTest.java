package labb1.del2.vehicleparts;

import labb1.del2.vehicleparts.flatbeds.AngledFlatbed;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AngledFlatbedTest {
    @Test
    public void testRaiseFlatbed() {
        AngledFlatbed flatbed = new AngledFlatbed();
        flatbed.raiseFlatbed();
        assertNotEquals(0, flatbed.getFlatbedAngle());
    }

    @Test
    public void testLowerFlatbed() {
        AngledFlatbed flatbed = new AngledFlatbed();
        flatbed.raiseFlatbed();
        flatbed.lowerFlatbed();
        assertEquals(0, flatbed.getFlatbedAngle());
    }

    @Test
    public void testRaiseFlatbedToMax() {
        AngledFlatbed flatbed = new AngledFlatbed();
        while(flatbed.getFlatbedAngle() < flatbed.getMaxAngle())
            flatbed.raiseFlatbed();
        assertEquals(flatbed.getMaxAngle(), flatbed.getFlatbedAngle());
    }

    @Test
    public void testLowerFlatbedToMin() {
        AngledFlatbed flatbed = new AngledFlatbed();
        while(flatbed.getFlatbedAngle() < 10)
            flatbed.raiseFlatbed();
        while(flatbed.getFlatbedAngle() > flatbed.getMinAngle())
            flatbed.lowerFlatbed();
        assertEquals(flatbed.getMinAngle(), flatbed.getFlatbedAngle());
    }

    @Test
    public void testIsLowered() {
        AngledFlatbed flatbed = new AngledFlatbed();
        assertTrue(flatbed.isLowered());
    }

    @Test
    public void testIsNotLowered() {
        AngledFlatbed flatbed = new AngledFlatbed();
        flatbed.raiseFlatbed();
        assertFalse(flatbed.isLowered());
    }
}

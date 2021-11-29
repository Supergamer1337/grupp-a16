package labb1.del2.vehicles;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Volvo240Test {

    @Test
    void getHudInfo() {
        Volvo240 volvo = new Volvo240();
        String[] info = volvo.getHudInfo();
        assertEquals("Model: Volvo240", info[0]);
        assertEquals("Engine On: false", info[1]);
        assertEquals("Speed: 0km/h", info[2]);
        assertEquals("Nr of doors: 4", info[3]);
        assertEquals("Trim Factor: 1.25", info[4]);
    }

    @Test
    void speedFactor() {
        Volvo240 volvo = new Volvo240();
        assertEquals(volvo.getPower() * 0.01 * volvo.getTrimFactor(), volvo.speedFactor());
    }
}
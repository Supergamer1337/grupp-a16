package labb1.del2.vehicles;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Volvo240Test {

    @Test
    void getHudInfo() {
        Volvo240 volvo = new Volvo240();
        String[] info = {
                "Model: Volvo240",
                "Engine On: false",
                "Speed: 0km/h",
                "Nr of doors: 4",
                "Trim Factor: 1.25"
        };
        assertArrayEquals(info, volvo.getHudInfo());
    }

    @Test
    void speedFactor() {
        Volvo240 volvo = new Volvo240();
        assertEquals(volvo.getPower() * 0.01 * volvo.getTrimFactor(), volvo.speedFactor());
    }
}
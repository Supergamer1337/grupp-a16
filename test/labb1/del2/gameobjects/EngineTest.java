package labb1.del2.gameobjects;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import labb1.del2.vehicleparts.Engine;

public class EngineTest {
    @Test
    public void testIsTurnedOn() {
        Engine engine = new Engine(100);
        assertFalse(engine.isTurnedOn());
    }

    @Test
    public void testTurnOn() {
        Engine engine = new Engine(100);
        engine.turnOn();
        assertTrue(engine.isTurnedOn());
    }

    @Test
    public void testTurnOff() {
        Engine engine = new Engine(100);
        engine.turnOn();
        engine.turnOff();
        assertFalse(engine.isTurnedOn());
    }

    @Test
    public void testEnginePower() {
        Engine engine = new Engine(100);
        assertEquals(100, engine.getPower());
    }

    @Test
    public void testEngineToggle() {
        Engine engine = new Engine(100);
        engine.toggleEngineOn();
        assertTrue(engine.isTurnedOn());
    }
}

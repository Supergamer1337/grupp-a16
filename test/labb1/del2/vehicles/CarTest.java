package labb1.del2.vehicles;

import labb1.del2.vehicleparts.Engine;
import labb1.del2.vehicles.Car;
import labb1.del2.vehicles.Saab95;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CarTest {
    @Test
    public void testIncrementSpeed() {
        Car car = new Saab95();
        car.getEngine().toggleEngineOn();
        car.incrementSpeed(1);
        assertEquals(car.getEngine().speedFactor(), car.getSpeed());
    }

    @Test
    public void testDecrementSpeed() {
        Car car = new Saab95();
        car.getEngine().toggleEngineOn();
        car.incrementSpeed(1);
        car.decrementSpeed(1);
        assertEquals(0, car.getSpeed());
    }

    @Test
    public void testGetHudInfo() {
        Car car = new Saab95();
        String[] info = car.getHudInfo();
        assertEquals("Model: Saab95", info[0]);
        assertEquals("Engine On: false", info[1]);
        assertEquals("Speed: 0km/h", info[2]);
        assertEquals("Nr of doors: 2", info[3]);
    }

    @Test
    public void testGetNrOfDoors() {
        Car car = new Saab95();
        assertEquals(2, car.getNrOfDoors());
    }

    @Test
    public void testGetEngine() {
        Car car = new Saab95();
        Engine engine = car.getEngine();
        assertNotEquals(engine, new Engine(5));
        assertEquals(engine, car.getEngine());
        assertEquals(engine.hashCode(), car.getEngine().hashCode());
        assertNotEquals(engine, new Engine(10));
    }

    @Test
    public void testGetController() {
        Car car = new Saab95();

    }
}

package labb1.del2.vehicles;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VehicleTest {
    @Test
    public void testAccelerate() {
        Car car = new Saab95();
        car.turnOn();
        car.accelerate(1);
        assertEquals(car.speedFactor(), car.getSpeed());
    }

    @Test
    public void testDecelerate() {
        Car car = new Saab95();
        car.turnOn();
        car.accelerate(1);
        car.decelerate(0.5);
        assertNotEquals(0, car.getSpeed());
        car.decelerate(0.5);
        assertEquals(0, car.getSpeed());
    }

    @Test
    public void testMove() {
        Car car = new Saab95();
        car.turnOn();
        car.move(1);
        assertPos(car, 0, 0);

        car.accelerate(1);
        car.move(1);
        assertPos(car, car.speedFactor(), 0);
    }

    @Test
    public void testTurnRight() {
        Car car = new Saab95();
        car.turnOn();
        car.accelerate(1);
        car.turnRight(1);
        assertEquals(car.getRotation(), car.getTurnSpeed());

    }

    @Test
    public void testTurnLeft() {
        Car car = new Saab95();
        car.turnOn();
        car.accelerate(1);
        car.turnLeft(1);
        assertEquals(car.getRotation(), - car.getTurnSpeed());
    }

    public void assertPos(Car car, double expectedX, double expectedY) {
        assertEquals(expectedX, car.getPosX());
        assertEquals(expectedY, car.getPosY());
    }
}

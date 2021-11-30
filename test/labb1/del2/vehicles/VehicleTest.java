package labb1.del2.vehicles;

import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Test;
import javafx.scene.paint.Color;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleTest {
    @Test
    public void testAccelerate() {
        Car car = new Saab95();
        car.turnOn();
        car.accelerate(1);
        assertEquals(car.speedFactor(), car.getSpeed());
        try { car.accelerate(2);}
        catch (Exception e) { System.out.println(e); }
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
        try { car.decelerate(2);}
        catch (Exception e) { System.out.println(e); }
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

    @Test
    public void testGetColor() {
        Car car = new Saab95();
        assertEquals(Color.RED, car.getColor());
    }

    @Test
    public void testGetModelName() {
        Car car = new Saab95();
        assertEquals("Saab95", car.getModelName());
    }

    @Test
    public void testGetRect() {
        Car car = new Saab95();
        assertTrue(car.getRect() instanceof Rectangle);
    }

    @Test
    public void testGetPosX() {
        Car car = new Saab95();
        assertEquals(0, car.getPosX());
    }

    @Test
    public void testGetPosY() {
        Car car = new Saab95();
        assertEquals(0, car.getPosY());
    }

    @Test
    public void testGetWidth() {
        Car car = new Saab95();
        assertEquals(100.20, car.getWidth());
    }

    @Test
    public void testGetHeight() {
        Car car = new Saab95();
        assertEquals(50, car.getHeight());
    }

    @Test
    public void testGetSpeed() {
        Car car = new Saab95();
        car.turnOn();
        car.accelerate(1);
        assertEquals(1.25, car.getSpeed());
    }

    @Test
    public void testSetSpeed() {
        Car car = new Saab95();
        car.turnOn();
        car.setSpeed(3);
        assertEquals(3, car.getSpeed());
    }

    @Test
    public void testSetColor() {
        Car car = new Saab95();
        Car car2 = new Saab95();
        car.setColor(Color.BLUE);
        assertNotEquals(car.getColor(), car2.getColor());
    }

    @Test
    public void testGetTurnSpeed() {
        Car car = new Saab95();
        assertEquals(100 * Math.PI / car.getWidth(), car.getTurnSpeed());
    }

    @Test
    public void testGetRotation() {
        Car car = new Saab95();
        assertEquals(0, car.getRotation());
        car.turnOn();
        car.accelerate(1);
        car.turnRight(1);
        assertTrue(car.getRotation() > 0);
    }

    @Test
    public void testSetRotation() {
        Car car = new Saab95();
        car.setRotation(10);
        assertEquals(10, car.getRotation());
    }

    private void assertPos(Car car, double expectedX, double expectedY) {
        assertEquals(expectedX, car.getPosX());
        assertEquals(expectedY, car.getPosY());
    }
}

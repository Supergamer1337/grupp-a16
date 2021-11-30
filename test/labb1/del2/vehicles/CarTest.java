package labb1.del2.vehicles;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import labb1.del2.vehicleparts.Engine;
import labb1.del2.vehicles.Car;
import labb1.del2.vehicles.Saab95;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarTest {

    @Test
    public void testIncrementSpeed() {
        Car car = new Saab95();
        try { car.incrementSpeed(1); }
        catch (Exception e) {
            System.out.println(e);
        }
        car.turnOn();
        car.incrementSpeed(1);
        assertEquals(car.speedFactor(), car.getSpeed());
    }

    @Test
    public void testDecrementSpeed() {
        Car car = new Saab95();
        car.toggleOn();
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
    public void testGetEnginePower() {
        Car car = new Saab95();
        assertEquals(125, car.getPower());
    }

    @Test
    public void testIsEngineOn() {
        Car car = new Saab95();
        assertFalse(car.isTurnedOn());
    }

    @Test
    public void testTurnOn() {
        Car car = new Saab95();
        car.turnOn();
        assertTrue(car.isTurnedOn());
    }

    @Test
    public void testTurnOff() {
        Car car = new Saab95();
        car.turnOff();
        assertFalse(car.isTurnedOn());
    }

    @Test
    public void testToggleEngineOn() {
        Car car = new Saab95();
        car.toggleOn();
        assertTrue(car.isTurnedOn());
        car.toggleOn();
        assertFalse(car.isTurnedOn());
    }

    @Test
    public void testGetController() {
        Volvo240 car = new Volvo240();
        assertNotEquals(null, car.getController());
    }
}

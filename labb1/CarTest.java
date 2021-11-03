import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

public class CarTest {
    @Test
    public void myTest() {
        Car[] cars = {
                new Saab95(),
                new Volvo240()
        };
        testCars(cars);
    }

    private void testCars(Car[] cars) {
        for (Car car : cars) {
            testSpeed(car);
            testEngine(car);
            testSetColor(car);
            testDirection(car);
            testMove(car);
        }
        testSaab95();
        testVolvo240();
    }

    private void testSaab95() {
        Saab95 saab = new Saab95();
        testNrDoors(saab, 2);
        testColor(saab, Color.RED);
        testEnginePower(saab, 125.0);
        testTurbo(saab);
    }

    private void testVolvo240() {
        Volvo240 volvo = new Volvo240();
        testNrDoors(volvo, 4);
        testColor(volvo, Color.BLACK);
        testEnginePower(volvo, 100.0);
    }

    protected void testNrDoors(Car car, int nrDoors) {
        assertEquals(car.getNrDoors(), nrDoors);
    }

    private void testColor(Car car, Color color) {
        assertEquals(car.getColor(), color);
    }

    private void testEnginePower(Car car, double enginePower) {
        assertEquals(car.getEnginePower(), enginePower);
    }

    private void testSpeed(Car car) {
        assertEquals(car.getCurrentSpeed(), 0);
        car.gas(0.5);
        assertEquals(car.speedFactor() / 2, car.getCurrentSpeed());
        car.gas(0.5);
        assertEquals(car.speedFactor(), car.getCurrentSpeed());
        for (int i = 0; i < 101; i++)
            car.gas(1);
        assertEquals(car.getEnginePower(), car.getCurrentSpeed());

        for (int i = 0; i < 100; i++)
            car.brake(1);
        assertEquals(0, car.getCurrentSpeed());

        car.gas(1);
        car.brake(0.5);
        assertEquals(car.speedFactor()/2, car.getCurrentSpeed());
        car.brake(1);
        assertEquals(0, car.getCurrentSpeed());
    }

    private void testTurbo(Saab95 car) {
        double oldSpeedFactor = car.speedFactor();
        car.setTurboOn();
        car.gas(1);
        assertTrue(car.getCurrentSpeed() > oldSpeedFactor);
        car.setTurboOff();
    }

    private void testEngine(Car car) {
        car.startEngine();
        assertEquals(0.1, car.getCurrentSpeed());
        car.stopEngine();
        assertEquals(0, car.getCurrentSpeed());
    }

    private void testSetColor(Car car) {
        car.setColor(Color.CYAN);
        assertEquals(car.getColor(), Color.CYAN);
    }

    private void testDirection(Car car) {
        Car.Direction[] dirValues = Car.Direction.values();

        for (Car.Direction dirValue : dirValues) {
            assertEquals(dirValue, car.getCurrentDirection());
            car.turnRight();
        }

        for (Car.Direction dirValue : dirValues) {
            assertEquals(dirValue, car.getCurrentDirection());
            car.turnLeft();
        }
    }

    private void testTurn() {
        // TODO: Implement testTurn
    }

    private void testMove(Car car) {
        car.move();
        assertEquals(car.posX, 0);
        assertEquals(car.posY, 0);
        car.gas(1);
        car.move();
        assertEquals(car.posX, 0);
        assertEquals(car.posY, (int)(car.getCurrentSpeed()));
        car.turnRight();
        car.move();
        assertEquals(car.posX, (int)(car.getCurrentSpeed()));
        assertEquals(car.posY, (int)(car.getCurrentSpeed()));

    }
}
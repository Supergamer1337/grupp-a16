package labb1.del1;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class CarTest {
    @Test
    public void myTest() {
        testCars();
        testSaab95();
        testVolvo240();
    }

    @Test
    public void testCars() {
        Car[] cars = {
                new Saab95(),
                new Volvo240()
        };
        for (Car car : cars) {
            testSetColor(car);
            testEngine(car);
            testSpeed(car);
            testDirection(car);
            testMove(car);
        }
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

        for (int i = dirValues.length - 1; i > -1; i--) {
            car.turnLeft();
            assertEquals(dirValues[i], car.getCurrentDirection());
        }
    }

    private void testMove(Car car) {
        Car.Direction[] dirValues = Car.Direction.values();
        car.gas(1);
        int[][] pos = {
                {0, -(int)car.speedFactor()},
                {(int)car.speedFactor(), -(int)car.speedFactor()},
                {(int)car.speedFactor(), 0},
                {0, 0}
        };
        for (int i = 0; i < dirValues.length; i++) {
            car.move();
            car.turnRight();
            assertTrue(pos[i][0] == car.getPosX() && pos[i][1] == car.getPosY());
        }
    }
}
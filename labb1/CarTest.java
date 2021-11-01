import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class CarTest {
    @Test
    public void myTest() {
        // Saab95 Tests
        Saab95 saab = new Saab95();
        testNrDoors(saab, 2);
        testColor(saab, Color.RED);
        testEnginePower(saab, 125.0);
        testSpeed(saab);

        // Volvo240 Tests
        Volvo240 volvo = new Volvo240();
        testNrDoors(volvo, 4);
        testColor(volvo, Color.BLACK);
        testEnginePower(volvo, 100.0);
        testSpeed(volvo);
    }

    private void testNrDoors(Car car, int nrDoors) {
        assertEquals(car.getNrDoors(), nrDoors);
    }

    private void testColor(Car car, Color color) {
        assertEquals(car.getColor(), color);
    }

    private void testEnginePower(Car car, double enginePower) {
        assertEquals(car.getEnginePower(), enginePower);
    }

    private void testSpeed(Car car) {
        // TODO: Add more testing
        assertEquals(car.getCurrentSpeed(), 0);
    }
}

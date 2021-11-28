package labb1.del2.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import labb1.del2.utils.Vector2D;

public class Vector2DTest {
    @Test
    public void testGetX() {
        Vector2D vector = new Vector2D(2, 1);
        assertEquals(2.0, vector.getX());
    }

    @Test
    public void testGetY() {
        Vector2D vector = new Vector2D(2, 1);
        assertEquals(1.0, vector.getY());
    }

    @Test
    public void testAddVectors() {
        Vector2D vector = new Vector2D(2, 1);
        Vector2D vector2 = new Vector2D(3, 2);
        Vector2D result = vector.add(vector2);
        assertEquals(5.0, result.getX());
        assertEquals(3.0, result.getY());
    }

    @Test
    public void testAddXY() {
        Vector2D vector = new Vector2D(2, 1);
        Vector2D result = vector.add(2, 3);
        assertEquals(4.0, result.getX());
        assertEquals(4.0, result.getY());
    }

    @Test
    public void testCopy() {
        Vector2D vector = new Vector2D(2, 1);
        Vector2D normalizedVector = vector.copy();
        assertEquals(2.0, normalizedVector.getX());
        assertEquals(1.0, normalizedVector.getY());
    }

    @Test
    public void testGetNormalized() {
        Vector2D vector = new Vector2D(2, 1);
        Vector2D result = vector.getNormalized();
        assertEquals(0.4, result.getX());
        assertEquals(0.2, result.getY());
    }

    @Test
    public void testAngleToVector() {
        Vector2D angleVector = Vector2D.angleToVector2D(Math.PI / 2);
        double margin = 0.00000001;
        assertTrue(angleVector.getX() > -margin && angleVector.getX() < margin);
        assertTrue(angleVector.getY() > 1 - margin && angleVector.getY() < 1 + margin);
    }

    @Test
    public void testZeroVector() {
        Vector2D v = new Vector2D();
        assertEquals(0, v.getX());
        assertEquals(0, v.getY());
    }
}

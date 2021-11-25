package labb1.del2.helpers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import labb1.del2.helpers.Vector2D;

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
    public void addVectors() {
        Vector2D vector = new Vector2D(2, 1);
        Vector2D vector2 = new Vector2D(3, 2);
        Vector2D result = vector.add(vector2);
        assertEquals(5.0, result.getX());
        assertEquals(3.0, result.getY());
    }

    @Test
    public void addXY() {
        Vector2D vector = new Vector2D(2, 1);
        Vector2D result = vector.add(2, 3);
        assertEquals(4.0, result.getX());
        assertEquals(4.0, result.getY());
    }
}

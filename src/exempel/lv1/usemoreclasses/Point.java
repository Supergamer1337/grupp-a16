package exempel.lv1.usemoreclasses;

import java.util.Objects;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/*

  A class for points in 3D. Should be immutable

  NOTE: No IO here, this is just the logical concept.

  To test run Ex2TestPointTriangle

*/
public class Point {
    int x,y, length;

    public Point(int x, int y, int length) {
        this.x = x;
        this.y = y;
        this.length = length;
    }

    public Point(Point point) {
        x = point.x;
        y = point.y;
        length = point.length;
    }

    public double distance(Point point) {
        int dX = x-point.x;
        int dY = y-point.y;
        return sqrt(dX * dX + dY * dY);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Point p = (Point) obj;
        return p.x == x && p.y == y && p.length == length;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, length);
    }
}



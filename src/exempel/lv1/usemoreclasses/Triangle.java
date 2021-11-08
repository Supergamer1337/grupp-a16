package exempel.lv1.usemoreclasses;

import java.util.Arrays;
import java.util.Objects;

import static java.lang.Math.sqrt;

/*
      A class for a Triangle in 3D
      Should use Point class and Herons formula

      NOTE: No IO here, this is just the logical concept.

      To test run Ex2TestPointTriangle

*/
public class Triangle {
    Point[] points;

    public Triangle(Point point1, Point point2, Point point3) {
        points = new Point[]{point1, point2, point3};
    }

    public double area() {
        double a = points[0].distance(points[1]), b = points[1].distance(points[2]), c = points[2].distance(points[0]);
        double s = (a + b + c) / 2;
        double area = sqrt(s * (s - a) * (s - b) * (s - c));
        System.out.println("Area is " + area);
        return area;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Triangle tri = (Triangle) obj;
        for (int i = 0; i < 3; i++) {
            if (!tri.points[i].equals(points[i])) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(points[0], points[1], points[2]);
    }
}


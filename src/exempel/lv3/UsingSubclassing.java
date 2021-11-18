package exempel.lv3;

import java.awt.*;

public class UsingSubclassing {

    class Polygon {
        public void paint(Graphics g) {
            // Code for painting a polygon goes here
        }

        public boolean overlaps(Polygon p) {
            // Code for deciding whether two polygons overlap goes here
            return false;
        }
    }

    class Rectangle extends Polygon {
        public boolean overlaps(Rectangle r) {
            // Code for deciding whether two rectangles overlap goes here
            return false;
        }
    }
}
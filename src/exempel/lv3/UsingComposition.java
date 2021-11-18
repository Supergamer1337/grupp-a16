package exempel.lv3;

import java.awt.*;

public class UsingComposition {
    class Rectangle {
        private PolygonHelper helper;

        public void paint(Graphics g) {
            helper.paint(g);
        }

        public boolean overlaps(Rectangle r) {
            // Code for deciding if two rectangles overlap goes here
            return false;
        }
    }

    /* In this class we put the code that Square, Rectangle, Triangle, etc. are all
    going to use. */
    class PolygonHelper {
        public void paint(Graphics g) {
            // Code for painting a polygon goes here
        }
    }
}


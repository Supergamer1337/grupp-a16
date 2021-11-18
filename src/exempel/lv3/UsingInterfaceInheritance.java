package exempel.lv3;

import java.awt.*;

public class UsingInterfaceInheritance {
    interface IPolygon {
        void paint(Graphics g);

        boolean overlaps(IPolygon p);
    }

    class Rectangle implements IPolygon {

        @Override
        public void paint(Graphics g) {
            // Code for painting a rectangle goes here
        }

        @Override
        public boolean overlaps(IPolygon p) {
            // Code for deciding if a rectangle and a polygon overlap goes here
            return false;
        }

        public boolean overlaps(Rectangle r) {
            // Code for deciding if two rectangles overlap goes here
            return false;
        }
    }
}
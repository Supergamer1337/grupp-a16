package exempel.lv1.drawpolygons.polygons;

import java.awt.*;

public class Triangle extends Polygon {

    private final static int radius = 10;

    public Triangle(Point pos) {
        super(pos);
    }

    @Override
    public void paint(Graphics g) {
        g.drawLine(getPos().x, getPos().y-radius, getPos().x-radius,getPos().y+radius);
        g.drawLine(getPos().x-radius, getPos().y+radius, getPos().x+radius, getPos().y+radius);
        g.drawLine(getPos().x+radius, getPos().y+radius, getPos().x,getPos().y-radius);
    }
}

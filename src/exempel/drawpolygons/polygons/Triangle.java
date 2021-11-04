package exempel.drawpolygons.polygons;

import java.awt.*;

public class Triangle extends Polygon {

    private static int radius = 10;

    public Triangle(Point pos) {
        super(pos);
    }

    @Override
    public void Draw(Graphics g) {
        g.drawLine(pos.x, pos.y-radius, pos.x-radius,pos.y+radius);
        g.drawLine(pos.x-radius, pos.y+radius, pos.x+radius, pos.y+radius);
        g.drawLine(pos.x+radius, pos.y+radius, pos.x,pos.y-radius);
    }
}

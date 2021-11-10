package exempel.lv1.drawpolygons.polygons;

import java.awt.*;

public class Rectangle extends Polygon {

    private static Point dimensions = new Point(40, 20);

    public Rectangle(Point pos) {
        super(pos);
    }

    @Override
    public void paint(Graphics g) {
        g.drawRect(getPos().x - dimensions.x / 2, getPos().y - dimensions.y / 2, dimensions.x, dimensions.y);
    }
}

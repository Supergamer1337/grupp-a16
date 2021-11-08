package exempel.lv1.drawpolygons.polygons;

import java.awt.*;

public class Rectangle extends Polygon {

    private static Point dimensions = new Point(40, 20);

    public Rectangle(Point pos) {
        super(pos);
    }

    @Override
    public void draw(Graphics g) {
        g.drawRect(pos.x - dimensions.x / 2, pos.y - dimensions.y / 2, dimensions.x, dimensions.y);
    }
}

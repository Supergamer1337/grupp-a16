package exempel.lv1.drawpolygons.polygons;

import java.awt.*;

public class Square extends Polygon {
    private final static int width = 10;

    public Square(Point pos) {
        super(pos);
    }

    @Override
    public void paint(Graphics g) {
        g.drawRect(getPos().x - width, getPos().y - width, width * 2, width * 2);
    }
}

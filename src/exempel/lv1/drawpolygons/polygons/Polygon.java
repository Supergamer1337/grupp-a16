package exempel.lv1.drawpolygons.polygons;

import java.awt.*;

public abstract class Polygon {
    Point pos;

    public Polygon(Point pos) {
        this.pos = pos;

    }

    public abstract void draw(Graphics g);
}

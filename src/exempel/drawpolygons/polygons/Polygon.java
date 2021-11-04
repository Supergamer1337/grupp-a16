package exempel.drawpolygons.polygons;

import java.awt.*;

public abstract class Polygon {
    Point pos;

    public Polygon(Point pos) {
        this.pos = pos;

    }

    public abstract void Draw(Graphics g);
}

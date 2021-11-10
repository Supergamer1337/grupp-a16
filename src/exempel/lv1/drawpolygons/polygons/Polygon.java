package exempel.lv1.drawpolygons.polygons;

import java.awt.*;

public abstract class Polygon {
    private Point pos;

    public Polygon(Point pos) {
        this.pos = pos;
    }

    public Polygon(int x, int y) {
        this(new Point(x, y));
    }

    public abstract void paint(Graphics g);

    public Point getPos() { return pos; }

}

package exempel.drawpolygons;

import exempel.drawpolygons.polygons.*;
import exempel.drawpolygons.polygons.Polygon;
import exempel.drawpolygons.polygons.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class DrawPolygons extends Component {

    private ArrayList<exempel.drawpolygons.polygons.Polygon> polygons;

    public DrawPolygons() {
        // New
        polygons = new ArrayList<Polygon>();
        polygons.add(new Square(new Point(100, 100)));
        polygons.add(new Triangle(new Point(150, 150)));
        polygons.add(new Rectangle(new Point(100, 200)));
    }

    @Override
    public void paint(Graphics g) {
        for (Polygon polygon : polygons) {
            polygon.Draw(g);
        }
    }

    public static void main(String args[]) {
        JFrame frame = new JFrame();
        DrawPolygons polygons = new DrawPolygons();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(30,30,300,300);
        frame.getContentPane().add(polygons);
        frame.setVisible(true);
    }
}

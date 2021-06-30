package de.oshgnacknak.gruphi;

import java.awt.*;
import java.awt.geom.Point2D;

class Node {

    public static final double RADIUS = 10;

    public static final Color COLOR = Color.WHITE;

    Point2D.Double pos;

    double radius;

    Color color;

    Node(double x, double y) {
       this.pos = new Point2D.Double(x, y);
       this.radius = RADIUS;
       this.color = COLOR;
    }

    boolean inside(double x, double y) {
        return pos.distance(x, y) <= radius;
    }
}

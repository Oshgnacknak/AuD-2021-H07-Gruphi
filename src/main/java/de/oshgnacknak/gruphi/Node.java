package de.oshgnacknak.gruphi;

import java.awt.*;
import java.awt.geom.Point2D;

class Node {

    public static final double RADIUS = 12;

    public static final Color COLOR = Color.WHITE;

    Vector pos;

    double radius;

    Color color;

    Node(double x, double y) {
       this.pos = new Vector(x, y);
       this.radius = RADIUS;
       this.color = COLOR;
    }

    boolean inside(double x, double y) {
        return pos.dist(x, y) <= radius;
    }
}

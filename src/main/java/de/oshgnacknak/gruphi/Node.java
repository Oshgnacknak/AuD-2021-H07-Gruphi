package de.oshgnacknak.gruphi;

import java.awt.*;
import java.awt.geom.Point2D;

class Node {

    public static final double RADIUS = 12;

    public static final Color COLOR = Color.WHITE;

    Vector pos;

    double radius;

    Color color;

    Node(Vector pos) {
       this.pos = pos;
       this.radius = RADIUS;
       this.color = COLOR;
    }

    boolean inside(Vector v) {
        return pos.dist(v) <= radius;
    }
}

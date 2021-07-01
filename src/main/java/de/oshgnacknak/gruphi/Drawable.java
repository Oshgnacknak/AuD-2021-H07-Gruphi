package de.oshgnacknak.gruphi;

import java.awt.*;

public interface Drawable {

    void fill(Color c);

    void strokeWeight(double w);

    void rect(double x, double y, double w, double h);

    void ellipse(double x, double y, double w, double h);

    void triangle(double x1, double y1, double x2, double y2, double x3, double y3);

    void line(double x1, double y1, double x2, double y2);

    void rotated(double theta, double x, double y, Runnable r);

    void translated(double x, double y, Runnable r);

    void scaled(double scale, Runnable r);
}

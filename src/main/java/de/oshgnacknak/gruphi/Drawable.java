package de.oshgnacknak.gruphi;

import java.awt.*;

public interface Drawable {

    void fill(Color c);

    void strokeWeight(double w);

    void rect(double x, double y, double w, double h);

    void ellipse(double x, double y, double w, double h);

    void line(double x1, double y1, double x2, double y2);
}

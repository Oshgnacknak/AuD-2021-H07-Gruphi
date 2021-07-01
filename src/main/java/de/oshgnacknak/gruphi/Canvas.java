package de.oshgnacknak.gruphi;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class Canvas extends JPanel {

    private final Consumer<Drawable> draw;

    public Canvas(Consumer<Drawable> draw) {
        this.draw = draw;
    }

    @Override
    public void paint(Graphics graphics) {
        var g = (Graphics2D) graphics;

        draw.accept(new Drawable() {

            @Override
            public void fill(Color c) {
                g.setColor(c);
            }

            @Override
            public void strokeWeight(double w) {
                g.setStroke(new BasicStroke((int) w));
            }

            @Override
            public void rect(double x, double y, double w, double h) {
                g.fillRect(round(x), round(y), round(w), round(h));
            }

            @Override
            public void ellipse(double x, double y, double w, double h) {
                g.fillOval(round(x - w/2), round(y - h/2), round(w), round(h));
            }

            @Override
            public void triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
                int[] xs = {round(x1), round(x2), round(x3)};
                int[] ys = {round(y1), round(y2), round(y3)};
                g.fillPolygon(xs, ys, 3);
            }

            @Override
            public void line(double x1, double y1, double x2, double y2) {
                g.drawLine(round(x1), round(y1), round(x2), round(y2));
            }

            @Override
            public void rotated(double theta, double x, double y, Runnable r) {
                g.rotate(theta, x, y);
                r.run();
                g.rotate(-theta, x, y);
            }

            @Override
            public void translated(double x, double y, Runnable r) {
                g.translate(x, y);
                r.run();
                g.translate(-x, -y);
            }

            @Override
            public void scaled(double scale, Runnable r) {
                g.scale(scale, scale);
                r.run();
                g.scale(1/scale, 1/scale);
            }
        });
    }

    private int round(double d) {
        return (int) Math.round(d);
    }
}

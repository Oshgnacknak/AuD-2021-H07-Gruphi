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
                g.fillRect((int) x, (int) y, (int) w, (int) h);
            }

            @Override
            public void ellipse(double x, double y, double w, double h) {
                g.fillOval((int) (x - w/2), (int) (y - h/2), (int) w, (int) h);
            }

            @Override
            public void line(double x1, double y1, double x2, double y2) {
                g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
            }
        });
    }
}

package de.oshgnacknak.gruphi;

import java.awt.event.*;

public class PanningAndZooming implements MouseListener, MouseWheelListener, MouseMotionListener {

    private final Vector offset;

    private double scale;

    private final GruphiFrame frame;

    private Vector prevMouse = null;

    public PanningAndZooming(GruphiFrame frame) {
        this.offset = new Vector(0, 0);
        this.scale = 1;
        this.frame = frame;
    }

    public void pan(Vector v) {
        offset.add(v.copy().div(scale));
    }

    public void zoom(double x, double y, double s) {
        var before = screenToWorld(x, y);
        scale *= s;
        var after = screenToWorld(x, y);

        var d = before
            .sub(after)
            .mul(scale);
        offset.sub(d);
    }

    public void zoom(double s) {
        zoom(frame.getWidth()/2.0, frame.getHeight()/2.0, s);
    }

    public Vector screenToWorld(double x, double y) {
        return new Vector(x, y)
            .sub(offset)
            .div(scale);
    }

    public void draw(Drawable d, Runnable r) {
        d.translated(offset.x, offset.y, () ->
            d.scaled(scale, r));
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON2) {
            prevMouse = new Vector(e.getX(), e.getY());
        }

        var v = screenToWorld(e.getX(), e.getY());
        frame.onMousePressed(e.getButton(), v);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON2) {
            prevMouse = null;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        var s = e.getWheelRotation() < 0 ? 1.1 : 0.9;
        zoom(e.getX(), e.getY(), s);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (prevMouse != null) {
            var v = new Vector(e.getX(), e.getY());
            offset.sub(prevMouse.sub(v));
            prevMouse = v;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {}
}

package de.oshgnacknak.gruphi;

public class Vector {

    double x, y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    Vector copy() {
        return new Vector(x, y);
    }

    Vector add(Vector v) {
        x += v.x;
        y += v.y;
        return this;
    }

    public Vector sub(Vector v) {
        x -= v.x;
        y -= v.y;
        return this;
    }

    Vector mul(double d) {
        x *= d;
        y *= d;
        return this;
    }

    double dot(Vector v) {
        return x*v.x + y*v.y;
    }

    double mag() {
        return Math.sqrt(dot(this));
    }

    Vector norm() {
        return mul(1 / mag());
    }

    Vector setMag(double mag) {
        return mul(mag/this.mag());
    }

    double dist(Vector v) {
        return dist(v.x, v.y);
    }

    double dist(double x, double y) {
        var dx = this.x - x;
        var dy = this.y - y;
        return Math.sqrt(dx*dx + dy*dy);
    }


    double angle() {
        return Math.atan2(y, x);
    }
}

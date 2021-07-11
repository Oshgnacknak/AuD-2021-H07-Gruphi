package de.oshgnacknak.gruphi;

import h07.algebra.Monoid;

public class DoubleAddition implements Monoid<Double> {

    @Override
    public Double zero() {
        return 0.0;
    }

    @Override
    public Double add(Double a, Double b) {
        return a + b;
    }
}

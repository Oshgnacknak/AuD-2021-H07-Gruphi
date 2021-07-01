package de.oshgnacknak.gruphi;

import h07.graph.DirectedGraphFactory;

import java.util.function.BiPredicate;

public interface Gruphi {

    long getFrameDelay();

    double getVelocity();

    double getGridSpacing();

    BiPredicate<Node, Node> getNeighbourPredicate();

    DirectedGraphFactory<Node, Double> getDirectedGraphFactory();
}

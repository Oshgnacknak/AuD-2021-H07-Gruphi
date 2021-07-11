package de.oshgnacknak.gruphi;

import h07.graph.DirectedGraphFactory;

import java.util.function.BiPredicate;

public class GruphiBuilder {
    private Long frameDelay;
    private Double velocity;
    private Double gridSpacing;
    private BiPredicate<Node, Node> neighbourPredicate;
    private DirectedGraphFactory<Node, Double> directedGraphFactory;

    public GruphiBuilder setFrameDelay(Long frameDelay) {
        this.frameDelay = frameDelay;
        return this;
    }

    public GruphiBuilder setVelocity(Double velocity) {
        this.velocity = velocity;
        return this;
    }

    public GruphiBuilder setGridSpacing(Double gridSpacing) {
        this.gridSpacing = gridSpacing;
        return this;
    }

    public GruphiBuilder setNeighbourPredicate(BiPredicate<Node, Node> neighbourPredicate) {
        this.neighbourPredicate = neighbourPredicate;
        return this;
    }

    public GruphiBuilder setDirectedGraphFactory(DirectedGraphFactory<Node, Double> directedGraphFactory) {
        this.directedGraphFactory = directedGraphFactory;
        return this;
    }

    public GruphiImpl createGruphi() {
        return new GruphiImpl(frameDelay, velocity, gridSpacing, neighbourPredicate, directedGraphFactory);
    }
}
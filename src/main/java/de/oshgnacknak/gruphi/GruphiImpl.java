package de.oshgnacknak.gruphi;

import h07.graph.DirectedGraphFactory;

import java.util.Objects;
import java.util.function.BiPredicate;

public class GruphiImpl implements Gruphi {

    private static final long DEFAULT_FRAME_DELAY = 1000 / 60;
    private static final double DEFAULT_VELOCITY = 5;
    private static final double DEFAULT_GRID_SPACING = 50;

    private final long frameDelay;

    private final double velocity;

    private final double gridSpacing;

    private final BiPredicate<Node, Node> neighbourPredicate;

    private final DirectedGraphFactory<Node, Double> directedGraphFactory;

    public GruphiImpl(Long frameDelay,
                      Double velocity,
                      Double gridSpacing,
                      BiPredicate<Node, Node> neighbourPredicate,
                      DirectedGraphFactory<Node, Double> directedGraphFactory) {
        this.frameDelay = frameDelay == null ? DEFAULT_FRAME_DELAY : frameDelay;
        this.velocity = velocity == null ? DEFAULT_VELOCITY : velocity;
        this.gridSpacing = gridSpacing == null ? DEFAULT_GRID_SPACING : gridSpacing;
        this.neighbourPredicate = neighbourPredicate == null ? this::defaultAreNeighbours : neighbourPredicate;
        this.directedGraphFactory = Objects.requireNonNull(directedGraphFactory, "A directedGraphFactory must be set");
    }

    private boolean defaultAreNeighbours(Node a, Node b) {
        return a.pos.dist(b.pos) <= gridSpacing;
    }

    @Override
    public long getFrameDelay() {
        return frameDelay;
    }

    @Override
    public double getVelocity() {
        return velocity;
    }

    @Override
    public double getGridSpacing() {
        return gridSpacing;
    }

    @Override
    public BiPredicate<Node, Node> getNeighbourPredicate() {
        return neighbourPredicate;
    }

    @Override
    public DirectedGraphFactory<Node, Double> getDirectedGraphFactory() {
        return directedGraphFactory;
    }
}

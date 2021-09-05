package de.oshgnacknak.gruphi;

import h07.algorithm.MinimumSpanningForestAlgorithm;
import h07.algorithm.ShortestPathsAlgorithm;
import h07.graph.DirectedGraphFactory;

import java.util.function.BiPredicate;

public interface Gruphi {

    /**
     * @return Delay between draw calls
     */
    long getFrameDelay();

    /**
     * @return Velocity to move camera and selected node with
     */
    double getVelocity();

    /**
     * @return Horizontal and vertical spacing between nodes whilst generating a grid
     */
    double getGridSpacing();

    /**
     * @return A {@link BiPredicate} that returns <code>true</code>
     * iff a pair of nodes should be considered neighbours
     * by the {@link MazeGenerator}.
     */
    BiPredicate<Node, Node> getNeighbourPredicate();

    /**
     * @return A {@link DirectedGraphFactory} to create the initial {@link h07.graph.DirectedGraph}
     */
    DirectedGraphFactory<Node, Double> getDirectedGraphFactory();

    /**
     * @return A {@link ShortestPathsAlgorithm} for the path finding
     */
    ShortestPathsAlgorithm<Node, Double> getShortestPathsAlgorithm();

    /**
     * @return A {@link MinimumSpanningForestAlgorithm} for the spanning tree calculation
     */
    MinimumSpanningForestAlgorithm<Node, Double> getMinimumSpanningForestAlgorithm();
}

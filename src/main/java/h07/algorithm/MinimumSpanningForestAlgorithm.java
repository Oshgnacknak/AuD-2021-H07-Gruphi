package h07.algorithm;

import h07.graph.DirectedGraph;
import h07.graph.DirectedGraphFactory;

import java.util.Comparator;

public interface MinimumSpanningForestAlgorithm<V, A> {

    DirectedGraph<V, A> minimumSpanningForest(DirectedGraph<V, A> graph, Comparator<? super A> comparator, DirectedGraphFactory<V, A> factory);
}

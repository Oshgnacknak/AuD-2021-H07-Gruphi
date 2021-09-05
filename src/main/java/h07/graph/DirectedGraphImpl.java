package h07.graph;

import java.util.*;

class DirectedGraphImpl<V, A> implements DirectedGraph<V, A> {

    private final Map<V, Map<V, A>> theGraph;

    DirectedGraphImpl() {
        theGraph = new HashMap<>();
    }

    @Override
    public Collection<V> getAllNodes() {
        return Set.copyOf(theGraph.keySet());
    }

    @Override
    public Collection<V> getChildrenForNode(V node) {
        return Set.copyOf(getEdges(node).keySet());
    }

    private Map<V, A> getEdges(V node) {
        var edges = theGraph.get(Objects.requireNonNull(node));
        if (edges == null) {
            throw new NoSuchElementException("Node not in graph: " + node);
        }
        return edges;
    }

    @Override
    public A getArcWeightBetween(V from, V to) {
        checkToNode(to);

        var arc = getEdges(from).get(to);
        if (arc == null) {
            throw new NoSuchElementException("No edge from " + from + " to " + to);
        }
        return arc;
    }

    @Override
    public void addNode(V node) {
        Objects.requireNonNull(node);
        if (theGraph.containsKey(node)) {
            throw new IllegalArgumentException("Node already exists in graph: " + node);
        }
        theGraph.put(node, new HashMap<>());
    }

    @Override
    public void removeNode(V node) {
        Objects.requireNonNull(node);
        if (theGraph.remove(node) == null) {
            throw new NoSuchElementException("Node does not exist in graph: " + node);
        }
        for (var edges : theGraph.values()) {
            edges.remove(node);
        }
    }

    @Override
    public void connectNodes(V from, A weight, V to) {
        Objects.requireNonNull(from);
        Objects.requireNonNull(weight);
        checkToNode(to);
        var edges = getEdges(from);
        if (edges.containsKey(to)) {
            throw new IllegalArgumentException("Edge from " + from + " to " + to + " already exists");
        }
        edges.put(to, weight);
    }

    @Override
    public void disconnectNodes(V from, V to) {
        Objects.requireNonNull(from);
        checkToNode(to);

        var edges = getEdges(from);
        if (edges.remove(to) == null) {
            throw new NoSuchElementException("No edge from " + from + " to " + to);
        }
    }

    private void checkToNode(V to) {
        Objects.requireNonNull(to);
        if (!theGraph.containsKey(to)) {
            throw new NoSuchElementException("Node not in graph: " + to);
        }
    }
}

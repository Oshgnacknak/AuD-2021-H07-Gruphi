package de.oshgnacknak.gruphi;

import h07.graph.DirectedGraph;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public class MazeGenerator<V> {

    private final Random random;
    private final DirectedGraph<V, Double> graph;
    private final Set<V> visited;
    private final BiPredicate<V, V> areNeighbours;

    public MazeGenerator(DirectedGraph<V, Double> graph, BiPredicate<V, V> areNeighbours) {
        this.random = new Random();
        this.graph = graph;
        this.areNeighbours = areNeighbours;
        this.visited = new HashSet<>();
    }

    public void generate(V start) {
        var stack = new Stack<V>();
        visited.clear();

        for (var node : graph.getAllNodes()) {
            for (var child : graph.getChildrenForNode(node)) {
                graph.disconnectNodes(node, child);
            }
        }

        visited.add(start);
        stack.push(start);
        while (!stack.isEmpty()) {
            var current = stack.pop();
            var unvisited = getUnvisited(current);

            if (!unvisited.isEmpty()) {
                stack.push(current);
                var neighbour = unvisited.get(random.nextInt(unvisited.size()));

                graph.connectNodes(current, random.nextDouble(), neighbour);
                graph.connectNodes(neighbour, random.nextDouble(), current);

                visited.add(neighbour);
                stack.push(neighbour);
            }
        }
    }

    private List<V> getUnvisited(V node) {
        return graph.getAllNodes()
            .stream()
            .filter(n ->
                n != node
                && areNeighbours.test(node, n)
                && !visited.contains(n))
            .collect(Collectors.toList());
    }
}

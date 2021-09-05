package de.oshgnacknak.gruphi;

import h07.graph.DirectedGraph;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public class MazeGenerator<V> {

    private final Random random;
    private final DirectedGraph<V, Double> graph;
    private final Set<V> visited;
    private final BiPredicate<V, V> neighbourPredicate;

    public MazeGenerator(DirectedGraph<V, Double> graph, BiPredicate<V, V> neighbourPredicate) {
        this.random = new Random();
        this.graph = graph;
        this.neighbourPredicate = neighbourPredicate;
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

                graph.connectNodes(current, 1.0, neighbour);
                graph.connectNodes(neighbour, 1.0, current);

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
                && neighbourPredicate.test(node, n)
                && !visited.contains(n))
            .collect(Collectors.toList());
    }
}

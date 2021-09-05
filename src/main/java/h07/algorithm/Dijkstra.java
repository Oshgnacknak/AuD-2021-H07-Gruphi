package h07.algorithm;

import h07.algebra.Monoid;
import h07.graph.DirectedGraph;
import h07.graph.Path;

import java.util.*;
import java.util.stream.Stream;

public class Dijkstra<V, A> implements ShortestPathsAlgorithm<V, A> {

    @Override
    public Map<V, Path<V, A>> shortestPaths(DirectedGraph<V, A> graph, V startNode, Monoid<A> monoid, Comparator<? super A> comparator) {
        var d = new DijkstraImpl(graph, startNode, monoid, comparator);
        d.computeShortestPaths();
        return d.paths;
    }

    private class DijkstraImpl {
        final DirectedGraph<V, A> graph;
        final V startNode;
        final Monoid<A> monoid;
        final Comparator<? super A> comparator;

        final Map<V, Path<V, A>> paths;
        final Queue<V> queue;

        private DijkstraImpl(DirectedGraph<V, A> graph, V startNode, Monoid<A> monoid, Comparator<? super A> comparator) {
            this.graph = Objects.requireNonNull(graph, "Argument graph may not be null");
            this.startNode = Objects.requireNonNull(startNode, "Argument startNode may not be null");
            this.monoid = Objects.requireNonNull(monoid, "Argument monoid may not be null");
            this.comparator = Objects.requireNonNull(comparator, "Argument comparator may not be null");

            if (!graph.getAllNodes().contains(startNode)) {
                throw new NoSuchElementException("Node startNode not in graph");
            }

            this.paths = getPathMap();
            this.queue = getQueue();
        }

        private HashMap<V, Path<V, A>> getPathMap() {
            var paths = new HashMap<V, Path<V, A>>();
            paths.put(startNode, Path.of(startNode));
            return paths;
        }

        private PriorityQueue<V> getQueue() {
            var queue = new PriorityQueue<V>(Comparator.comparing(n ->
                sumPathDistances(paths.get(n)), comparator));
            queue.add(startNode);
            return queue;
        }

        private A sumPathDistances(Path<?, A> path) {
            return Stream.iterate(
                path.traverser(),
                Path.Traverser::hasNextNode,
                t -> { t.walkToNextNode(); return t; })
                .map(Path.Traverser::getDistanceToNextNode)
                .reduce(monoid.zero(), monoid::add);
        }

        public void computeShortestPaths() {
            while (!queue.isEmpty()) {
                var node = queue.remove();

                for (var child : graph.getChildrenForNode(node)) {
                    var path = getPath(node, child);

                    if (isShortestPath(child, path)) {
                        paths.put(child, path);
                        queue.remove(child);
                        queue.add(child);
                    }
                }
            }
        }

        private boolean isShortestPath(V child, Path<V, A> path) {
            if (!paths.containsKey(child)) {
                return true;
            }

            return 0 > comparator.compare(
                sumPathDistances(path),
                sumPathDistances(paths.get(child)));
        }

        private Path<V, A> getPath(V node, V child) {
            return paths
                .get(node)
                .concat(child, graph.getArcWeightBetween(node, child));
        }
    }
}
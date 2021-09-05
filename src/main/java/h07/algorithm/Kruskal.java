package h07.algorithm;

import h07.graph.DirectedGraph;
import h07.graph.DirectedGraphFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Kruskal<V, A> implements MinimumSpanningForestAlgorithm<V, A> {

    @Override
    public DirectedGraph<V, A> minimumSpanningForest(DirectedGraph<V, A> graph, Comparator<? super A> comparator, DirectedGraphFactory<V, A> factory) {
        var output = factory.createDirectedGraph();
        var sets = new ArrayList<Set<V>>();

        for (V node : graph.getAllNodes()) {
            output.addNode(node);

            var set = new HashSet<V>();
            set.add(node);
            sets.add(set);
        }

        var egdes = graph.getAllNodes()
            .stream()
            .flatMap(n ->
                graph.getChildrenForNode(n)
                    .stream()
                    .map(c ->
                        new Pair<>(n, c)))
            .sorted(Comparator.comparing(
                p -> graph.getArcWeightBetween(p.fst, p.snd),
                comparator));

        egdes.forEach(p -> {
            var s1 = sets
                .stream()
                .filter(s -> s.contains(p.fst))
                .findFirst()
                .orElseThrow();

            if (s1.contains(p.snd)) {
                return;
            }

            var s2 = sets
                .stream()
                .filter(s -> s.contains(p.snd))
                .findFirst()
                .orElseThrow();

            s1.addAll(s2);
            sets.remove(s2);

            var arc = graph.getArcWeightBetween(p.fst, p.snd);
            output.connectNodes(p.fst, arc, p.snd);
            output.connectNodes(p.snd, arc, p.fst);
        });

        return output;
    }
}

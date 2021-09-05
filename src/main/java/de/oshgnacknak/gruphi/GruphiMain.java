package de.oshgnacknak.gruphi;

import h07.algorithm.Dijkstra;
import h07.algorithm.Kruskal;
import h07.graph.DirectedGraphFactory;

public class GruphiMain {

    public static void main(String[] args) {
        var gruphi = new GruphiBuilder()
            .setDirectedGraphFactory(DirectedGraphFactory.defaultFactory())
            .setShortestPathsAlgorithm(new Dijkstra<>())
            .setMinimumSpanningForestAlgorithm(new Kruskal<>())
            .createGruphi();

        var frame = new GruphiFrame(gruphi);
        frame.setVisible(true);
        frame.updateLoop();
        System.exit(0);
    }
}

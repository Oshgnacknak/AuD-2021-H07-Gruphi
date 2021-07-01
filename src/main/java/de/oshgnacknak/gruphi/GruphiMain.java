package de.oshgnacknak.gruphi;

import h07.graph.DirectedGraphFactory;

import java.util.function.BiPredicate;

public class GruphiMain {

    public static void main(String[] args) {
        var spacing = 50;

        var gruphi = new Gruphi() {

            @Override
            public long getFrameDelay() {
                return 1000 / 60;
            }

            @Override
            public double getVelocity() {
                return 5;
            }

            @Override
            public double getGridSpacing() {
                return spacing;
            }

            @Override
            public BiPredicate<Node, Node> getNeighbourPredicate() {
                return (a, b) ->
                    a.pos.dist(b.pos) <= spacing;
            }

            @Override
            public DirectedGraphFactory<Node, Double> getDirectedGraphFactory() {
                return DirectedGraphFactory.defaultFactory();
            }
        };

        var frame = new GruphiFrame(gruphi);
        frame.setVisible(true);
        frame.updateLoop();
        System.exit(0);
    }
}

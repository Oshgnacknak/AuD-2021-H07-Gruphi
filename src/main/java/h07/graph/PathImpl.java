package h07.graph;

import java.util.*;

class PathImpl<V, A> implements Path<V, A> {

    private final List<Node> thePath;

    public PathImpl(V v1) {
        Objects.requireNonNull(v1, "Argument v1 may not be null");
        this.thePath = List.of(new Node(v1, null));
    }

    public PathImpl(List<Node> thePath) {
        this.thePath = thePath;
    }

    @Override
    public Traverser<V, A> traverser() {
        return new TraverserImp();
    }

    @Override
    public Path<V, A> concat(V node, A distance) {
        Objects.requireNonNull(node, "Argument node may not be null");
        Objects.requireNonNull(distance, "Argument distance may not be null");

        var list = new ArrayList<>(thePath);
        list.add(new Node(node, distance));
        return new PathImpl<V, A>(list);
    }

    @Override
    public Iterator<V> iterator() {
        var iter = thePath.iterator();
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return iter.hasNext();
            }

            @Override
            public V next() {
                return iter.next().node;
            }
        };
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        var trav = traverser();

        while(trav.hasNextNode()) {
            sb.append(trav.getCurrentNode())
                .append(" -[")
                .append(trav.getDistanceToNextNode())
                .append("]-> ");
            trav.walkToNextNode();
        }

        return sb
            .append(trav.getCurrentNode())
            .toString();
    }

    private class Node {
        final V node;
        final A distance;

        private Node(V node, A distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    private class TraverserImp implements Traverser<V, A> {
        Node current;
        Node next;
        Iterator<Node> iter;

        TraverserImp() {
            this.iter = thePath.iterator();
            if (iter.hasNext()) {
                current = iter.next();
            }
            if (iter.hasNext()) {
                next = iter.next();
            }
        }

        @Override
        public V getCurrentNode() {
            return current.node;
        }

        @Override
        public A getDistanceToNextNode() {
            if (next == null) {
                throw new IllegalStateException("No next node present");
            }
            return next.distance;
        }

        @Override
        public void walkToNextNode() {
            if (next == null) {
                throw new NoSuchElementException("No next node present");
            }

            current = next;
            next = iter.hasNext() ? iter.next() : null;
        }

        @Override
        public boolean hasNextNode() {
            return next != null;
        }
    }
}

package h07.graph;

/**
 * Eine abstrakte Fabrik zum Erzeugen von {@link DirectedGraph}-Objekten.
 *
 * @param <V> der Typ der Knoten dieses Graphen
 * @param <A> der Typ der Kantengewichte dieses Graphen
 */
public interface DirectedGraphFactory<V, A> {
  /**
   * Erzeugt einen {@link DirectedGraph}.
   *
   * @return der {@code DirectedGraph}, der von dieses Fabrik erzeugt wird.
   */
  DirectedGraph<V, A> createDirectedGraph();

  static <V, A> DirectedGraphFactory<V, A> defaultFactory() {
    return DirectedGraphImpl::new;
  }
}

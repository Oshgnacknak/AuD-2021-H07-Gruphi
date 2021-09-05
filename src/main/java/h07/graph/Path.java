package h07.graph;

import java.util.Objects;

/**
 * Repräsentiert einen gewichteten Pfad als Teilmenge eines endlichen
 * gerichteten Graphs.
 *
 * @param <V> der Typ der Knoten dieses Pfades
 * @param <A> der Typ der Kantengewichte dieses Pfades
 */
public interface Path<V, A> extends Iterable<V> {
  /**
   * Dieses Interface ermöglicht das Traversieren eines gerichteten Pfades.
   * Dabei läuft der {@code Traverser} von Knoten zu Knoten unter
   * Berücksichtigung der Gewichte der Kanten zwischen den Knoten.
   * Den Knoten, auf dem sich ein {@code Traverser} zu einem gegebenen
   * Zeitpunkt befindet, nennen wir den <em>aktuell betrachteten Knoten</em>.
   *
   * @param <V> der Typ der Knoten, über die dieser {@code Traverser} läuft
   * @param <A> der Typ der Gewichte der Kanten, die zwischen den einzelnen
   *            Knoten liegen
   */
  interface Traverser<V, A> {
    /**
     * Liefert den von diesem {@code Traverser} aktuell betrachteten Knoten.
     *
     * @return der aktuell betrachtete Knoten
     */
    V getCurrentNode();

    /**
     * Liefert das Gewicht der Kante vom aktuell betrachteten Knoten zu
     * seinem Nachfolgeknoten.
     *
     * @return die Distanz zum nächsten Knoten.
     * @throws IllegalStateException falls es sich beim aktuell betrachteten
     *                               Knoten um den letzten Knoten im Pfad dieses
     *                               {@code Traverser}s handelt
     */
    A getDistanceToNextNode();

    /**
     * @throws java.util.NoSuchElementException falls es sich beim aktuell
     *                                          betrachteten Knoten um den letzten Knoten im Pfad dieses
     *                                          {@code Traverser}s handelt.
     */
    void walkToNextNode();

    /**
     * Zeigt an, ob der aktuell betrachtete Knoten einen Nachfolgeknoten
     * besitzt.
     *
     * @return {@code true} genau dann, wenn es sich beim aktuell
     * betrachteten Knoten nicht um den letzten Knoten im Pfad
     * dieses {@code Traverser}s handelt
     */
    boolean hasNextNode();
  }

  /**
   * Liefert einen {@link Traverser}, mit dem sich dieser Pfad traversieren
   * lässt.
   * Beim aktuell betrachteten Knoten des {@code Traverser}s handelt es sich
   * zunächst um den ersten Knoten in diesem Pfad.
   *
   * @return ein {@code Traverser} für diesen Pfad
   */
  Traverser<V, A> traverser();

  /**
   * Erzeugt einen neuen Pfad aus den Knoten und Kanten dieses Pfades und
   * einem weiteren Knoten.
   * Dieser wird durch eine neue Kante an den letzten Knoten dieses Pfades
   * angehängt.
   * Dieser Pfad wird durch diese Methode jedoch nicht verändert.
   *
   * @param node     der Knoten, der im neuen Pfad an den letzten Knoten
   *                 dieses Pfades angehängt werden soll
   * @param distance das Gewicht der neuen Kante, mit der der neue Knoten mit
   *                 dem letzten Knoten dieses Pfades verbunden werden soll
   * @return eine Kopie dieses Pfades mit einem zusätzlichen Knoten
   * @throws NullPointerException falls der neue Knoten oder das Gewicht der
   *                              neuen Kante {@code null} ist
   */
  Path<V, A> concat(V node, A distance);

  /**
   * Erzeugt einen gewichteten Pfad mit einem Knoten.
   *
   * @param v1  der erste Knoten im Pfad
   * @param <V> der Typ der Knoten des erzeugten Pfades
   * @param <A> der Typ der Kantengewichte erzeugten Pfades
   * @return ein Pfad mit dem übergebenen Knoten
   * @throws NullPointerException falls der Knoten {@code null} ist
   */
  static <V, A> Path<V, A> of(V v1) {
    Objects.requireNonNull(v1, "Der Knoten eines Pfades darf nicht null sein");
    return new PathImpl<>(v1);
  }

  /**
   * Erzeugt einen gewichteten Pfad mit zwei Knoten.
   *
   * @param v1  der erste Knoten im Pfad
   * @param w1  das Gewicht der Kante vom ersten zum zweiten Knoten
   * @param v2  der zweite Knoten im Pfad
   * @param <V> der Typ der Knoten des erzeugten Pfades
   * @param <A> der Typ der Kantengewichte erzeugten Pfades
   * @return ein Pfad mit den übergebenen Knoten und Kantengewichten
   * @throws NullPointerException falls einer der Knoten oder eines der
   *                              Kantengewichte {@code null} ist
   */
  static <V, A> Path<V, A> of(V v1, A w1, V v2) {
    return Path.<V, A>of(v1).concat(v2, w1);
  }

  /**
   * Erzeugt einen gewichteten Pfad mit drei Knoten.
   *
   * @param v1  der erste Knoten im Pfad
   * @param w1  das Gewicht der Kante vom ersten zum zweiten Knoten
   * @param v2  der zweite Knoten im Pfad
   * @param w2  das Gewicht der Kante vom zweiten zum dritten Knoten
   * @param v3  der dritte Knoten im Pfad
   * @param <V> der Typ der Knoten des erzeugten Pfades
   * @param <A> der Typ der Kantengewichte erzeugten Pfades
   * @return ein Pfad mit den übergebenen Knoten und Kantengewichten
   * @throws NullPointerException falls einer der Knoten oder eines der
   *                              Kantengewichte {@code null} ist
   */
  static <V, A> Path<V, A> of(V v1, A w1, V v2, A w2, V v3) {
    return Path.of(v1, w1, v2).concat(v3, w2);
  }
}

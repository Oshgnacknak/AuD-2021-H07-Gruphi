# Gruphi - Die Graph GUI

![](gruphi.png)

Um diese GUI nutzen zu müssen,
muss man die H1,
sprich die Klasse `h07.graph.DirectedGraphImpl`,
implementiert haben
und eine entsprechende Fabrik in der `Gruphi.main()` angeben.
Die einfachste Fabrik sieht in etwa so aus:

```java
public static <V, A> DirectedGraphFactory<V, A> defaultFactory() {
    return DirectedGraphImpl::new;
}
```


# Streuerung

- Pfeiltasten, Vimkeys, Mittlere Maustaste:
Kamera bewegen

- Mausrad:
Zoomen

- `C`:
Graphen leeren 

- `G`:
Graphen leeren und raster erzeugen
  
- Linke Maustaste:
Knoten erstellen

- Rechte Maustaste:
Knoten auswählen
  
- Escape, `Q`:
Beenden

## Wenn Knoten ausgewählt (rot)

- WASD:
Knoten bewegen

- `X`, Entfernen, Rücktaste:
Knoten löschen

- Linke Maustaste:
Verbingung erstellen bzw. löschen,
wenn anderer Knoten geklickt.
Sonst Knoten teleportieren

- Rechte Maustaste:
Knoten unauswählen,
bwz. neuen Knoten auswählen
  
- `M`:
Labyrinth mit Knoten als Startknoten generieren

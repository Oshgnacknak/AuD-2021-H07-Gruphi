# Gruphi - Die Graph GUI

![](gruphi.png)

Um diese GUI nutzen zu müssen,
muss man die H1,
sprich die Klasse `h07.graph.DirectedGraphImpl`,
implementiert haben und eine Factory dafür erstellen.
Die Einfachste sieht in etwa so aus:

```java
public static <V, A> DirectedGraphFactory<V, A> defaultFactory() {
    return DirectedGraphImpl::new;
}
```

Diese kann man in der Klasse [de.oshgnacknak.gruphi.GruphiMain](src/main/java/de/oshgnacknak/gruphi/GruphiMain.java)
angeben und die Main-Methode ausführen.
Weiter kann man dort einige Variablen setzten,
die in [de.oshgnacknak.gruphi.Gruphi](src/main/java/de/oshgnacknak/gruphi/Gruphi.java) definiert sind.
Unter anderem kann man hier auch seinen Dijkstra (H5) anmelden.
Viel Spaß!

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
Knoten auswählen.
Startknoten (grün) auswählen löscht Pfäde.
  
- Escape, `Q`:
Beenden

- `U`, `I`:
Gewichtung für die nächste Kante verkleinern/vergrößern

- `O`:
Gewichtung für die nächste Kante auf 1 zurücksetzten

- `F`:
Berechne oder verstecke den minimalen Spannbaum (blau) 

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

- `P`:
Pfäde mit Knoten als Startknoten generieren (grün).
Danach andere Knoten auswählen, um Pfäde zu sehen.
package de.oshgnacknak.gruphi;

import h07.graph.DirectedGraph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Optional;

class GruphiFrame extends JFrame {

    private final DirectedGraph<Node, Double> graph;

    private final MazeGenerator<Node> mazeGenerator;
    private final Gruphi gruphi;

    private final PanningAndZooming panningAndZooming;

    private final Vector selectedVel = new Vector(0, 0);
    private final Vector cameraVel = new Vector(0, 0);

    private Node selected = null;
    private boolean running = true;

    GruphiFrame(Gruphi gruphi) {
        super("Gruphi - The Graph GUI - By Osh");

        this.gruphi = gruphi;
        this.graph = gruphi.getDirectedGraphFactory().createDirectedGraph();
        this.mazeGenerator = new MazeGenerator<>(graph, gruphi.getNeighbourPredicate());
        this.panningAndZooming = new PanningAndZooming(this);

        add(new Canvas(this::draw));
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        addMouseListener(panningAndZooming);
        addMouseWheelListener(panningAndZooming);
        addMouseMotionListener(panningAndZooming);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W: {
                        selectedVel.y = -gruphi.getVelocity();
                    } break;

                    case KeyEvent.VK_S: {
                        selectedVel.y = gruphi.getVelocity();
                    } break;

                    case KeyEvent.VK_A: {
                        selectedVel.x = -gruphi.getVelocity();
                    } break;

                    case KeyEvent.VK_D: {
                        selectedVel.x = gruphi.getVelocity();
                    } break;

                    case KeyEvent.VK_K:
                    case KeyEvent.VK_UP: {
                        cameraVel.y = gruphi.getVelocity();
                    } break;

                    case KeyEvent.VK_J:
                    case KeyEvent.VK_DOWN: {
                        cameraVel.y = -gruphi.getVelocity();
                    } break;

                    case KeyEvent.VK_H:
                    case KeyEvent.VK_LEFT: {
                        cameraVel.x = gruphi.getVelocity();
                    } break;

                    case KeyEvent.VK_L:
                    case KeyEvent.VK_RIGHT: {
                        cameraVel.x = -gruphi.getVelocity();
                    } break;

                    case KeyEvent.VK_X:
                    case KeyEvent.VK_DELETE:
                    case KeyEvent.VK_BACK_SPACE: {
                        if (selected != null) {
                            graph.removeNode(selected);
                            selected = null;
                        }
                    } break;

                    case KeyEvent.VK_Q:
                    case KeyEvent.VK_ESCAPE: {
                        running = false;
                    } break;

                    case KeyEvent.VK_C: {
                        clearGraph();
                    } break;

                    case KeyEvent.VK_M: {
                        if (selected != null) {
                            mazeGenerator.generate(selected);
                        }
                    } break;

                    case KeyEvent.VK_G: {
                        generateGrid();
                    } break;

                    default: break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                    case KeyEvent.VK_S: {
                        selectedVel.y = 0;
                    } break;

                    case KeyEvent.VK_A:
                    case KeyEvent.VK_D: {
                        selectedVel.x = 0;
                    } break;

                    case KeyEvent.VK_K:
                    case KeyEvent.VK_J:
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_UP: {
                        cameraVel.y = 0;
                    } break;

                    case KeyEvent.VK_H:
                    case KeyEvent.VK_L:
                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_LEFT: {
                        cameraVel.x = 0;
                    } break;

                    case KeyEvent.VK_PLUS: {
                        panningAndZooming.zoom(1.1);
                    } break;

                    case KeyEvent.VK_MINUS: {
                        panningAndZooming.zoom(0.9);
                    } break;

                    default: break;
                }
            }
        });
    }

    public void onMousePressed(int button, Vector v) {
        switch (button) {
            case MouseEvent.BUTTON1: {
                if (selected != null) {
                    var clicked = findClickedNode(v);
                    if (clicked.isPresent() && clicked.get() != selected) {
                        var n = clicked.get();
                        if (graph.getChildrenForNode(selected).contains(n)) {
                            graph.disconnectNodes(selected, n);
                        } else {
                            graph.connectNodes(selected, 1.0, n);
                        }
                    } else {
                        selected.pos = v;
                    }
                } else {
                    graph.addNode(new Node(v));
                }
            } break;

            case MouseEvent.BUTTON3: {
                if (selected != null) {
                    selected.color = Node.COLOR;
                    selected.radius = Node.RADIUS;
                    selected = null;
                }
                findClickedNode(v)
                    .ifPresent(n -> {
                        selected = n;
                        selected.radius *= 1.3;
                        selected.color = Color.RED;
                    });
            } break;

            default: break;
        }
    }

    private void generateGrid() {
        clearGraph();

        var dist = gruphi.getGridSpacing();

        var rows = getHeight() / dist - 1;
        var cols = getWidth() / dist - 1;

        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                var v = new Vector(x, y)
                    .add(0.5, 0.5)
                    .mul(dist);
                var n = new Node(v);
                graph.addNode(n);
            }
        }
    }

    private void clearGraph() {
        selected = null;
        for (var node : graph.getAllNodes()) {
            graph.removeNode(node);
        }
    }

    private Optional<Node> findClickedNode(Vector v) {
        return graph.getAllNodes()
            .stream()
            .filter(n -> n.inside(v))
            .findFirst();
    }

    public void draw(Drawable d) {
        d.fill(Color.BLACK);
        d.rect(0, 0, getWidth(), getHeight());
        panningAndZooming.draw(d, () ->
            drawNodes(d));
    }

    private void drawNodes(Drawable d) {
        d.strokeWeight(1);
        d.fill(Color.WHITE);
        drawConnections(d);

        for (var node : graph.getAllNodes()) {
            d.fill(node.color);
            d.ellipse(node.pos.x, node.pos.y, node.radius * 2, node.radius * 2);
        }
    }

    private void drawConnections(Drawable d) {
        for (var node : graph.getAllNodes()) {
            for (var child : graph.getChildrenForNode(node)) {
                d.line(node.pos.x, node.pos.y, child.pos.x, child.pos.y);
                drawConnectionArrowHead(d, node, child);
            }
        }
    }

    private void drawConnectionArrowHead(Drawable d, Node node, Node child) {
        var v = child.pos
            .copy()
            .sub(node.pos);
        var r = 4;
        var p = v.copy()
            .setMag(v.mag() - child.radius)
            .add(node.pos);

        d.rotated(v.angle(), p.x, p.y, () ->
            d.triangle(
                p.x+r, p.y,
                p.x-r, p.y-r,
                p.x-r, p.y+r));
    }

    public void updateLoop() {
        var last = System.currentTimeMillis();
        var acc = 0;

        while (running) {
            while (acc > gruphi.getFrameDelay()) {
                update();
                acc -= gruphi.getFrameDelay();
            }
            repaint();

            var current = System.currentTimeMillis();
            acc += current - last;
            last = current;
        }
    }

    private void update() {
        if (selected != null) {
            selected.pos.add(selectedVel);
        }
        panningAndZooming.pan(cameraVel);
    }
}

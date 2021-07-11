package de.oshgnacknak.gruphi;

public class GruphiMain {

    public static void main(String[] args) {
        var gruphi = new GruphiBuilder()
            // .setDirectedGraphFactory(someFactory)
            .createGruphi();

        var frame = new GruphiFrame(gruphi);
        frame.setVisible(true);
        frame.updateLoop();
        System.exit(0);
    }
}

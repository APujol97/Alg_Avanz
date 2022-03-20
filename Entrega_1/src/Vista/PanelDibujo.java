/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import Principal.Error;
import Modelo.Model;

/**
 *
 * @author Joan Alcover, Alejandro Fluixà, Francisco Muñoz, Antonio Pujol
 */
public class PanelDibujo extends JPanel {

    private int w;
    private int h;
    private Model mod;
    private Vista vis;
    protected final int FPS = 24;  // 24 frames per segon
    private final ProcesPintat procpin;
    private BufferedImage bima;

    public PanelDibujo(int x, int y, Model m, Vista v) {
        w = x;
        h = y;
        mod = m;
        vis = v;
        bima = null;
        this.setPreferredSize(new Dimension(w, h));
        
                
        procpin = new ProcesPintat(this);
        //el 30 es por las iteraciones, si las iteraciones son fijas pues lo podemos dejar asi pero si las introduce el ususario habria que cambiar
        procpin.start();
    }

    public void repaint() {
        if (this.getGraphics() != null) {
            paint(this.getGraphics());
        }
    }

    public void paint(Graphics gr) {
        Graphics2D aux = (Graphics2D) gr;
        aux.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        mod.setPixels((int) getWidth() / 30);

        aux.setStroke(new BasicStroke(2.0f));
        
        aux.setColor(Color.BLACK);
       
        aux.drawLine(0, 0, 0, getHeight());
        aux.drawLine(0, 0, getWidth(), 0);
        aux.drawLine(getWidth(), 0, getWidth(), getHeight());
        aux.drawLine(0, getHeight(), getWidth(), getHeight());

        switch (mod.getTipo()) {
            case 1:
                aux.setColor(Color.BLUE);
                break;
            case 2:
                aux.setColor(Color.RED);
                break;
            case 3:
                aux.setColor(Color.green);
                break;
            case 4:
                aux.setColor(Color.PINK);
                break;
        }
        
        aux.drawLine(mod.getOldX(), ( getHeight() - mod.getOldY() ), mod.getX(), ( getHeight() - mod.getY() ));
        vis.actualizarBarra(mod.getPercent());
    }
}

class ProcesPintat extends Thread {

    private PanelDibujo pan;

    public ProcesPintat(PanelDibujo pd) {
        pan = pd;
    }

    public void run() {
        long temps = System.nanoTime();
        long tram = 1000000000L / pan.FPS;
        while (true) {
            if ((System.nanoTime() - temps) > tram) {
                pan.repaint();
                temps = System.nanoTime();
                espera((long) (tram / 2000000));
            }
        }
    }

    private void espera(long t) {
        try {
            Thread.sleep(t);
        } catch (Exception e) {
            Error.informaError(e);
        }
    }
}

package Model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Ball implements Runnable, VO {
    private int vx, vy;
    private int posx, posy, radius = 80;
    private boolean isRunning = true;
    private Color color;
    TGM model;

    public Ball(TGM model, int vx, int vy, int posx, int posy, int radius) {
        this.model = model;
        this.vx = vx;
        this.vy = vy;
        this.posx = posx;
        this.posy = posy;
        this.radius = radius;
        this.color = new Color((int) (Math.random() * 0x1000000));

    }

    @Override
    public void run() {
        while (isRunning) {
            if (!model.collideDetection(this)) {
                move();
            }
            try {
                {// Se duerme el hilo para que la bola no se mueva tan rápido
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void stop() {
        isRunning = false;
    }

    @Override
    public void paint(Graphics g) {
        // Se crea un objeto Graphics2D para poder dibujar
        Graphics2D g2d = (Graphics2D) g;
        // Se activa el antialiasing y se pinta la bola con sus características
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(this.color);
        g2d.fillOval(posx, posy, radius, radius);
    }

    @Override
    public void move() {
        // Se actualiza la posición de la bola
        posx += vx;
        posy += vy;
    }

    public int getVx() {
        return this.vx;
    }

    public void setVx(int vx) {
        this.vx = vx;
    }

    public int getVy() {
        return this.vy;
    }

    public void setVy(int vy) {
        this.vy = vy;
    }

    public int getPosx() {
        return this.posx;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public int getPosy() {
        return this.posy;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

    public int getRadius() {
        return this.radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public boolean isIsRunning() {
        return this.isRunning;
    }

    public boolean getIsRunning() {
        return this.isRunning;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public TGM getModel() {
        return this.model;
    }

    public void setModel(TGM model) {
        this.model = model;
    }

}

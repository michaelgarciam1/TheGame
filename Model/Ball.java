package Model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Ball implements Runnable, VO {
    private int vx, vy;
    private int posx, posy, radius = 80;
    private boolean isRunning = true;

    public Ball(int vx, int vy, int posx, int posy, int radius) {
        this.vx = vx;
        this.vy = vy;
        this.posx = posx;
        this.posy = posy;
        this.radius = radius;
    }

    // Método para hacer rebotar la bola en los bordes de la ventana
    private void bounce() {
        // Movimiento horizontal
        if (posx - radius <= 0 || posx + radius >= 500) {
            vx = -vx;
        }
        // Movimiento vertical
        if (posy - radius <= 0 || posy + radius >= 500) {
            vy = -vy;
        }
    }

    @Override
    public void run() {
        while (isRunning) {
            bounce();
            move();
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
        g2d.setColor(Color.lightGray);
        g2d.fillOval(posx, posy, radius, radius);
    }

    @Override
    public void move() {
        // Se actualiza la posición de la bola
        posx += vx;
        posy += vy;
    }

}

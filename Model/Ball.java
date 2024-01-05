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
            move();
            bounce();
            try {
                {
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
     
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.RED);
        g2d.fillOval(posx - radius, posy - radius, 2 * radius, 2 * radius); // Draw the ball
    }

    @Override
    public void move() {
        posx += vx;
        posy += vy;
    }

}

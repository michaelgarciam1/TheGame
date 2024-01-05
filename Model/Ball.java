package Model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Ball implements Runnable, VO {
    private int vx, vy;
    private int posx, posy, radius ;
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
            bounce();
            move();
           
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
        // System.out.println("pintando en " + posx + " " + posy+ " " + radius);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.black);
        g2d.fillOval(posx , posy , radius,  radius); // Draw the ball
    }

    @Override
    public void move() {
        posx += vx;
        posy += vy;
        // System.out.println("posx: " + posx + " posy: " + posy);
    }

}

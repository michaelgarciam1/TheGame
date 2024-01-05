package Model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Ball implements Runnable, VO {
    private int velocityX;
    private int velocityY;
    private int acceleration;
    private int posx, posy, bounds;
    private int mass;
    private int radius;
    private boolean move_up, move_left;
    private int width = 500;
    private int height = 500;
    private boolean isRunning = true;

    public Ball(int x, int y, int bounds,int velocityX,int velocityY) {
        this.posx = x;
        this.posy = y;
        this.bounds = bounds;
        this.velocityX = velocityX;
        this.velocityY = velocityY;

    }


    @Override
    public void run() {
        while(isRunning) {
            move();
            try {
                Thread.sleep(10);
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
        g2d.fillOval(posx, posy, bounds, bounds);
    }

    @Override
    public void move() {
        posx += velocityX;
        posy += velocityY;
    }


    public int getAcceleration() {
        return this.acceleration;
    }

    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
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

    public int getBounds() {
        return this.bounds;
    }

    public void setBounds(int bounds) {
        this.bounds = bounds;
    }

    public int getMass() {
        return this.mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public int getRadius() {
        return this.radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public boolean isMove_up() {
        return this.move_up;
    }

    public boolean getMove_up() {
        return this.move_up;
    }

    public void setMove_up(boolean move_up) {
        this.move_up = move_up;
    }

    public boolean isMove_left() {
        return this.move_left;
    }

    public boolean getMove_left() {
        return this.move_left;
    }

    public void setMove_left(boolean move_left) {
        this.move_left = move_left;
    }

}

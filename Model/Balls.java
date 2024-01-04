package Model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Balls implements Runnable, VO {
    private int velocity;
    private int acceleration;
    private int posx, posy, bounds = 80;
    private int mass;
    private int radius;
    private boolean move_up, move_left;
    private int width = 500;
    private int height = 500;
    private boolean isRunning = true;

    public Balls(int velocity, int acceleration, int posx, int posy, int bounds, int mass, int radius, boolean move_up,
            boolean move_left) {
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.posx = posx;
        this.posy = posy;
        this.bounds = bounds;
        this.mass = mass;
        this.radius = radius;
        this.move_up = move_up;
        this.move_left = move_left;
    }


    private void bounce() {
        // horizontal
        if (posx > width - bounds) {
            move_left = true;
        }
        if (posx < 0) {
            move_left = false;
        }
        if (move_left) {
            posx -= 1;
        } else {
            posx += 1;
        }

        // vertical
        if (posy > height - bounds) {
            move_up = true;
        }
        if (posy < 0) {
            move_up = false;
        }
        if (move_up) {
            posy -= 1;
        } else {
            posy += 1;
        }

    }

    @Override
    public void run() {
        while (isRunning) {
            bounce();
            try {
                synchronized(this){
                    wait(10);
                }
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public void stop() {
        isRunning = false;
        synchronized (this) {
            this.notify();  // Notifica al hilo para que salga del wait
        }
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

    public int getVelocity() {
        return this.velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
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

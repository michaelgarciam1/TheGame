package Model;

import javax.swing.Timer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Balls implements Runnable, VO, ActionListener {
    private int velocity;
    private int acceleration;
    private int posx,posy,bounds=80;
    private int mass;
    private int radius;
	private boolean move_up, move_left;
    private int width = 500;
    private int height = 500;


    public Balls(int velocity, int acceleration, int posx, int posy, int bounds, int mass, int radius, boolean move_up, boolean move_left) {
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
    

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        paint(); 
    }
    private void bounce() {
       new ActionListener() {			
            public void actionPerformed(ActionEvent e) {
                // horizontal motion logic.
                if (posx > width - bounds) {
                    move_left = true;
                }
                if (posx < 0) {
                    move_left = false;
                }
                // Performing horizontal motion.
                    if (move_left) {
                        posx -= 1;
                    }
                    else {
                        posx += 1;
                    }
                // vertical motion logic.
                if (posy > height - bounds) {
                    move_up = true;
                }
                if (posy < 0) {
                    move_up = false;
                }
                // Performing vertical motion.
                    if (move_up) {
                        posy -= 1;
                    }
                    else {
                        posy += 1;
                    }
         
            }
        };
      
        
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
   
    @Override
    public void run() {
        while (true) {
            bounce();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

   @Override
    public void paint() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'paint'");
    }

    @Override
    public void move() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

}

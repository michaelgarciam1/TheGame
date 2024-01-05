package View;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import Model.Ball;

public class VW extends Canvas implements Runnable{

    ArrayList<Ball> balls;

    public VW(ArrayList<Ball> balls) {
        this.balls = balls;
        Dimension d = new Dimension(200, 300);
        this.setPreferredSize(d);
   

    }
        
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Ball ball : balls) {
             System.out.println("llega");
            ball.paint(g);
        }

    }

    public ArrayList<Ball> getBalls() {
        return this.balls;
    }

    public void setBalls(ArrayList<Ball> balls) {
        this.balls = balls;
    }

    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

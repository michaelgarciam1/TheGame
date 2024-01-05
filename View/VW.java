package View;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import Model.Ball;

public class VW extends Canvas {

    ArrayList<Ball> balls;

    public VW(ArrayList<Ball> balls) {
        this.balls = balls;

        Dimension d = new Dimension(500, 500);
        this.setPreferredSize(d);
    }

    @Override
    public void paint(Graphics g) {
        for (Ball ball : balls) {
            ball.paint(g);
        }
    }

    public ArrayList<Ball> getBalls() {
        return this.balls;
    }

    public void setBalls(ArrayList<Ball> balls) {
        this.balls = balls;
    }

}

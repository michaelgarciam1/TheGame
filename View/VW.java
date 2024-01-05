package View;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;


import Model.Ball;

public class VW extends Canvas  {

    ArrayList<Ball> balls;

    public VW(ArrayList<Ball> balls) {
        this.balls = balls;
    }
    public void paint() {
        
        Graphics2D g2d = (Graphics2D) this.getGraphics();
        for (Ball ball : balls) {
            ball.paint(g2d);
        }
    }
}

package View;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;


import Model.Balls;

public class VW extends JPanel implements Runnable {

    ArrayList<Balls> balls;

    public VW(ArrayList<Balls> balls) {
        this.balls = balls;

        this.setLayout(new GridBagLayout());
        this.addComponentsToPane();
    }

    private void addComponentsToPane() {
        GridBagConstraints c = new GridBagConstraints();
        JLabel lBalls = new JLabel("Bolas");
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.gridheight = 1;
        c.gridwidth = 1;

        c.gridx++;
        this.add(lBalls, c);
        c.gridx++;
        this.add(this.balls, c);
    }

    private void add(ArrayList<Balls> balls2, GridBagConstraints c) {
    }

    @Override
    public void run() {
        while (true) {
            // Actualizar la posición de las bolas
            for (Balls ball : balls) {
                ball.move();
            }
            // Repintar el panel
            repaint();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Balls> getBalls() {
        return this.balls;
    }

    public void setBalls(ArrayList<Balls> balls) {
        this.balls = balls;
    }

}

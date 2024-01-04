package View;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import Model.Balls;

import java.awt.*;

import java.util.ArrayList;

public class CP extends JPanel {
    ArrayList<Balls> balls;
    JToggleButton playPause;

    public CP(ArrayList<Balls> balls) {
        this.balls = balls;
        this.playPause = new JToggleButton("Play/Pause");
        this.setLayout(new GridBagLayout());
        addComponentsToPane();

    }

    private void addComponentsToPane() {
        GridBagConstraints c = new GridBagConstraints();
        JLabel lPlayPause = new JLabel("Empezar a Jugar");
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0, 30, 0, 4);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.gridheight = 1;
        c.gridwidth = 1;

        this.add(lPlayPause, c);
        c.gridx++;
        this.add(this.playPause, c);

    }

    public JToggleButton getPlayPause() {
        return this.playPause;
    }

    public void setPlayPause(JToggleButton playPause) {
        this.playPause = playPause;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar las bolas en el panel de control
        if (balls != null) {
            Graphics2D g2d = (Graphics2D) g;
            for (Balls ball : balls) {
                g2d.setColor(Color.RED);
                g2d.fillOval(ball.getPosx(), ball.getPosy(), ball.getBounds(), ball.getBounds());
            }
        }
    }
}


package View;

import javax.swing.JPanel;
import javax.swing.JToggleButton;

import Controller.TGCT;
import Model.Balls;

import java.awt.*;

import java.util.ArrayList;

public class CP extends JPanel {
    ArrayList<Balls> balls;
    JToggleButton playPause;
    TGCT controller;

    public CP(TGCT controller, ArrayList<Balls> balls) {
        this.balls = balls;
        this.controller = controller;
        this.playPause = new JToggleButton("Play/Pause");
        this.setLayout(new GridBagLayout());
        addComponentsToPane();

    }

    private void addComponentsToPane() {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0, 30, 0, 4);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.gridheight = 1;
        c.gridwidth = 1;

        this.add(this.playPause, c);

    }

    public JToggleButton getPlayPause() {
        return this.playPause;
    }

    public void setPlayPause(JToggleButton playPause) {
        this.playPause = playPause;
    }


}


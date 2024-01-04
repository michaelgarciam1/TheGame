package View;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JToggleButton;

import Controller.TGCT;
import Model.Balls;

public class TGV extends JFrame implements ActionListener, Runnable {
    CP controlPanel;
    JToggleButton playPause;
    VW viewer;
    TGCT controller;
    ArrayList<Balls> balls;

    public TGV(TGCT controller, ArrayList<Balls> balls) {
        this.balls = balls;
        this.controller = controller;
        this.controlPanel = new CP(controller, balls);
        this.viewer = new VW(balls);
        this.playPause = this.controlPanel.getPlayPause();
        this.playPause.addActionListener(this);
    
        this.configureJFrame();
        this.setVisible(true);

    }

    private void configureJFrame() {
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 800);
        this.addComponentsToPane(this.getContentPane());
    }

    private void addComponentsToPane(Container panel) {
        GridBagConstraints c = new GridBagConstraints();

        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.gridheight = 1;
        c.gridwidth = 1;

        panel.add(controlPanel, c);
        c.gridy++;
        c.gridx = 0;

        panel.add(viewer, c);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        switch (str) {
            case "Play-Pause":
                if (this.playPause.isSelected()) {
                    this.playPause.setText("Pause");
                } else {
                    this.playPause.setText("Play");
                }
                controller.play();
                break;
            default:
                System.err.println("Acción NO tratada: " + e);
        }
    }

    public CP getControlPanel() {
        return this.controlPanel;
    }

    public void setControlPanel(CP controlPanel) {
        this.controlPanel = controlPanel;
    }

    public JToggleButton getPlayPause() {
        return this.playPause;
    }

    public void setPlayPause(JToggleButton playPause) {
        this.playPause = playPause;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
                // this.viewer.repaint();
            } catch (InterruptedException e) {
            }
        }
    }
}
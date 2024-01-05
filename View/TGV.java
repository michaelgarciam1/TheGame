package View;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JToggleButton;
import Controller.TGCT;


public class TGV extends JFrame implements ActionListener, Runnable {
    CP controlPanel;
    JToggleButton playPause;
    VW viewer;
    TGCT controller;
    Thread viewerThread;

    public TGV(TGCT controller) {
        this.controller = controller;
        this.viewer = new VW(controller.getModel().getBalls());
        this.controlPanel = new CP();
    
        this.playPause = this.controlPanel.getPlayPause();
        this.playPause.addActionListener(this);
    
        this.configureJFrame();
        this.setVisible(true);

        this.viewerThread = new Thread(this.viewer);
        this.viewerThread.start();

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
        // panel.validate();
        // panel.repaint();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        switch (str) {
            case "Play/Pause":
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
                this.viewer.repaint();
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
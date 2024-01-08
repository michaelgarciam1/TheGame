package View;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToggleButton;
import Controller.TGCT;

public class TGV extends JFrame implements ActionListener {
    CP controlPanel;
    JButton playPause;
    VW viewer;
    TGCT controller;
    Thread viewerThread;                        

    public TGV(TGCT controller) {
        this.controller = controller;
        // Se inicializa la vista con la lista de bolas del modelo
        this.viewer = new VW(controller.getModel().getBalls());
        this.controlPanel = new CP();

        this.playPause = this.controlPanel.getPlayPause();
        this.playPause.addActionListener(this);

        // Se configura la interfaz gráfica
        this.configureJFrame();
        this.setVisible(true);

        // inicalización del hilo para la actualización continua de la vista
        this.viewerThread = new Thread(this.viewer);
        this.viewerThread.start();

    }

    // Método para configurar la interfaz gráfica
    private void configureJFrame() {
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 800);
    
        this.addComponentsToPane(this.getContentPane());
        this.pack();
    }

    // Método para añadir componentes al panel.
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

        // Se añade el panel de control al contenedor
        panel.add(controlPanel, c);
        c.gridy++;
        c.gridx = 0;

        // Se añade el panel de visualización al contenedor
        panel.add(viewer, c);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        switch (str) {
            // Si se pulsa el botón de play/pause se llama al método play del controlador
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

    public JButton getPlayPause() {
        return this.playPause;
    }

    public void setPlayPause(JButton playPause) {
        this.playPause = playPause;
    }

}
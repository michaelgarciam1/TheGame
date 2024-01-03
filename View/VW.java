package View;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Model.Balls;

public class VW extends JPanel implements Runnable {
    JTextField contador;
    ArrayList<Balls> balls;

    public VW(ArrayList<Balls> balls) {
        this.balls = balls;
        this.contador = new JTextField("0");

        this.setLayout(new GridBagLayout());
        this.addComponentsToPane();
    }

    private void addComponentsToPane() {
        GridBagConstraints c = new GridBagConstraints();
        JLabel lContador = new JLabel("Contador");
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.gridheight = 1;
        c.gridwidth = 1;

        c.gridx++;
        this.add(lContador, c);
        c.gridx++;
        this.add(this.contador, c);
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

    public JTextField getContador() {
        return this.contador;
    }

    public void setContador(JTextField contador) {
        this.contador = contador;
    }


    public ArrayList<Balls> getBalls() {
        return this.balls;
    }

    public void setBalls(ArrayList<Balls> balls) {
        this.balls = balls;
    }
   


}

package View;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import java.awt.*;

public class CP extends JPanel {
    JToggleButton playPause;

    public CP() {
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
}

package Model;

import java.awt.Graphics;

// Interfaz que implementan las bolas para poder pintarlas y moverlas
public interface VO {
    public void paint(Graphics g);

    public void move();
}
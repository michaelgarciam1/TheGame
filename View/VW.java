package View;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import Model.Ball;

public class VW extends Canvas implements Runnable {
    ArrayList<Ball> balls;
    // Imagen fuera de pantalla para evitar parpadeo
    private Image offscreenImage;

    public VW(ArrayList<Ball> balls) {
        this.balls = balls;
        Dimension d = new Dimension(500, 500);
        this.setPreferredSize(d);

    }

    // Método para actualizar la imagen
    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        // Si la imagen fuera de pantalla no existe la creamos
        if (offscreenImage == null) {
            offscreenImage = createImage(getWidth(), getHeight());
        }
        // Obtenemos el contexto gráfico de la imagen
        Graphics offscreenGraphics = offscreenImage.getGraphics();

        // Llamamos a clear, que se encarga de llenar el fondo de la imagen fuera de
        // pantalla con un color blanco
        clear((Graphics2D) offscreenGraphics);

        // Convierte el objeto Graphics a un objeto Graphics2D para usar el renderizado
        // y que no parpadee
        Graphics2D g2d = (Graphics2D) offscreenGraphics;

        // Dibujamos cada bola de la lista
        for (Ball ball : balls) {
            if (ball != null) {
                ball.paint(g2d);
            }
        }
        
        // Dibujamos la imagen fuera de pantalla en el canvas
        g.drawImage(offscreenImage, 0, 0, this);
    }

    // Método para limpiar la imagen fuera de pantalla
    public void clear(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    // Con el run se actualiza continuamente la vista
    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Ball> getBalls() {
        return this.balls;
    }

    public void setBalls(ArrayList<Ball> balls) {
        this.balls = balls;
    }

}

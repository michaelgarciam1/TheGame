package Controller;

import Server.*;
import Model.*;

public class TGPCT {

    public boolean derecha = false;
    public boolean isConnected = false;
    private CCT conexiones;
    private TGCT game;

    TGR rules;

    public TGPCT() {
        rules = new TGR(this);
        game = new TGCT(this);
        conexiones = new CCT("localhost", this);
    }

    public static void main(String[] args) {
        TGPCT game = new TGPCT();
    }

    public void collide(Object o1, Object o2) {
        rules.collide(o1, o2);
    }

    public void enviarDerecha(Ball ball) {
        System.out.println("enviando derecha"+ derecha+", " +isConnected);
        if (isConnected && derecha) {

            ball.setVx(-ball.getVx());
        } else {
            // System.out.println("enviando derecha"+ derecha+", " +ball.getPosx());
            ball.setPosx(1);
            ball.kill();
            conexiones.enviarBall(ball);
        }
    }

    public void enviarIzquierda(Ball ball) {
        if (isConnected && !derecha) {
            ball.setVx(-ball.getVx());
        } else {
            // System.out.println("enviando izquierda"+ derecha+", " +ball.getPosx());
            ball.setPosx(499);
            ball.kill();
            conexiones.enviarBall(ball);
        }
    }

    public void ballRecieved(Ball ball) {
        game.addBall(ball);
    }

    public boolean isDerecha() {
        return this.derecha;
    }

}

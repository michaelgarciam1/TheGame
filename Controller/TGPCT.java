package Controller;

import Server.*;
import Model.*;

public class TGPCT {

    private boolean derecha = true;
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
        if (isConnected && !derecha) {
            ball.kill();
            conexiones.enviarBall(ball);
           
        } else {
            ball.setVx(-ball.getVx());
        }
    }

    public void enviarIzquierda(Ball ball) {
        if (isConnected && derecha) {
            ball.kill();
            conexiones.enviarBall(ball);
        } else {
            ball.setVx(-ball.getVx());
        }
    }

    public void ballRecieved(Ball ball) {
        game.addBall(ball);
    }

}

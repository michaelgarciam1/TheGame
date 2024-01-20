package Controller;

import Model.Ball;

public class TGR {
    TGPCT controler;

    public TGR(TGPCT controler) {
        this.controler = controler;
    }

    public void collide(Object o1, Object o2) {
        if (o1 instanceof Ball && o2 instanceof String) {
            wallCollide((Ball) o1, (String) o2);
        }
        if (o1 instanceof Ball && o2 instanceof Ball) {
            ballCollide((Ball) o1, (Ball) o2);
        }
    }

    private void wallCollide(Ball ball, String wall) {
        if (wall.equals("y")) {
            ball.setVy(-ball.getVy());
        }
        if (wall.equals("x+")) {
            controler.enviarDerecha(ball);
        }
        if (wall.equals("x-")) {
            controler.enviarIzquierda(ball);
        }

    }

    private void ballCollide(Ball b1, Ball b2) {
        b1.kill();
        b2.kill();
    }
}

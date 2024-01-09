package Controller;

import Model.Ball;

public class TGR {
    public TGR() {
    }

    public void collide(Object object1, Object object2) {
        if (object1 instanceof Ball && object2 instanceof String) {
            wallCollide((Ball) object1, (String) object2);
        }
        //si hay mas objetos habra que devolver una lista de acciones al modelo
        //y aqui tambien tiene que llegar un arraylist de las cosas que chocan
    }

    private void wallCollide(Ball ball, String wall) {
        if (wall.equals("x")) {
            ball.setVx(-ball.getVx());
        } else {
            ball.setVy(-ball.getVy());
        }
    }

}

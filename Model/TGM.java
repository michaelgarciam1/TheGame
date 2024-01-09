package Model;

import java.util.ArrayList;
import java.util.Random;

import Controller.TGCT;

public class TGM {
    ArrayList<Ball> balls;
    TGCT gameController;

    public TGM(TGCT gameController) {
        this.gameController = gameController;
        this.balls = new ArrayList<Ball>();
        addBall();
    }


    public void addBall() {
        Random random = new Random();
        // Se crea una nueva bola
        Ball newBall = new Ball(this, random.nextInt(0,10), random.nextInt(0,10), random.nextInt(0,100), random.nextInt(0,100), random.nextInt(5,20 ));
        // el Thread se encarga de generar bolas
        Thread thread = new Thread(newBall);
        thread.start();
        // Se añade la bola al array de bolas
        this.balls.add(newBall);

    }

    public boolean collideDetection(Ball ball) {
        //todavia no se ha moviod por lo tanto hay que agarrar la posicion 
        //anterior y la actual para ver si choco con algo
        int posx = ball.getPosx()+ball.getVx();
        int posy = ball.getPosy()+ball.getVy();
        if (posx < 0 || posx > 500) {
            gameController.collide(ball, "x");
            return true;
        }
        if (posy < 0 || posy > 500) {
            gameController.collide(ball, "y");
            return true;
        }

        //si no choco con los bordes ahora hay que ver si choco con otra bola
        for(Ball ball2: balls){
            if(ball2 != ball){
                if(ball2.getPosx() == posx && ball2.getPosy() == posy){
                    gameController.collide(ball, ball2);
                    return true;
                }
            }
        }

        //si no entra en ningun if es porque no choco con nada
        return false;

    }

    private void lostBall(Ball ball) {
        // TODO: lostBall

    }

    public ArrayList<Ball> getBalls() {
        return this.balls;
    }

    public void setBalls(ArrayList<Ball> balls) {
        this.balls = balls;
    }

}

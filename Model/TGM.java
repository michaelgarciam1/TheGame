package Model;

import java.util.ArrayList;
import java.util.Random;

import Controller.TGCT;

public class TGM {
    ArrayList<Ball> balls;
    TGCT gameController;
    ArrayList <Thread> threads;

    public TGM(TGCT gameController) {
        this.gameController = gameController;
        this.balls = new ArrayList<Ball>();
        this.threads = new ArrayList<Thread>();
        addBall();
    }


    public void addBall() {
        Random random = new Random();
        // Se crea una nueva bola
        Ball newBall = new Ball(this, random.nextInt(0,10), random.nextInt(0,10), random.nextInt(0,200), random.nextInt(0,200), random.nextInt(5,20 ));
        // el Thread se encarga de generar bolas
        Thread thread = new Thread(newBall);
        threads.add(thread);
        thread.start();
        // Se añade la bola al array de bolas
        this.balls.add(newBall);

    }
    public void removeBall(Ball ball){
        this.balls.remove(ball);

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
        ArrayList<Ball> ballsCopy = this.getBalls();
        //si no choco con los bordes ahora hay que ver si choco con otra bola
        for (Ball ball2 : ballsCopy) {
            if(ball2 == null){
                continue;
            }
            if (ball2 != ball) {
                double distanciaCentros = Math.sqrt(Math.pow((ball2.getPosx() - ball.getPosx()), 2)
                        + Math.pow((ball2.getPosy() - ball.getPosy()), 2));
        
                double sumaRadios = ball.getRadius() + ball2.getRadius();
        
                if (distanciaCentros <= sumaRadios) {
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

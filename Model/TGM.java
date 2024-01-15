package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import Controller.TGCT;

public class TGM {
    private List<Ball> balls = new CopyOnWriteArrayList<>();
    TGCT gamecontroler;

    public TGM(TGCT gamecontroler) {
        this.gamecontroler = gamecontroler;
        for (int i = 0; i < 3000; i++) {

            this.addBall();
        }
    }

    public void addBall() {
        Ball newBall = new Ball(this);
        // el Thread se encarga de generar bolas
        Thread thread = new Thread(newBall);
        thread.start();
        this.balls.add(newBall);
    }

    public Boolean collideDetection(Ball ball) {
        int posx = ball.getPosx() + ball.getVx();
        int posy = ball.getPosy() + ball.getVy();

        if (posx < 0 || posx > 500) {
            gamecontroler.collide(ball, "x");
            return true;
        }

        if (posy < 0 || posy > 500) {
            gamecontroler.collide(ball, "y");
            return true;
        }
        // for (Ball ball2 : balls) {
        //     if (ball != ball2) {
        //         double distance = Math.sqrt(Math.pow(ball2.getPosx() - posx,
        //                 2) + Math.pow(ball2.getPosy() - posy, 2));
        //         if (distance <= ball.getRadius() + ball2.getRadius()) {
        //             gamecontroler.collide(ball, ball2);
        //             return true;
        //         }
        //     }
        // }
        return false;
    }

    private void lostBall(Ball ball) {
        // TODO: lostBall

    }

    public void removeBall(Ball ball) {
        this.balls.remove(ball);
    }

    public List<Ball> getBalls() {
        return this.balls;
    }

    public void setBalls(ArrayList<Ball> balls) {
        this.balls = balls;
    }

}

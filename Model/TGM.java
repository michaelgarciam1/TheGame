package Model;

import java.util.ArrayList;
import java.util.Random;

import Controller.TGCT;

public class TGM {
    ArrayList<Ball> balls;
    TGCT gamecontroler;

    public TGM(TGCT gamecontroler) {
        this.gamecontroler = gamecontroler;
        this.balls = new ArrayList<Ball>();
        addBall();
    }

    public void addBall() {
        Ball newBall = new Ball(this);
        // el Thread se encarga de generar bolas
        Thread thread = new Thread(newBall);
        thread.start();
        this.balls.add(newBall);

    }

    public Boolean collideDetection(Ball ball) {
        int posx = ball.getPosx()+ball.getVx();
        int posy = ball.getPosy()+ball.getVy();

        if (posx < 0 || posx > 500) {
            gamecontroler.collide(ball, "x");
            return true;
        }

        if (posy < 0 || posy > 500) {
            gamecontroler.collide(ball, "y");
            return true;
        }
        for(Ball ball2 : balls){
            if(ball != ball2){
               if(ball2.getPosx()==posx && ball2.getPosy()==posy){
                    gamecontroler.collide(ball, ball2);
                    return true;
                }
            }
        }
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

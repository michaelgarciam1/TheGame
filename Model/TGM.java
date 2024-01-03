package Model;

import java.util.ArrayList;

public class TGM {
    ArrayList<Balls> balls;

    public TGM(ArrayList<Balls> balls) {
        this.balls = balls;
    }

    public void addBall() {
        Balls newBall = new Balls(0, 0, 0, 0, 0);
        // el Thread se encarga de generar bolas
        Thread thread = new Thread(newBall);
        thread.start();
        this.balls.add(newBall);

    }

    private void collideDetection(Balls ball) {
        // TODO: collideDetection

    }

    private void lostBall(Balls ball) {
        // TODO: lostBall

    }

    public ArrayList<Balls> getBalls() {
        return this.balls;
    }

    public void setBalls(ArrayList<Balls> balls) {
        this.balls = balls;
    }

}

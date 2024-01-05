package Model;

import java.util.ArrayList;

public class TGM {
    ArrayList<Ball> balls;

    public TGM() {
        this.balls = new ArrayList<Ball>();
        addBall();
    }

    public void addBall() {
        Ball newBall = new Ball(1, 3, 100, 100, 10);
        // el Thread se encarga de generar bolas
        Thread thread = new Thread(newBall);
        thread.start();
        this.balls.add(newBall);

    }

    private void collideDetection(Ball ball) {
        // TODO: collideDetection

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

package Model;

import java.util.ArrayList;

public class TGM {
    ArrayList<Ball> balls;

    public TGM() {
        this.balls = new ArrayList<Ball>();
        addBall();
    }

    public void addBall() {
        // Se crea una nueva bola
        Ball newBall = new Ball(3, 5, 100, 100, 15);
        // el Thread se encarga de generar bolas
        Thread thread = new Thread(newBall);
        thread.start();
        // Se añade la bola al array de bolas
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

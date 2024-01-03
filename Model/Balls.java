package Model;

public class Balls implements Runnable {
    private int velocity;
    private int acceleration;
    private int position;
    private int mass;
    private int radius;

    public Balls(int velocity, int acceleration, int position, int mass, int radius) {
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.position = position;
        this.mass = mass;
        this.radius = radius;
    }

    private void bounce() {
        int width = 600;
        int height = 800;
        velocity += acceleration;
        position += velocity;

        // Si la bola toca el lado izquierdo o derecho, invertir la velocidad
        if (position < 0 || position > width) {
            velocity = -velocity;
        }
        // Si la bola toca el techo o el suelo, invertir la velocidad
        if (position < 0 || position > height) {
            velocity = -velocity;
        }
        // Actualizar la interfaz gráfica con la nueva posición de la bola

    }

    public int getVelocity() {
        return this.velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public int getAcceleration() {
        return this.acceleration;
    }

    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getMass() {
        return this.mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public int getRadius() {
        return this.radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void run() {
        while (true) {
            bounce();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

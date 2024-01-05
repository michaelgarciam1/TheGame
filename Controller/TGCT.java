package Controller;

import Model.TGM;
import View.TGV;

public class TGCT {
    TGV view;
    TGM model;

    public TGCT() {
        // Inicialización del modelo y la vista
        this.model = new TGM();
        // A la vista se le pasa el controlador para que pueda acceder al modelo
        this.view = new TGV(this);

    }

    public void play() {
        // cuando llamamos a addBall se crea una nueva bola y luego se incia un nuevo
        // hilo para ejecutar esa misma bola
        this.model.addBall();
    }

    public static void main(String[] args) {
        TGCT controller = new TGCT();
        // Se crean dos bolas y se inicia un hilo para cada una
        controller.play();

        // Thread thread = new Thread(controller.getView());
        // thread.start();
    }

    public TGV getView() {
        return this.view;
    }

    public void setView(TGV view) {
        this.view = view;
    }

    public TGM getModel() {
        return this.model;
    }

    public void setModel(TGM model) {
        this.model = model;
    }

}
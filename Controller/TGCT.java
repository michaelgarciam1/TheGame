package Controller;

import Model.TGM;
import View.TGV;

public class TGCT {
    TGV view;
    TGM model;
    TGPCT gamePlayController;

    public TGCT(TGPCT gamePlayController) {
        this.gamePlayController = gamePlayController;
        // Inicialización del modelo y la vista
        this.model = new TGM(this);
        // A la vista se le pasa el controlador para que pueda acceder al modelo
        this.view = new TGV(this);

    }

    public void play() {
        // cuando llamamos a addBall se crea una nueva bola y luego se incia un nuevo
        // hilo para ejecutar esa misma bola
        this.model.addBall();
    }

    public void collide(Object object1, Object object2){
        this.gamePlayController.collide(object1, object2);
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
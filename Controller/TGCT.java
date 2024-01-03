 package Controller;

import java.util.ArrayList;

import Model.Balls;
import Model.TGM;
import View.TGV;

public class TGCT {
    TGV view;
    TGM model;


    public TGCT() {
        ArrayList<Balls> balls = new ArrayList<Balls>();
        this.view = new TGV(balls);
        this.model = new TGM(balls);
    }
    public void play(){
        //cuando llamamos a addBall se crea una nueva bola y luego se incia un nuevo hilo para ejecutar esa misma bola
        this.model.addBall();
    }
    public static void main(String[] args) {
        TGCT controller = new TGCT();
        Thread thread=new Thread(controller.getView());
        thread.start();    
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
package Controller;

public class TGPCT {

    TGR rules;
    public TGPCT() {
        rules= new TGR();
        TGCT game = new TGCT(this);
        
    }

    public static void main(String[] args) {
        TGPCT game = new TGPCT();
    }

    public void collide(Object o1, Object o2){
        rules.collide(o1, o2);
    }
}

package Controller;

public class TGPCT {
    TGR rules;
    public TGPCT() {
        TGCT controller = new TGCT(this);
        rules = new TGR();
    }
    public static void main(String[] args) {
        TGPCT gamePlayController = new TGPCT();     
    }
    public void collide(Object object1, Object object2){
        this.rules.collide(object1, object2);
    }
    
}

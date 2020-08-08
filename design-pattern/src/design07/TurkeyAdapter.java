package design07;

public class TurkeyAdapter implements Duck {
    Turkey turkey;

    public TurkeyAdapter(Turkey turkey){
        this.turkey = turkey;
    }

    public void fly() {
        turkey.fly();
    }

    public void quake() {
        turkey.gobble();
    }
}

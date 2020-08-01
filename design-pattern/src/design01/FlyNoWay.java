package design01;

import design01.FlyBehavior;

public class FlyNoWay implements FlyBehavior {
    public void fly() {
        System.out.println("I cant't fly");
    }
}

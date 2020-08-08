package design04;

import java.util.ArrayList;

public abstract class Pizza {
    String name;
    String dough;
    String sauce;
    ArrayList toppings = new ArrayList();

    public void prepare(){
        System.out.println("Preparing " + name);
        System.out.println("Tossing dough... " + name);
        System.out.println("Adding sauce");
        System.out.println("Adding toppings: ");
        for (int i = 0; i < toppings.size(); i++){
            System.out.print(" "+toppings.get(i));
        }
    }
    void bake(){
        System.out.println("Bake for 25 minutes at 350");
    }
    void cut(){
        System.out.println("Cutting the pizza into diagonal slices");
    }
    void box(){
        System.out.println("Place pizza in offical PizzaStore box");
    }

    public String getName(){
       return name;
    }

}

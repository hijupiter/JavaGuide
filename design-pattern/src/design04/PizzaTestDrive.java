package design04;

public class PizzaTestDrive {
    public static void main(String[] args){
        PizzaStore02 nyStore = new NYPizzaStore();
        Pizza pizza = nyStore.orderPizza("cheese");
        System.out.println("Ethan ordered a " + pizza.getName() + "\n");
    }

}

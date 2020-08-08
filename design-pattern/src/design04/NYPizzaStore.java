package design04;

public class NYPizzaStore extends PizzaStore02{


    Pizza createPizza(String item) {
        if (item.equals("cheese")) {
            return new NYStyleCheesePizza();
        } else return null;
    }

}

package design04;

public abstract  class PizzaStore02 {

    public Pizza orderPizza(String type){
        Pizza pizza;
        pizza = createPizza(type);
        pizza.prepare();
        pizza.bake();
        return pizza;
    }

    abstract  Pizza createPizza(String type);
}

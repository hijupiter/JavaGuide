package design04;

/*简单的工厂模式
* 相对工厂方法来说缺乏弹性，不能变更正在创建的产品
* */
public class PizzaStore {
    SimplePizzaFactory factory;

    public PizzaStore(SimplePizzaFactory factory){
        this.factory = factory;
    }

    public Pizza orderPizza(String type){
        Pizza pizza;
        pizza = factory.createPizza(type);
        pizza.prepare();
        pizza.bake();
        return pizza;
    }

}

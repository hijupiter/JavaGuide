## 观察者模式
为交互对象之间的松耦合设计而努力  
在对象之间定义一对多的以来、这样以来，当一个对象改变状态，依赖它的对象都会收到通知，并自动更新。
### 重点
主题（可观察者）用一个共同的接口来更新观察者  
观察者和可观察者之间用松耦合方式结合，可观察者不知道观察者的细节，只知道观察者实现了观察者接口

## 装饰者模式
设计原则：类应该对扩展开放，对修改关闭  
遵循开放-关闭原则  

装饰者和被装饰对象有相同的超类型（利用继承获得类型匹配）   
你可以用一个或多个装饰者包装一个对象  
既然装饰者和被装饰对象有相同的超类型，所以在任何需要原始对象（被包装的）的场合，可以用装饰过的对象代替它。  
被装饰者可以在所委托被装饰者的行为之前与/或之后，加上自己的行为，以达到特定的目的。 


## 工厂模式
所有工厂模式都用来封装对象的创建  
工厂方法模式通过让子类决定该创建的对象是什么，来达到将对象创建的过程封装的目的。

### 定义
工厂方法模式定义了一个创建对象的接口，但由子类决定要实例化的类是哪一个。工厂方法让类把实例化推迟到子类。
在编写创建者类时，不需要知道实际创建的产品是哪一个。选择了那个子类，自然就决定了实际创建的产品是什么。

### 依赖倒置
要依赖抽象，不要依赖具体类

当PizzaStore直接实例化一个对象时，就是在依赖它的具体类，这个PizzaStore依赖于所有的比萨对象，因为它直接创建这些
披萨对象。每增加一个披萨种类，就等于让PizzaStore多了一个依赖。

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
    
#### 依赖倒置原则
设计原则：要依赖抽象，不要依赖具体类
不能让高层组件依赖低层组件；而且，不管高层还是底层，“两者”都应该依赖于抽象
PizzaStore就是一个高层组建，因为它是一个由其他低层组建定义其行为的类。在这个例子中，Pizza类就是底层组建。PizzaStore
依赖于这些具体披萨类。
    
    public abstract  class PizzaStore02 {
    public Pizza orderPizza(String type){
        Pizza pizza;
        pizza = createPizza(type);
        pizza.prepare();
        pizza.bake();
        return pizza;
    }
    abstract  Pizza createPizza(String type);}

现在，高层组建PizzaStore02和orderPizza都依赖了Pizza抽象。

### 抽象工厂
加入一个工厂来生产原料

    public interface PizzaIngredientFactory {
        public Dough createDough();
        public Sauce createSauce();
    }

通过抽象工厂所提供的接口，可以创建产品的家族，利用这个接口书写代码，我们的代码将从实际工厂解耦，以便
在不同上下文中实现各式各样的工厂，制造出各种不同的产品。   
生产各种各样原料由实现的具体原料工厂决定
     
    class NYPizzaIngredientFactory implements PizzaIngredientFactory {
        public Dough createDough(){
            return new ThinCrustDough();
        }
        public Sauce createSauce(){
            return new MarinaraSauce();
        }
    }
    
重做披萨
    
    public abstract class Pizza{
           String name;
           Dough dough;
           Sauce sauce;
           abstract void prepare();
           
       void bake(){
   
       }
   
       void cut(){}
       void box(){}
       void setName(String name){
           this.name = name;
       }
   
       String getName(){ return name;}
   
    } 
    
继续重做比萨
    
    class CheesePizza extends Pizza {
         PizzaIngredientFactory ingredientFactory;
         public CheesePizza(PizzaIngredientFactory ingredientFactory){
             this.ingredientFactory = ingredientFactory;
         }
         void prepare(){
             System.out.println("Preparing" + name);
             dough = ingredientFactory.createDough();
             sauce = ingredientFactory.createSauce();
         }
     }
     
再回到披萨店
    
    class NYPizzaStore extends PizzaStore{
        protected Pizza02 createPizza(String item){
            Pizza02 pizza = null;
            PizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();
            if (item.equals("cheese")){
                pizza = new CheesePizza02(ingredientFactory);
                pizza.setName("New York Style Cheese Pizza");
            }
            return pizza;
        }
     }
     
#### 工厂方法和抽象工厂的不同

工厂方法：客户只需知道他们所使用的抽象类型就可以了，而由子类来负责决定具体类型
抽象工厂：提供一个用来创建一个产品家族的抽象类型，这个类型的子类定义了产品被产生的方法，要使用这个工厂
必须先实例化它，然后将它传入一些针对抽象类型所写的代码中。


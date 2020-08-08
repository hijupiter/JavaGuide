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

## 单件模式

确保一个类只有一个实例，并提供一个全局访问点

    public class Singleton {
        private static Singleton uniqueInstance;
        private Singleton(){}
        public static Singleton getInstance(){
            if (uniqueInstance == null){
                uniqueInstance = new Singleton();
            }
            return uniqueInstance;
        }
    }

会出现一个问题，多线程的时候可能同时执行getInstance，使得两个线程同时被执行
        
        public class Singleton {
            private static Singleton uniqueInstance;
            private Singleton(){}
            public static synchronized Singleton getInstance(){
                if (uniqueInstance == null){
                    uniqueInstance = new Singleton();
                }
                return uniqueInstance;
            }
        }
        
加入同步锁之后效率会下降，其实我们只需要在第一次执行此方法的时候，才需要真正的同步。
使用“急切”创建实例代替“延迟”实例化的做法

        public class Singleton {
            private static Singleton uniqueInstance = new Singleton();
            
            private Singleton(){}
            
            public static synchronized Singleton getInstance(){
                return uniqueInstance;
            }
        }

在静态初始化器中创建单件。这段代码保证了线程安全。JVM在加载这个类时会马上创建此唯一的单件实例。JVM保证
在任何线程访问uniqueInstance静态变量之前，一定要先创建此实例。
        
        public class Singleton {
            private static Singleton uniqueInstance = new Singleton();
            
            private Singleton(){}
            
            public static synchronized Singleton getInstance(){
                return uniqueInstance;
            }
        }
        
另一种技巧是，使用 “双重检查加锁”， 在getInstance()中减少使用同步

        public class Singleton {
            private volatile static Singleton uniqueInstance;
            
            private Singleton(){}
            
            public static Singleton getInstance(){
                if(uniqueInstance==null){
                synchronized(Singleton.class){
                if (uniqueInstance == null){
                    uniqueInstance = new Singleton();}
                 }}
                 return unqiueInstance;   
            }       
        }

volatile关键词确保:当uniqueInstance变量被初始化成Singleton实例时，多个线程正确地处理uniqueInstance变量

## 封装调用
### 第一个命令对象
#### 实现命令接口
    
    public interface Command {
        public void execute();
    }

#### 实现一个打开电灯的命令
    public class LightOnCommand implements Command {
        Light light;
        
        public LightOnCommand(Light light){
            this.light = light;
        }
        
        public void execute(){
            light.on();
        }
    }
    
#### 使用命令对象
    public class SimpleRemoteControl {
        Command slot;
        
        public SimpleRemoteControl(){}
        
        public void setCommand(Command command){
            slot = command;
        }
        
        public void buttonWasPressed(){
            slot.execute();
        }
    }
    
#### 一个简单的测试

    public class RemoteControlTest {
    
        public static void main(String[] args){
            Light light = new Light();
            Command lightOn = new LightOnCommand(light);
            SimpleRemoteControl remote = new SimpleRemoteControl();
            remote.setCommand(lightOn);
            remote.buttonWasPressed();
        }
    }

### 定义命令模式

命令模式将 “请求” 封装成对象，以便使用不同的请求、队列或者日志来参数化其他对象。
命令模式也支持可撤销的操作

#### 实现遥控器

    public class RemoteControl {
        Command[] onCommands;
        Command[] offCommands;
        
        public RemoteControl(){
            onCommands = new Command[7];
            offCommands = new Command[7];
            
            Command noCommand = new NoCommand();
            for(int i =0; i<7;i++){
            onCommand[i] = noCommand;
            offCommand[i] = noCommand;}
        }
        
        public void setCommand(int slot, Command onCommand, Command offCommand){
            onCommands[slot] = onCommand;
            offCommands[slot] = offCommand;
        }
        
        public void onButtonWasPushed(int slot){
            onCommands[slot].execute();
        }
        
        public void offButtonWasPushed(int slot){
            offCommands[slot].execute();
        }
        
        public String toString(){
            StringBuffer stringBuff = new StringBuffer();
            stringBuff.append("\n ------Remote Control -----\n");
            for(int i = 0; i < onCommands.length;i++){
                stringBuff.append("[slot " + i +"]" + onCommands[i].getClass().getName() + "  " +  onCommands[i].getClass().getName() + "\n"); 
            }
            return stringBuff.toString();
        }
        
    }
   
#### 命令模式的用途：队列请求

实现命令接口的对象被放进队列
线程从队列中一个个地删除命令对象，然后调用命令对象的execute()方法。一旦完成了，就会再去处理下一个新的命令对象。
工作队列类和进行计算的对象之间完全是解耦的。此刻线程可能在进行财务运算，下一刻却在读取网络数据。
工作队列对象不在乎到底做些什么，他们只知道取出命令对象，然后调用其execute()方法。

#### 要点

当需要将发出请求的对象和执行请求的对象解耦的时候，使用命令模式。
命令模式--将请求封装成对象，这可以让你使用不同的请求、队列或者日志请求来参数化其他对象，这可以让你
使用不同的请求、队列，或者日志请求来参数化其他对象。命令模式也可以支持撤销操作。

## 适配器模式与外观模式

OO适配器：将一个借口转换成另一个接口，以符合客户的期望。

    public interface Duck {
        public void quake();
        public void fly();
    }
    
    public interface Turkey {
        public void gobble();
        public void fly();
    }

假如我们要用Turkey冒充是Duck对象

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

### 定义适配器模式

适配器模式将一个类的接口，转换成客户期望的另一个接口。适配器让原本接口不兼容的类可以合作无间。

### 外观模式

外观模式改变接口的原因是为了简化接口。
在提供简化的接口的同时，依然将系统完整的功能暴露出来，以供需要的人使用。

子系统可以创建许多个外观。

外观不只是简化了接口，也将客户从组件的子系统中解耦。

#### OO原则
“最少知识” 原则：只和你的密友谈话。

就任何对象而言，在该对象的方法内，我们只应该调用属于以下范围的方法：
+ 该对象本身
+ 被当做方法的参数而传递进来的对象
+ 此方法所创建或实例化的任何对象
+ 独享的任何组件

不采用这个原则：
    
    public float getTemp(){
        Thermometer thermometer = station.getThermometer();
        return thermometer.getTemperature();
    }

采用这个原则：

    public float getTemp(){
        return station.getTemperature();
    }

## 模板方法模式
封装算法

*模板方法模式*在一个方法中定义一个算法的股价，而将一些步骤延迟到子类中。模板方法使得子类可以在不改变算法
结构的情况下，重新定义算法中的某些步骤。

    abstract class AbstractClass {
        final void templateMethod(){
            primitiveOperation1();
            primitiveOperation2();
            concreteOperation();
            hook();
        }
        
        abstract  void primitiveOperation1();
        abstract  void primitiveOperation2();
        final void concreteOperation(){};
        void hook(){};
    }
    
hook() 钩子方法。子类可以视情况决定要不要覆盖他们。

### 对模板进行挂钩

钩子是一种在抽象类中声名的方法。但只有空或者默认的实现。
钩子的存在，可以让子类有能力对算法的不同点进行挂钩。要不要挂钩，由子类自行决定。

#### 使用钩子
    public abstract  class CaffeineBeverageWithHook {
        
        void prepareRecipe(){
            boilWater();
            brew();
            pourInCup();
            if(customerWantsCondiments()){
                addCondiments();
            }
        }
        
        abstract void brew();
        
        abstract void addCondiments();
        
        void boilWater(){
            System.out.println("Boiling water");
        }
        
        void pourInCup(){
            System.out.println("Pouring into cup");
        }
        
        boolean customerWantsCondiments(){
            return true;
        }
    }
    
#### 使用钩子

    public class CoffeWithHook extends CaffeineBeverageWithHook {
        
        public void brew(){
            System.out.println("Dripping Coffee through filter");
        };
        
        public void addCondiments(){
            System.out.println("Adding Sugar and Milk");
        }
        
        public boolean customerWantsCondiments(){
            String answer = getUserInput();
            if (answer.toLowerCase().startsWith("y")){
                return true;
            }else{
                return false;
            }
        }
        
        private String getUserInput(){
            String answer = null;
            System.out.print("Would you like milk and sugar with your coffee (y/n)? ");
    
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            try{
                answer = in.readLine();
            }catch (IOException ioe){
                System.err.println("IO error tyring to read your answer");
            }
            if ( answer == null){
                return "no";
            }
            return answer;
        }
    }

#### 钩子的目的
+ 让子类实现算法中可选的部分
+ 在钩子对于子类的实现并不重要的时候，子类可以对此钩子置之不理。
+ 钩子让子类能够有机会对模板方法中某些即将发生的（或者刚刚发生的）步骤做出反应。


### 好莱坞原则
原则：别调用我们，我们会调用你
我们允许低层组件将自己挂钩到系统上，但是高层组件会决定什么时候和怎么样使用这些低层组件。

使用钩子的例子

    public class MyFrame extends JFrame {
    
        public MyFrame(String title){
            super(title);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
            this.setSize(300,300);
            this.setVisible(true);
        }
    
        public void paint(Graphics graphics){
            super.paint(graphics);
            String msg = "I rule!!!";
            graphics.drawString(msg, 100, 100);
        }
    
        public static void main(String[] args){
            MyFrame myFrame = new MyFrame("Head First Design Patterns");
        }
        
    }

## 迭代器与组合模式
依赖于一个名为迭代器的接口。有hasNext(),next()方法。
一旦我们有了这个接口，就可以为各种对象集合实现迭代数：数组、列表、散列表等

迭代器模式提供一种方法顺序访问一个聚合对象中的各个元素，而又不暴露其内部的表示。
















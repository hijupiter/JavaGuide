package design03;

public class Soy extends CondimentDecorator {

    Beverage beverage;

    public Soy(Beverage beverage){
        this.beverage = beverage;
    }

    public String getDescription(){
        return beverage.getDescription() + ", Soy";
    }

    public String getSize(){
        return beverage.getSize();
    }

    public double cost(){
        double cost = beverage.cost();
        if(getSize() == beverage.TALL){
            cost += .10;
        }else if(getSize() == beverage.GRANDE){
            cost += .15;
        }else if (getSize() == beverage.VENTI){
            cost += .20;
        }
        return cost;
    }
}

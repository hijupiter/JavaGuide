package design03;

public abstract class Beverage {
    String description = "Unkonwn Beverage";
    String TALL = "tall";
    String GRANDE = "grande";
    String VENTI = "venti";
    String size;

    public String getDescription(){
        return description;
    }

    public String setSize(String size){
        return this.size = size;
    }

    public String getSize(){
        return size;
    }
    public abstract double cost();
}

package design02;

public class CurrentCoinditionsDisplay implements DisplayElement,Observer {

    private Subject weatherData;
    private float humidity;
    private float temperature;

    public CurrentCoinditionsDisplay(Subject weatherData){
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    public void display(){
        System.out.println("Current conditions:" + temperature + "F degrees and" + humidity + "% humidity");
    }

    public void update(float temperature, float humidity, float pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }
}

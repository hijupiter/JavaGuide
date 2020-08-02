package design02;

import java.util.Observable;
import java.util.Observer;

public class CurrentConditionsDisplay02 implements DisplayElement, Observer {

    private Observable observable;
    private float humidity;
    private float temperature;

    public CurrentConditionsDisplay02(Observable observable){
        this.observable = observable;
        observable.addObserver(this);
    }

    public void display(){
        System.out.println("Current conditions:" + temperature + "F degrees and" + humidity + "% humidity");
    }

    public void update(Observable obs, Object args){
        if (obs instanceof WeatherData02){
            WeatherData02 weatherData = (WeatherData02)obs;
            this.temperature = weatherData.getTemperature();
            this.humidity = weatherData.getHumidity();
            display();
        }
    }
}

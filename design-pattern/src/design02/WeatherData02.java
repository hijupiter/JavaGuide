package design02;

import java.util.Observable;

public class WeatherData02 extends Observable{
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData02(){
    }


    public void measurementsChanges(){
        setChanged();
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanges();
    }

    public float getTemperature(){
        return temperature;
    }

    public float getHumidity(){
        return humidity;
    }

    public float getPressure(){
        return pressure;
    }
}

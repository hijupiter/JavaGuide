package design02;

public class WeatherStation02 {
    public static void main(String[] args){
        WeatherData02 weatherData02 = new WeatherData02();

        CurrentConditionsDisplay02 currentDisplay = new CurrentConditionsDisplay02(weatherData02);
        weatherData02.setMeasurements(80,65,30.2f);
    }
}

package design02;

public class WeatherStation {
    public static void main(String[] args){
        WeatherData weatherData = new WeatherData();

        CurrentCoinditionsDisplay currentDisplay = new CurrentCoinditionsDisplay(weatherData);
        weatherData.setMeasurements(80,65,30.2f);
    }
}

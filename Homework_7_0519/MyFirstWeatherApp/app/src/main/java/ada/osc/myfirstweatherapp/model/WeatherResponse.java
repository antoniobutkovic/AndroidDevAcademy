package ada.osc.myfirstweatherapp.model;

public class WeatherResponse {
    private Weather[] weather = new Weather[1];
    private Main main;
    private Wind wind;
    private String dtTxt;
    private String cityName;

    public WeatherResponse(Weather[] weather, Main main, Wind wind, String dtTxt, String cityName) {
        this.weather = weather;
        this.main = main;
        this.wind = wind;
        this.dtTxt = dtTxt;
        this.cityName = cityName;
    }

    public WeatherResponse() {
    }

    public Main getMain() {
        return main;
    }

    public Wind getWind() {
        return wind;
    }

    public Weather getWeatherObject() {
        return weather[0];
    }

    public String getDtTxt() {
        return dtTxt;
    }

    public void setWeatherObject(Weather weatherObject) {
        this.weather[0] = weatherObject;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}

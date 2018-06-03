package ada.osc.myfirstweatherapp.presentation;

import ada.osc.myfirstweatherapp.view.weather.WeatherView;

/**
 * Created by Toni on 5/30/2018.
 */

public interface WeatherPresenter extends BasePresenter<WeatherView>{

    void getWeatherInfo(String locationName);

}

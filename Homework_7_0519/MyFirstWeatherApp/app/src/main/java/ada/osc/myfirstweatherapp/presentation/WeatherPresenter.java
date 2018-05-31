package ada.osc.myfirstweatherapp.presentation;

import android.view.View;

import ada.osc.myfirstweatherapp.view.Weather.WeatherView;

/**
 * Created by Toni on 5/30/2018.
 */

public interface WeatherPresenter {

    void setView(WeatherView weatherView);

    void getWeatherInfo(String locationName);

}

package ada.osc.myfirstweatherapp.view.weather;

import ada.osc.myfirstweatherapp.model.WeatherResponse;

/**
 * Created by Toni on 5/30/2018.
 */

public interface WeatherView {

    void updateUI(WeatherResponse weatherResponse);

    void onFailure();

}

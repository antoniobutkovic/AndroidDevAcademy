package ada.osc.myfirstweatherapp.view.Weather;

import java.util.List;

import ada.osc.myfirstweatherapp.model.Location;
import ada.osc.myfirstweatherapp.model.Main;
import ada.osc.myfirstweatherapp.model.WeatherResponse;

/**
 * Created by Toni on 5/30/2018.
 */

public interface WeatherView {

    void updateUI(WeatherResponse weatherResponse);

    void onFailure();

}

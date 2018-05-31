package ada.osc.myfirstweatherapp.interaction;

import ada.osc.myfirstweatherapp.model.WeatherResponse;
import retrofit2.Callback;

/**
 * Created by Toni on 5/30/2018.
 */

public interface ApiInteractor {

    void getWeatherInfo(Callback<WeatherResponse> getAllLocationsCallback, String locationName);

}

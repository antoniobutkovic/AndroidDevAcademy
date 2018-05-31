package ada.osc.myfirstweatherapp.interaction;

import android.util.Log;

import ada.osc.myfirstweatherapp.Constants;
import ada.osc.myfirstweatherapp.model.WeatherResponse;
import ada.osc.myfirstweatherapp.network.ApiService;
import retrofit2.Callback;

/**
 * Created by Toni on 5/30/2018.
 */

public class ApiInteractorImpl implements ApiInteractor{

    private ApiService apiService;

    public ApiInteractorImpl(ApiService apiService){
        this.apiService = apiService;
    }

    @Override
    public void getWeatherInfo(Callback<WeatherResponse> callback, String locationName) {
        apiService.getWeather(Constants.APP_ID, locationName).enqueue(callback);
    }
}

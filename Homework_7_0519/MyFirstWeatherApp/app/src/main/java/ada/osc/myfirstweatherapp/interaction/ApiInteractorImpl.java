package ada.osc.myfirstweatherapp.interaction;

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
    public void getLocationInfo(Callback<WeatherResponse> getLocationInfoCallback) {

    }
}

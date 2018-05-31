package ada.osc.myfirstweatherapp.presentation;

import android.util.Log;
import android.view.View;

import java.util.List;

import ada.osc.myfirstweatherapp.interaction.ApiInteractor;
import ada.osc.myfirstweatherapp.interaction.RoomCallback;
import ada.osc.myfirstweatherapp.interaction.RoomInteractor;
import ada.osc.myfirstweatherapp.model.Location;
import ada.osc.myfirstweatherapp.model.WeatherResponse;
import ada.osc.myfirstweatherapp.network.ApiService;
import ada.osc.myfirstweatherapp.view.Weather.WeatherView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Toni on 5/30/2018.
 */

public class WeatherPresenterImpl implements WeatherPresenter{

    private ApiInteractor apiInteractor;
    private RoomInteractor roomInteractor;
    private WeatherView view;

    public WeatherPresenterImpl(ApiInteractor apiInteractor){
        this.apiInteractor = apiInteractor;
    }

    @Override
    public void setView(WeatherView weatherView) {
        this.view = weatherView;
    }

    @Override
    public void getWeatherInfo(String locationName) {
        apiInteractor.getWeatherInfo(getWeatherInfoCallback(), locationName);
    }

    private Callback<WeatherResponse> getWeatherInfoCallback() {
        return new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    WeatherResponse weatherResponse = response.body();
                    view.updateUI(weatherResponse);
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                view.onFailure();
            }
        };
    }
}

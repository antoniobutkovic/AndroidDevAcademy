package ada.osc.myfirstweatherapp.presentation;

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

    public WeatherPresenterImpl(ApiInteractor apiInteractor, RoomInteractor roomInteractor){
        this.apiInteractor = apiInteractor;
        this.roomInteractor = roomInteractor;
    }

    @Override
    public void setView(WeatherView weatherView) {
        this.view = weatherView;
    }

    @Override
    public void getAllLocations() {
        roomInteractor.getAllLocations(roomCallback);
    }

    RoomCallback roomCallback = new RoomCallback() {
        @Override
        public void onLocationAdded() {

        }

        @Override
        public void onReadLocationsSuccess(List<Location> locations) {
            view.setAdapter(locations);
        }
    };

    private Callback<WeatherResponse> getWeatherInfo() {
        return new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {

                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {

            }
        };
    }
}

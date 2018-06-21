package ada.osc.myfirstweatherapp.presentation;

import ada.osc.myfirstweatherapp.Constants;
import ada.osc.myfirstweatherapp.interaction.ApiInteractor;
import ada.osc.myfirstweatherapp.interaction.RoomInteractor;
import ada.osc.myfirstweatherapp.model.WeatherResponse;
import ada.osc.myfirstweatherapp.view.weather.WeatherView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Toni on 5/30/2018.
 */

public class WeatherPresenterImpl implements WeatherPresenter{

    private ApiInteractor apiInteractor;
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
                    prepareData(weatherResponse);
                    view.updateUI(weatherResponse);
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                view.onFailure();
            }
        };
    }

    private void prepareData(WeatherResponse weatherResponse) {
        weatherResponse.getMain().setTemp(toCelsiusFromKelvin(weatherResponse.getMain().getTemp()));
        weatherResponse.getMain().setTempMin(toCelsiusFromKelvin(weatherResponse.getMain().getTempMin()));
        weatherResponse.getMain().setTempMax(toCelsiusFromKelvin(weatherResponse.getMain().getTempMax()));
        weatherResponse.getWeatherObject().setMain(createWeatherIconValue(weatherResponse.getWeatherObject().getMain()));
    }

    private String createWeatherIconValue(String description) {
        if (description != null)
            switch (description) {
                case Constants.SNOW_CASE: {
                    return Constants.SNOW;
                }
                case Constants.RAIN_CASE: {
                    return Constants.RAIN;
                }
                case Constants.CLEAR_CASE: {
                    return Constants.SUN;
                }
                case Constants.MIST_CASE: {
                    return Constants.FOG;
                }
                case Constants.FOG_CASE: {
                    return Constants.FOG;
                }
                case Constants.HAZE_CASE: {
                    return Constants.FOG;
                }
                case Constants.CLOUD_CASE: {
                    return Constants.CLOUD;
                }
            }
            return null;
    }

    private double toCelsiusFromKelvin(double temperature) {
        return temperature - 273;
    }
}

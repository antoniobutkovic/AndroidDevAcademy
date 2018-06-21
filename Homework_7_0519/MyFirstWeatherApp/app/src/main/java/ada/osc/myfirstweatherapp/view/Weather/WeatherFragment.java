package ada.osc.myfirstweatherapp.view.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import ada.osc.myfirstweatherapp.App;
import ada.osc.myfirstweatherapp.Constants;
import ada.osc.myfirstweatherapp.R;
import ada.osc.myfirstweatherapp.model.WeatherResponse;
import ada.osc.myfirstweatherapp.presentation.WeatherPresenter;
import ada.osc.myfirstweatherapp.presentation.WeatherPresenterImpl;
import ada.osc.myfirstweatherapp.util.NetworkUtil;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Filip on 26/03/2016.
 */
public class WeatherFragment extends Fragment implements WeatherView{

    @BindView(R.id.weather_display_current_temperature_text_view)
    TextView currentTemperature;

    @BindView(R.id.weather_fragment_min_temperature_text_view)
    TextView minTemperature;

    @BindView(R.id.weather_fragment_max_temperature_text_view)
    TextView maxTemperature;

    @BindView(R.id.weather_display_pressure_text_view)
    TextView pressure;

    @BindView(R.id.weather_display_wind_text_view)
    TextView wind;

    @BindView(R.id.weather_display_detailed_description_text_view)
    TextView description;

    @BindView(R.id.weather_display_weather_icon_image_view)
    ImageView weatherIcon;

    @Inject
    WeatherPresenter presenter;

    public static WeatherFragment newInstance(String city) {
        Bundle data = new Bundle();
        data.putString(Constants.CITY_BUNDLE_KEY, city);
        WeatherFragment f = new WeatherFragment();
        f.setArguments(data);
        return f;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        App.getComponent().inject(this);
        presenter.setView(this);

    }

    @Override
    public void onStart() {
        super.onStart();
        refreshCurrentData();
    }

    public void setCurrentTemperatureValues(double temperatureValues) {
        currentTemperature.setText(getString(R.string.current_temperature_message, temperatureValues));
    }

    public void setMinTemperatureValues(double minTemperatureValues) {
        minTemperature.setText(getString(R.string.minimum_temperature_message, minTemperatureValues));
    }

    public void setMaxTemperatureValues(double maxTemperatureValues) {
        maxTemperature.setText(getString(R.string.maximum_temperature_message, maxTemperatureValues));
    }

    public void setPressureValues(double pressureValues) {
        pressure.setText(getString(R.string.pressure_message, pressureValues));

    }

    public void setWindValues(double windValues) {
        wind.setText(getString(R.string.wind_speed_message, windValues));
    }

    public void setWeatherIcon(String iconPath) {
        Glide.with(getActivity().getApplicationContext()).load(Constants.IMAGE_BASE_URL + iconPath).into(weatherIcon);
    }

    public void setDescriptionValues(String descriptionValues) {
        description.setText(descriptionValues);
    }

    @Override
    public void updateUI(WeatherResponse weatherResponse) {
        setCurrentTemperatureValues(weatherResponse.getMain().getTemp());
        setMaxTemperatureValues(weatherResponse.getMain().getTempMax());
        setMinTemperatureValues(weatherResponse.getMain().getTempMin());
        setPressureValues(weatherResponse.getMain().getPressure());
        setWindValues(weatherResponse.getWind().getSpeed());
        setDescriptionValues(weatherResponse.getWeatherObject().getDescription());
        setWeatherIcon(weatherResponse.getWeatherObject().getMain());
    }

    @Override
    public void onFailure() {
        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.weather_fragment_loading_failure_toast_message), Toast.LENGTH_SHORT).show();
    }

    private void refreshCurrentData() {
        if (NetworkUtil.checkIfInternetConnectionIsAvailable(getActivity())) {
            String locationName = getArguments().getString(Constants.CITY_BUNDLE_KEY);
            presenter.getWeatherInfo(locationName);
        }else {
            Toast.makeText(getActivity().getApplicationContext(), getString(R.string.weather_fragment_no_connection_toast_message), Toast.LENGTH_SHORT).show();
        }
    }
}

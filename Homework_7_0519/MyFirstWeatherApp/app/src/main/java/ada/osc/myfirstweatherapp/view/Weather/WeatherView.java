package ada.osc.myfirstweatherapp.view.Weather;

import java.util.List;

import ada.osc.myfirstweatherapp.model.Location;

/**
 * Created by Toni on 5/30/2018.
 */

public interface WeatherView {

    void setAdapter(List<Location> locations);

}

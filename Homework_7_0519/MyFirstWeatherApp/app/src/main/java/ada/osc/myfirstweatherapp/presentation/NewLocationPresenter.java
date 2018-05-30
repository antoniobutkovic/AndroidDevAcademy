package ada.osc.myfirstweatherapp.presentation;

import ada.osc.myfirstweatherapp.model.Location;
import ada.osc.myfirstweatherapp.view.addLocation.NewLocationView;

/**
 * Created by Toni on 5/29/2018.
 */

public interface NewLocationPresenter {

    void setView(NewLocationView newLocationView);

    void addNewLocation(Location location);

}

package ada.osc.myfirstweatherapp.presentation;


import ada.osc.myfirstweatherapp.view.location.LocationView;

/**
 * Created by Toni on 5/31/2018.
 */

public interface LocationPresenter {

    void setView(LocationView locationView);

    void getAllLocations();

}

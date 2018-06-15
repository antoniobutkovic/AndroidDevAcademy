package ada.osc.myfirstweatherapp.presentation;

import java.util.List;

import ada.osc.myfirstweatherapp.interaction.RoomCallback;
import ada.osc.myfirstweatherapp.interaction.RoomInteractor;
import ada.osc.myfirstweatherapp.model.Location;
import ada.osc.myfirstweatherapp.view.location.LocationView;

/**
 * Created by Toni on 5/31/2018.
 */

public class LocationPresenterImpl implements LocationPresenter{

    private LocationView view;
    private RoomInteractor roomInteractor;

    public LocationPresenterImpl(RoomInteractor roomInteractor){
        this.roomInteractor = roomInteractor;
    }

    @Override
    public void setView(LocationView locationView) {
        this.view = locationView;
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
            view.onLocationAdded(locations);
        }
    };
}

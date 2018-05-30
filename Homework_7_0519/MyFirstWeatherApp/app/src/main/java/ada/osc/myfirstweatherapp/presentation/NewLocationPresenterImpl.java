package ada.osc.myfirstweatherapp.presentation;

import android.util.Log;

import java.util.List;

import ada.osc.myfirstweatherapp.interaction.RoomCallback;
import ada.osc.myfirstweatherapp.interaction.RoomInteractor;
import ada.osc.myfirstweatherapp.model.Location;
import ada.osc.myfirstweatherapp.view.addLocation.NewLocationView;

/**
 * Created by Toni on 5/29/2018.
 */

public class NewLocationPresenterImpl implements NewLocationPresenter{

    private RoomInteractor interactor;
    private NewLocationView view;

    public NewLocationPresenterImpl(RoomInteractor interactor){
        this.interactor = interactor;
    }

    @Override
    public void setView(NewLocationView newLocationView) {
        this.view = newLocationView;
    }

    @Override
    public void addNewLocation(Location location) {
        if (location.getLocation().isEmpty()){
            view.onEmptyStringRequestError();
        }else if (isLocationDuplicated(location)){
            view.onLocationAlreadyExistsError();
        }else {
            interactor.insertLocation(roomCallback, location);
        }
    }

    public boolean isLocationDuplicated(Location location) {
        return false;
    }

    RoomCallback roomCallback = new RoomCallback() {
        @Override
        public void onLocationAdded() {
            view.onSuccess();
        }

        @Override
        public void onReadLocationsSuccess(List<Location> locations) {

        }
    };
}

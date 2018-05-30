package ada.osc.myfirstweatherapp.presentation;

import ada.osc.myfirstweatherapp.interaction.RoomInteractor;
import ada.osc.myfirstweatherapp.model.Location;
import ada.osc.myfirstweatherapp.view.addLocation.NewLocationView;

/**
 * Created by Toni on 5/29/2018.
 */

public class NewLocationPresenterImpl implements NewLocationPresenter{

    private RoomInteractor interactor;
    private NewLocationView newLocationView;

    public NewLocationPresenterImpl(RoomInteractor interactor){
        this.interactor = interactor;
    }

    @Override
    public void setView(NewLocationView newLocationView) {
        this.newLocationView = newLocationView;
    }

    @Override
    public void addNewLocation(Location location) {
        if (location.getLocation().isEmpty()){
            newLocationView.onEmptyStringRequestError();
        }else if (isLocationDuplicated(location)){
            newLocationView.onLocationAlreadyExistsError();
        }else {
            interactor.insertLocation(location);
        }
    }

    public boolean isLocationDuplicated(Location location) {
        return false;
    }
}

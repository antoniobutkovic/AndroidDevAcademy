package ada.osc.myfirstweatherapp.interaction;

import ada.osc.myfirstweatherapp.App;
import ada.osc.myfirstweatherapp.model.Location;
import ada.osc.myfirstweatherapp.persistance.LocationDao;

/**
 * Created by Toni on 5/29/2018.
 */

public class RoomInteractorImpl implements RoomInteractor{

    private final LocationDao locationDao;

    public RoomInteractorImpl(){
         locationDao = App.getLocationDao();
    }

    @Override
    public void insertLocation(Location location) {
        locationDao.insert(location);
    }

    @Override
    public void getLocations() {

    }
}

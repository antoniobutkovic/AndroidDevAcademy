package ada.osc.myfirstweatherapp.interaction;

import ada.osc.myfirstweatherapp.model.Location;

/**
 * Created by Toni on 5/29/2018.
 */

public interface RoomInteractor {

    void insertLocation(RoomCallback roomCallback, Location location);

    void getAllLocations(RoomCallback roomCallback);

    boolean isLocationDuplicated(String location);

}

package ada.osc.myfirstweatherapp.interaction;

import ada.osc.myfirstweatherapp.model.Location;

/**
 * Created by Toni on 5/29/2018.
 */

public interface RoomInteractor {

    interface onLocationInserted{
        void onSuccess();
    }

    void insertLocation(Location location);

    void getLocations();

}

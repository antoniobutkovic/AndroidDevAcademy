package ada.osc.myfirstweatherapp.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.UUID;

/**
 * Created by Filip on 10/02/2016.
 */

@Entity(tableName = "location_table")
public class Location {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String id;
    private String location;

    public Location(String location) {
        id = UUID.randomUUID().toString();
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

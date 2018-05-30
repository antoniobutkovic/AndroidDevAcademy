package ada.osc.myfirstweatherapp.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.UUID;

/**
 * Created by Filip on 10/02/2016.
 */

@Entity(tableName = "task_table")
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
}

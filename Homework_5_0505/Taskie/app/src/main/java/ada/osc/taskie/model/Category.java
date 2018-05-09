package ada.osc.taskie.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.UUID;

/**
 * Created by Toni on 5/8/2018.
 */

@Entity(tableName = "category_table")
public class Category {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String id;
    private String name;
    private String desc;


    public Category(String name, String desc) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

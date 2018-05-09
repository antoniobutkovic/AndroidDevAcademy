package ada.osc.taskie.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.UUID;

@Entity(tableName = "task_table")
public class Task implements Serializable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String mId;
    private String mTitle;
    private String mDescription;
    private boolean mCompleted;
    private TaskPriority mPriority;
    @Embedded(prefix = "cat") private Category category;


    public Task(String title, String description, TaskPriority priority, Category category) {
        mId = UUID.randomUUID().toString();
        mTitle = title;
        mDescription = description;
        mCompleted = false;
        mPriority = priority;
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public boolean isCompleted() {
        return mCompleted;
    }

    public void setCompleted(boolean completed) {
        mCompleted = completed;
    }

    public TaskPriority getPriority() {
        return mPriority;
    }

    public void setPriority(TaskPriority priority) {
        mPriority = priority;
    }
}

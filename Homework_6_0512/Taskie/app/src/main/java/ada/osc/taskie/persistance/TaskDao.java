package ada.osc.taskie.persistance;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ada.osc.taskie.model.Task;

/**
 * Created by Toni on 5/22/2018.
 */

@Dao
public interface TaskDao {

    @Insert
    void insert(Task task);

    @Delete
    void delete(Task task);

    @Update
    void edit(Task task);

    @Query("SELECT * from task_table")
    List<Task> getAllTasks();

}

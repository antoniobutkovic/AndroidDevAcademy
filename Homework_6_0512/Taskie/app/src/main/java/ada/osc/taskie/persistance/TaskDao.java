package ada.osc.taskie.persistance;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;

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

//    @Query("SELECT * from task_table WHERE id = :Id")
//    Task findTaskByPrimaryKey(String Id);
//
//    @Query("SELECT * from task_table ORDER BY mPriority ASC")
//    List<Task> getAllTasks();
}

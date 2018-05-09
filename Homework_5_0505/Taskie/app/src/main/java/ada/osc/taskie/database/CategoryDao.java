package ada.osc.taskie.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import ada.osc.taskie.model.Category;

/**
 * Created by Toni on 5/9/2018.
 */

@Dao
public interface CategoryDao {

    @Insert
    void insert(Category category);

    @Query("SELECT * from category_table")
    List<Category> getAllCategories();

    @Query("SELECT * from category_table WHERE name = :name")
    Category getCategoryByName(String name);

}

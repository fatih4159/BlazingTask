package de.agx.blazingtask.ui.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import de.agx.blazingtask.ui.types.TaskSettings;

import java.util.List;

@Dao
public interface TaskSettingsDao {

    @Query("SELECT * FROM task_settings")
    List<TaskSettings> getAll();

    @Query("SELECT * FROM task_settings WHERE settings_id = :id")
    TaskSettings getById(int id);

    @Insert
    void insert(TaskSettings taskSettings);

    @Update
    void update(TaskSettings taskSettings);

    @Delete
    void delete(TaskSettings taskSettings);

}
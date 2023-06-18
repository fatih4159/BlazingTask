package de.agx.blazingtask.ui.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

import de.agx.blazingtask.ui.types.TaskType;
import io.reactivex.Maybe;

@Dao
public interface TaskTypeDao {
    @Query("SELECT * FROM task_types")
    LiveData<List<TaskType>> getAll();

    @Query("SELECT * FROM task_types WHERE id = :id")
    LiveData<TaskType> getById(int id);

    @Insert
    void insert(TaskType taskType);

    @Update
    void update(TaskType taskType);

    @Delete
    void delete(TaskType taskType);

    @Query("DELETE FROM task_types")
    void deleteAll();
}
package de.agx.blazingtask.ui.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

import de.agx.blazingtask.ui.types.TaskUser;
import io.reactivex.Maybe;

@Dao
public interface TaskUserDao {
    @Query("SELECT * FROM task_user")
    LiveData<List<TaskUser>> getAll();

    @Query("SELECT * FROM task_user WHERE user_id = :userId")
    LiveData<TaskUser> getById(long userId);

    @Insert
    void insert(TaskUser taskUser);

    @Update
    void update(TaskUser taskUser);

    @Delete
    void delete(TaskUser taskUser);

    @Query("DELETE FROM task_user")
    void deleteAll();
}
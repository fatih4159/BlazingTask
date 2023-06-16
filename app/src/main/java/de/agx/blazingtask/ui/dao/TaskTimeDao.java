package de.agx.blazingtask.ui.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import de.agx.blazingtask.ui.types.TaskTime;

import java.util.List;

/**
 * Data Access Object for the TaskTime entity.
 */
@Dao
public interface TaskTimeDao {

    /**
     * Get all TaskTimes from the database.
     *
     * @return a list of TaskTime objects.
     */
    @Query("SELECT * FROM task_time")
    List<TaskTime> getAll();

    /**
     * Get a single TaskTime by ID.
     *
     * @param id the ID of the TaskTime to retrieve.
     * @return the TaskTime object with the specified ID, or null if none exists.
     */
    @Query("SELECT * FROM task_time WHERE id = :id")
    TaskTime getById(int id);

    /**
     * Insert a new TaskTime into the database.
     *
     * @param taskTime the TaskTime object to insert.
     */
    @Insert
    void insert(TaskTime taskTime);

    /**
     * Update an existing TaskTime in the database.
     *
     * @param taskTime the TaskTime object to update.
     */
    @Update
    void update(TaskTime taskTime);

    /**
     * Delete a TaskTime from the database.
     *
     * @param taskTime the TaskTime object to delete.
     */
    @Delete
    void delete(TaskTime taskTime);

    /**
     * Delete all TaskTimes from the database.
     */
    @Query("DELETE FROM task_time")
    void deleteAll();

    @Query("SELECT * FROM task_time")
    LiveData<List<TaskTime>> getAllTaskTimes();

    @Insert
    void insertAll(List<TaskTime> taskTimes);

}
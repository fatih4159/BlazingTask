package de.agx.blazingtask.db;

import android.content.Context;
import androidx.lifecycle.LiveData;

import java.util.List;

import de.agx.blazingtask.ui.dao.TaskSettingsDao;
import de.agx.blazingtask.ui.dao.TaskTimeDao;
import de.agx.blazingtask.ui.dao.TaskTypeDao;
import de.agx.blazingtask.ui.dao.TaskUserDao;
import de.agx.blazingtask.ui.types.TaskSettings;
import de.agx.blazingtask.ui.types.TaskTime;
import de.agx.blazingtask.ui.types.TaskType;
import de.agx.blazingtask.ui.types.TaskUser;
import io.reactivex.Maybe;

/**
 * Repository class to manage all data access operations.
 */
public class TaskRepository {

    private static final String TAG = "TaskRepository";
    private static TaskRepository instance;

    private TaskSettingsDao taskSettingsDao;
    private TaskTimeDao taskTimeDao;
    private TaskTypeDao taskTypeDao;
    private TaskUserDao taskUserDao;

    public TaskRepository(AppDatabase database) {
        this.taskSettingsDao = database.taskSettingsDao();
        this.taskTimeDao = database.taskTimeDao();
        this.taskTypeDao = database.taskTypeDao();
        this.taskUserDao = database.taskUserDao();
    }

    // TaskSettings methods

    public static TaskRepository getRepository(Context context){
        AppDatabase db = AppDatabase.getInstance(context);
        if(instance == null){
            instance = new TaskRepository(db);
            if (instance != null) {
                return instance;
            }
            else {
                throw new RuntimeException("TaskRepository is null");
            }
        }else {
            return instance;
        }
    }

    public TaskSettings getTaskSettingsById(int id) {
        return taskSettingsDao.getById(id);
    }

    public void insertTaskSettings(TaskSettings taskSettings) {
        taskSettingsDao.insert(taskSettings);
    }

    public void updateTaskSettings(TaskSettings taskSettings) {
        taskSettingsDao.update(taskSettings);
    }

    public void deleteTaskSettings(TaskSettings taskSettings) {
        taskSettingsDao.delete(taskSettings);
    }

    // TaskTime methods

    public List<TaskTime> getAllTaskTimes() {
        return taskTimeDao.getAll();
    }

    public TaskTime getTaskTimeById(int id) {
        return taskTimeDao.getById(id);
    }

    public void insertTaskTime(TaskTime taskTime) {
        taskTimeDao.insert(taskTime);
    }

    public void updateTaskTime(TaskTime taskTime) {
        taskTimeDao.update(taskTime);
    }

    public void deleteTaskTime(TaskTime taskTime) {
        taskTimeDao.delete(taskTime);
    }

    public void deleteAllTaskTimes() {
        taskTimeDao.deleteAll();
    }

    public LiveData<List<TaskTime>> getAllTaskTimesLiveData() {
        return taskTimeDao.getAllTaskTimes();
    }

    // TaskType methods

    public LiveData<List<TaskType>> getAllTaskTypes() {
        return taskTypeDao.getAll();
    }

    public LiveData<TaskType> getTaskTypeById(int id) {
        return taskTypeDao.getById(id);
    }

    public void insertTaskType(TaskType taskType) {
        taskTypeDao.insert(taskType);
    }

    public void updateTaskType(TaskType taskType) {
        taskTypeDao.update(taskType);
    }

    public void deleteTaskType(TaskType taskType) {
        taskTypeDao.delete(taskType);
    }

    public void deleteAllTaskTypes() {
        taskTypeDao.deleteAll();
    }

    // TaskUser methods

    public LiveData<List<TaskUser>> getAllTaskUsers() {
        return taskUserDao.getAll();
    }

    public LiveData<TaskUser> getTaskUserById(long userId) {
        return taskUserDao.getById(userId);
    }

    public void insertTaskUser(TaskUser taskUser) {
        taskUserDao.insert(taskUser);
    }

    public void updateTaskUser(TaskUser taskUser) {
        taskUserDao.update(taskUser);
    }

    public void deleteTaskUser(TaskUser taskUser) {
        taskUserDao.delete(taskUser);
    }

    public void deleteAllTaskUsers() {
        taskUserDao.deleteAll();
    }


}
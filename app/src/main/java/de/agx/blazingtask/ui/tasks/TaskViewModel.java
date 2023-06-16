package de.agx.blazingtask.ui.tasks;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import de.agx.blazingtask.db.AppDatabase;
import de.agx.blazingtask.ui.adapter.AdapterTaskTime;
import de.agx.blazingtask.ui.types.TaskTime;

public class TaskViewModel  {
    private static TaskViewModel instance;
    private MutableLiveData<List<TaskTime>> taskTimes;
    private AdapterTaskTime adapterTaskTime;

    public TaskViewModel() {
        taskTimes = new MutableLiveData<>();
        adapterTaskTime = new AdapterTaskTime(new ArrayList<>());
    }
    public static TaskViewModel getModel() {
        if (instance == null) {
            instance = new TaskViewModel();
        }
        return instance;
    }

    public static TaskViewModel getInstance() {
        return instance;
    }

    public static void setInstance(TaskViewModel instance) {
        TaskViewModel.instance = instance;
    }

    public MutableLiveData<List<TaskTime>> getTaskTimes() {
        return taskTimes;
    }

    public void setTaskTimes(LiveData<List<TaskTime>> taskTimes) {
        this.taskTimes = (MutableLiveData<List<TaskTime>>) taskTimes;
    }

    public AdapterTaskTime getAdapterTaskTime() {
        return adapterTaskTime;
    }

    public void setAdapterTaskTime(AdapterTaskTime adapterTaskTime) {
        this.adapterTaskTime = adapterTaskTime;
    }
}
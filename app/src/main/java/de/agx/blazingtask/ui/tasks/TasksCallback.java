package de.agx.blazingtask.ui.tasks;

import android.util.Log;
import android.view.View;
import de.agx.blazingtask.ui.types.TaskTime;

import static de.agx.blazingtask.db.TaskRepository.getRepository;

public class TasksCallback {
    private static final String TAG = "TasksCallback";
    private static TasksCallback instance;

    public static TasksCallback getInstance() {
        if (instance == null) {
            instance = new TasksCallback();
        }
        return instance;
    }

    public boolean onTaskLongClick(View view) {
        //Log.d(TAG, "onTaskLongClick: " + taskTime.toString());
        //getRepository(view.getContext()).deleteTaskTime(taskTime);
        return true;
    }

}

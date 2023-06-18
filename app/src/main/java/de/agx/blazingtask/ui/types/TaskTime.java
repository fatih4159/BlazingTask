package de.agx.blazingtask.ui.types;

import android.util.Log;
import android.view.View;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.android.material.snackbar.Snackbar;
import de.agx.blazingtask.MainActivity;
import de.agx.blazingtask.ui.tasks.TasksCallback;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static de.agx.blazingtask.db.TaskRepository.getRepository;

@Entity(tableName = "task_time")
public class TaskTime extends BaseObservable {

    private static final String TAG = "TaskTime";

    public TaskTime(String taskDate, String taskUser, String taskType,
                    String taskName, String taskTimeStampStart, String taskTimeStampFinish, String taskTimeDuration) {
        this.taskDate = taskDate;
        this.taskUser = taskUser;
        this.taskType = taskType;
        this.taskName = taskName;
        this.taskTimeStampStart = taskTimeStampStart;
        this.taskTimeStampFinish = taskTimeStampFinish;
        this.taskTimeDuration = taskTimeDuration;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "task_date")
    private String taskDate;

    @ColumnInfo(name = "task_user")
    private String taskUser;

    @ColumnInfo(name = "task_type")
    private String taskType;

    @ColumnInfo(name = "task_name")
    private String taskName;

    @ColumnInfo(name = "task_timestamp_start")
    private String taskTimeStampStart;

    @ColumnInfo(name = "task_timestamp_finish")
    private String taskTimeStampFinish;

    @ColumnInfo(name = "task_time_duration")
    private String taskTimeDuration;

    public TaskTime() {

    }

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(de.agx.blazingtask.BR.id);
    }

    @Bindable
    public String getTaskDate() {

        return this.taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
        notifyPropertyChanged(de.agx.blazingtask.BR.taskDate);
    }

    @Bindable
    public String getTaskUser() {
        return taskUser;
    }

    public void setTaskUser(String taskUser) {
        this.taskUser = taskUser;
        notifyPropertyChanged(de.agx.blazingtask.BR.taskUser);
    }

    @Bindable
    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
        notifyPropertyChanged(de.agx.blazingtask.BR.taskType);
    }

    @Bindable
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
        notifyPropertyChanged(de.agx.blazingtask.BR.taskName);
    }

    @Bindable
    public String getTaskTimeStampStart() {
        return taskTimeStampStart;
    }

    public void setTaskTimeStampStart(String taskTimeStampStart) {


        this.taskTimeStampStart = taskTimeStampStart;
        notifyPropertyChanged(de.agx.blazingtask.BR.taskTimeStampStart);
    }

    @Bindable
    public String getTaskTimeStampFinish() {
        return taskTimeStampFinish;
    }

    public void setTaskTimeStampFinish(String taskTimeStampFinish) {

        this.taskTimeStampFinish = taskTimeStampFinish;
        notifyPropertyChanged(de.agx.blazingtask.BR.taskTimeStampFinish);
    }

    @Bindable
    public String getTaskTimeDuration() {
        //updateDuration();
        return taskTimeDuration;
    }


    public void setTaskTimeDuration(String taskTimeDuration) {
        this.taskTimeDuration = taskTimeDuration;
        //updateDuration();
        notifyPropertyChanged(de.agx.blazingtask.BR.taskTimeDuration);
    }

    public static String millisToDateTime (long timestamp) {

        Date date = new Date(timestamp);
        SimpleDateFormat outputFormat = new SimpleDateFormat("EEEE dd.MM.yyyy HH:mm:ss", Locale.getDefault());
        Log.d(TAG, "millisToDateTime:  " + outputFormat.format(date) );

        return outputFormat.format(date);
    }

    public static String millilsToTime(long milis) {
        Date date = new Date(milis);
        SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        Log.d(TAG, "millisToDate:  " + outputFormat.format(date) );

        return outputFormat.format(date);
    }

    public static class Callbacks {
        public void onItemClicked() {
            // do something
        }


        public boolean onTaskLongClick(View view, TaskTime taskTime) {
            Log.d(TAG, "onTaskLongClick: " + taskTime.toString());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    getRepository(MainActivity.getContext()).deleteTaskTime(taskTime);
                }
            }).start();
            Snackbar.make(view, "Task deleted", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return true;
        }

    }
}
package de.agx.blazingtask.ui.types;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Entity(tableName = "task_time")
public class TaskTime extends BaseObservable {

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
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(dateFormat.parse(taskDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Format date and day name
        SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy.MM.dd EEEE", Locale.getDefault());
        return newDateFormat.format(calendar.getTime());
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
        return formatDate(taskTimeStampStart);
    }

    public void setTaskTimeStampStart(String taskTimeStampStart) {
        this.taskTimeStampStart = taskTimeStampStart;
        notifyPropertyChanged(de.agx.blazingtask.BR.taskTimeStampStart);
    }

    @Bindable
    public String getTaskTimeStampFinish() {
        return formatDate(taskTimeStampFinish);
    }

    public void setTaskTimeStampFinish(String taskTimeStampFinish) {
        this.taskTimeStampFinish = taskTimeStampFinish;
        notifyPropertyChanged(de.agx.blazingtask.BR.taskTimeStampFinish);
    }

    @Bindable
    public String getTaskTimeDuration() {
        return taskTimeDuration;
    }

    public void setTaskTimeDuration(String taskTimeDuration) {
        this.taskTimeDuration = taskTimeDuration;
        notifyPropertyChanged(de.agx.blazingtask.BR.taskTimeDuration);
    }

    private String formatDate(String timestamp) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss", Locale.getDefault());

        Date date;
        try {
            date = inputFormat.parse(timestamp);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }

        return outputFormat.format(date);
    }

    public class Callbacks {
        public void onItemClicked() {
            // do something
        }
    }
}
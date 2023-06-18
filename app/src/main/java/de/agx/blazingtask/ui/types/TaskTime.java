package de.agx.blazingtask.ui.types;

import android.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import com.google.android.material.button.MaterialButton;
import de.agx.blazingtask.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import static de.agx.blazingtask.db.TaskRepository.getRepository;

@Entity(tableName = "task_time")
public class TaskTime extends BaseObservable {

    private static final String TAG = "TaskTime";

    public TaskTime(int id, boolean taskStarted, String taskDate, String taskUser, String taskType, String taskTimeStampStart, String taskTimeStampFinish, String taskTimeDuration) {
        this.id = id;
        this.taskStarted = taskStarted;
        this.taskDate = taskDate;
        this.taskUser = taskUser;
        this.taskType = taskType;
        this.taskTimeStampStart = taskTimeStampStart;
        this.taskTimeStampFinish = taskTimeStampFinish;
        this.taskTimeDuration = taskTimeDuration;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "task_started")
    private boolean taskStarted = false;
    @ColumnInfo(name = "task_date")
    private String taskDate;

    @ColumnInfo(name = "task_user")
    private String taskUser;

    @ColumnInfo(name = "task_type")
    private String taskType;

    @ColumnInfo(name = "task_timestamp_start")
    private String taskTimeStampStart = "00:00:00";

    @ColumnInfo(name = "task_timestamp_finish")
    private String taskTimeStampFinish = "00:00:00";

    @ColumnInfo(name = "task_time_duration")
    private String taskTimeDuration = "00:00:00";

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
    public boolean isTaskStarted() {
        return taskStarted;
    }

    @Ignore
    Timer timer;
    @Ignore
    TimerTask timerTask;

    public void setTaskStarted(boolean taskStarted) {
        this.taskStarted = taskStarted;

        if(!taskStarted){
            if (timer != null) {
                timer.cancel();
                timer.purge();
            }
            if (timerTask != null) {
                timerTask.cancel();
            }
            timer = null;
            timerTask = null;
        }else {
            timer = new Timer();
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    setTaskTimeDuration(getTaskTimeDuration());
                }
            };
            timer.scheduleAtFixedRate(timerTask, 0, 1000);
        }

        notifyPropertyChanged(de.agx.blazingtask.BR.taskStarted);
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

        if (taskTimeStampStart.equals("00:00:00")) {
            return "00:00:00";
        }


        String tempdu = taskTimeDuration;

        // current time to millis
        long currentTime = System.currentTimeMillis();

        // start time string from hh:mm:ss to millis
        String[] startTime = this.taskTimeStampStart.split(":");
        long startTimeMillis = (Integer.parseInt(startTime[0]) * 3600 + Integer.parseInt(startTime[1]) * 60 + Integer.parseInt(startTime[2])) * 1000;

        // calculate duration
        long duration = currentTime - startTimeMillis;

        // convert duration to hh:mm:ss
        tempdu = millilsToTime(duration);

        return tempdu;
    }


    public void setTaskTimeDuration(String taskTimeDuration) {
        this.taskTimeDuration = taskTimeDuration;
        //updateDuration();
        notifyPropertyChanged(de.agx.blazingtask.BR.taskTimeDuration);
    }

    public static String millisToDateTime(long timestamp) {

        Date date = new Date(timestamp);
        SimpleDateFormat outputFormat = new SimpleDateFormat("EEEE dd.MM.yyyy HH:mm:ss", Locale.getDefault());
        //Log.d(TAG, "millisToDateTime:  " + outputFormat.format(date));

        return outputFormat.format(date);
    }

    public static String millilsToTime(long milis) {
        Date date = new Date(milis);
        SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        //Log.d(TAG, "millisToDate:  " + outputFormat.format(date));

        return outputFormat.format(date);
    }

    public static class Callbacks {
        AlertDialog dialog;

        public void onItemClicked() {
            // do something
        }

        public boolean onTaskLongClick(View view, TaskTime taskTime) {
            Log.d(TAG, "onTaskLongClick: " + taskTime.toString());
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

            MaterialButton btn_delete = new MaterialButton(view.getContext());
            btn_delete.setText("Delete");
            btn_delete.setOnClickListener((v) -> {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getRepository(MainActivity.getContext()).deleteTaskTime(taskTime);
                        dialog.dismiss();
                    }
                }).start();
            });

            MaterialButton btn_edit = new MaterialButton(view.getContext());
            btn_edit.setText("Edit");

            MaterialButton btn_start = new MaterialButton(view.getContext());
            btn_start.setText("Start");
            btn_start.setOnClickListener((v) -> {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "Starting task: " + taskTime.getTaskType());
                        taskTime.setTaskTimeStampStart(millilsToTime(System.currentTimeMillis()));
                        taskTime.setTaskStarted(true);
                        getRepository(MainActivity.getContext()).updateTaskTime(taskTime);
                        dialog.dismiss();
                    }
                }).start();
            });

            MaterialButton btn_stop = new MaterialButton(view.getContext());
            btn_stop.setText("Stop");
            btn_stop.setOnClickListener((v) -> {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "Stoping task: " + taskTime.getTaskType());
                        taskTime.setTaskTimeStampFinish(millilsToTime(System.currentTimeMillis()));
                        taskTime.setTaskStarted(false);
                        getRepository(MainActivity.getContext()).updateTaskTime(taskTime);
                        dialog.dismiss();
                    }
                }).start();
            });

            MaterialButton btn_cancel = new MaterialButton(view.getContext());
            btn_cancel.setText("Cancel");
            btn_cancel.setOnClickListener((v) -> {
                dialog.dismiss();
            });

            LinearLayout layout = new LinearLayout(view.getContext());
            layout.setOrientation(LinearLayout.VERTICAL);

            if(taskTime.isTaskStarted()){
                layout.addView(btn_stop);
            }else{
                layout.addView(btn_start);
            }


            layout.addView(btn_edit);
            layout.addView(btn_delete);
            layout.addView(btn_cancel);
            layout.setPadding(20, 20, 20, 20);


            builder.setTitle("Item Menu");
            builder.setView(layout);
            builder.setCancelable(true);
            dialog = builder.create();
            dialog.show();


            return true;
        }

    }
}
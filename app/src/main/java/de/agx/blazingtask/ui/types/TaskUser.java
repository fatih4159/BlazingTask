package de.agx.blazingtask.ui.types;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "task_user")
public class TaskUser extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    private long userId;

    @ColumnInfo(name = "user_name")
    private String userName;

    @ColumnInfo(name = "user_last_name")
    private String userLastName;

    @ColumnInfo(name = "user_work_time")
    private String userWorkTime;

    @ColumnInfo(name = "user_work_time_break")
    private String userWorkTimeBreak;

    public TaskUser(String userName, String userLastName, String userWorkTime, String userWorkTimeBreak) {
        this.userName = userName;
        this.userLastName = userLastName;
        this.userWorkTime = userWorkTime;
        this.userWorkTimeBreak = userWorkTimeBreak;
    }

    @Bindable
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        notifyPropertyChanged(BR.userName);
    }

    @Bindable
    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
        notifyPropertyChanged(BR.userLastName);
    }

    @Bindable
    public String getUserWorkTime() {
        return userWorkTime;
    }

    public void setUserWorkTime(String userWorkTime) {
        this.userWorkTime = userWorkTime;
        notifyPropertyChanged(BR.userWorkTime);
    }

    @Bindable
    public String getUserWorkTimeBreak() {
        return userWorkTimeBreak;
    }

    public void setUserWorkTimeBreak(String userWorkTimeBreak) {
        this.userWorkTimeBreak = userWorkTimeBreak;
        notifyPropertyChanged(BR.userWorkTimeBreak);
    }
}
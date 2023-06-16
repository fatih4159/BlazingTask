package de.agx.blazingtask.ui.types;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import de.agx.blazingtask.BR;

@Entity(tableName = "task_settings")
public class TaskSettings extends BaseObservable {

    // Adding PrimaryKey annotation with autoGenerate set to true
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "settings_id")
    private int settingsId;

    // Adding ColumnInfo annotation with column name
    @NonNull
    @ColumnInfo(name = "settings_key")
    private String settingsKey;

    // Adding ColumnInfo annotation with column name
    @NonNull
    @ColumnInfo(name = "settings_value")
    private String settingsValue;

    public TaskSettings(@NonNull String settingsKey, @NonNull String settingsValue) {
        this.settingsKey = settingsKey;
        this.settingsValue = settingsValue;
    }

    // Adding getters and setters with Bindable annotation
    @Bindable
    public int getSettingsId() {
        return settingsId;
    }

    public void setSettingsId(int settingsId) {
        this.settingsId = settingsId;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    @NonNull
    public String getSettingsKey() {
        return settingsKey;
    }

    public void setSettingsKey(@NonNull String settingsKey) {
        this.settingsKey = settingsKey;
        notifyPropertyChanged(de.agx.blazingtask.BR.settingsKey);
    }

    @Bindable
    @NonNull
    public String getSettingsValue() {
        return settingsValue;
    }

    public void setSettingsValue(@NonNull String settingsValue) {
        this.settingsValue = settingsValue;
        notifyPropertyChanged(de.agx.blazingtask.BR.settingsValue);
    }
}
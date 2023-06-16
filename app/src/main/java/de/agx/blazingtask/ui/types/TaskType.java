package de.agx.blazingtask.ui.types;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

@Entity(tableName = "task_types")
public class TaskType extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String typeName;
    private String typeGroup;
    private int typeTime;

    @Bindable
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
        notifyPropertyChanged(de.agx.blazingtask.BR.typeName);
    }

    @Bindable
    public String getTypeGroup() {
        return typeGroup;
    }

    public void setTypeGroup(String typeGroup) {
        this.typeGroup = typeGroup;
        notifyPropertyChanged(de.agx.blazingtask.BR.typeGroup);
    }

    @Bindable
    public int getTypeTime() {
        return typeTime;
    }

    public void setTypeTime(int typeTime) {
        this.typeTime = typeTime;
        notifyPropertyChanged(de.agx.blazingtask.BR.typeTime);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TaskType(String typeName, String typeGroup, int typeTime) {
        this.typeName = typeName;
        this.typeGroup = typeGroup;
        this.typeTime = typeTime;
    }
}
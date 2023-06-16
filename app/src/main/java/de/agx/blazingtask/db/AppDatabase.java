package de.agx.blazingtask.db;

import android.content.Context;

import androidx.databinding.adapters.Converters;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import de.agx.blazingtask.ui.dao.TaskSettingsDao;
import de.agx.blazingtask.ui.dao.TaskTimeDao;
import de.agx.blazingtask.ui.dao.TaskTypeDao;
import de.agx.blazingtask.ui.dao.TaskUserDao;
import de.agx.blazingtask.ui.types.*;

@Database(entities = {TaskType.class, TaskUser.class, TaskTime.class, TaskSettings.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "appdatabase.db";
    private static volatile AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static AppDatabase create(final Context context) {
        return Room.databaseBuilder(
                        context,
                        AppDatabase.class,
                        DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    public abstract TaskTypeDao taskTypeDao();

    public abstract TaskUserDao taskUserDao();

    public abstract TaskTimeDao taskTimeDao();

    public abstract TaskSettingsDao taskSettingsDao();
}
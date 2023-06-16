package de.agx.blazingtask.ui.tasks;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import de.agx.blazingtask.databinding.FragmentSlideshowBinding;
import de.agx.blazingtask.databinding.FragmentTasksBinding;
import de.agx.blazingtask.db.AppDatabase;
import de.agx.blazingtask.db.TaskRepository;
import de.agx.blazingtask.ui.adapter.AdapterTaskTime;
import de.agx.blazingtask.ui.types.TaskTime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TasksFragment extends Fragment {

    private FragmentTasksBinding binding;
    private static TaskViewModel taskViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTasksBinding.inflate(inflater, container, false);
        taskViewModel = TaskViewModel.getModel();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = dateFormat.format(new Date());


        AppDatabase db = AppDatabase.getInstance(getContext());
        new TaskRepository(db).getAllTaskTimesLiveData().observe(getViewLifecycleOwner(), new Observer<List<TaskTime>>() {
            @Override
            public void onChanged(List<TaskTime> taskTimes) {
                binding.recyclerView.swapAdapter(new AdapterTaskTime(taskTimes), false);

            }
        });
        binding.floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a LinearLayout to hold all the views
                LinearLayout linearLayout = new LinearLayout(getContext());
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.setPadding(50, 20, 50, 20);

                // Initialize all the required views and add them to the LinearLayout
                DatePicker taskDatePicker = new DatePicker(getContext());
                linearLayout.addView(taskDatePicker);

                EditText taskUserET = new EditText(getContext());
                taskUserET.setHint("Task User");
                linearLayout.addView(taskUserET);

                EditText taskTypeET = new EditText(getContext());
                taskTypeET.setHint("Task Type");
                linearLayout.addView(taskTypeET);

                EditText taskNameET = new EditText(getContext());
                taskNameET.setHint("Task Name");
                linearLayout.addView(taskNameET);

                TimePicker taskTimeStartPicker = new TimePicker(getContext());
                taskTimeStartPicker.setIs24HourView(true);
                linearLayout.addView(taskTimeStartPicker);

                TimePicker taskTimeFinishPicker = new TimePicker(getContext());
                taskTimeFinishPicker.setIs24HourView(true);
                linearLayout.addView(taskTimeFinishPicker);

                EditText taskTimeDurationET = new EditText(getContext());
                taskTimeDurationET.setHint("Task Time Duration");
                linearLayout.addView(taskTimeDurationET);

                // Create & show the dialog
                AlertDialog dialog = new AlertDialog.Builder(getContext())
                        .setTitle("Add New Task")
                        .setView(linearLayout)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Collect data from the views and create a new TaskTime instance
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                String taskDate = dateFormat.format(new Date(taskDatePicker.getYear() - 1900, taskDatePicker.getMonth(), taskDatePicker.getDayOfMonth()));

                                EditText taskTimeStampStartET = new EditText(getContext());
                                taskTimeStampStartET.setHint("Task TimeStamp Start");
                                String taskTimeStampStart = taskTimeStampStartET.getText().toString();

                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                                    int hour = taskTimeStartPicker.getHour();
                                    int minute = taskTimeStartPicker.getMinute();
                                    taskTimeStampStart = String.format(Locale.US, "%02d:%02d:00", hour, minute);
                                }

                                TaskTime newTaskTime = new TaskTime(
                                        taskDate,
                                        taskUserET.getText().toString(),
                                        taskTypeET.getText().toString(),
                                        taskNameET.getText().toString(),
                                        taskTimeStampStart,
                                        "00:00:00",
                                        taskTimeDurationET.getText().toString()
                                );

                                // Pass the new TaskTime object to your database layer to insert it into the database
                                AppDatabase db = AppDatabase.getInstance(getContext());
                                new TaskRepository(db).insertTaskTime(newTaskTime);
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();

                dialog.show();
            }
        });

        binding.recyclerView.setAdapter(taskViewModel.getAdapterTaskTime());

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
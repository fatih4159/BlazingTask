package de.agx.blazingtask.ui.tasks;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
                // Inflate the dialog layout
                View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_task_time, null);

                // Initialize all the required views
                EditText taskDateET = new EditText(getContext());
                EditText taskUserET = new EditText(getContext());
                EditText taskTypeET = new EditText(getContext());
                EditText taskNameET = new EditText(getContext());
                EditText taskTimeStampStartET = new EditText(getContext());;
                EditText taskTimeStampFinishET = new EditText(getContext());;
                EditText taskTimeDurationET = new EditText(getContext());;

                // Create & show the dialog
                AlertDialog dialog = new AlertDialog.Builder(getContext())
                        .setTitle("Add New Task")
                        .setView(dialogView)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Collect data from the views and create a new TaskTime instance
                                TaskTime newTaskTime = new TaskTime(
                                        taskDateET.getText().toString(),
                                        taskUserET.getText().toString(),
                                        taskTypeET.getText().toString(),
                                        taskNameET.getText().toString(),
                                        taskTimeStampStartET.getText().toString(),
                                        taskTimeStampFinishET.getText().toString(),
                                        taskTimeDurationET.getText().toString()
                                );

                                // Pass the new TaskTime object to your database layer to insert it into the database
                                AppDatabase db = AppDatabase.getInstance(getContext());
                                new TaskRepository(db).insert(newTaskTime);
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
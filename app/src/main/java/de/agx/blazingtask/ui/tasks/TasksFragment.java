package de.agx.blazingtask.ui.tasks;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import de.agx.blazingtask.R;
import de.agx.blazingtask.databinding.FragmentSlideshowBinding;
import de.agx.blazingtask.databinding.FragmentTasksBinding;
import de.agx.blazingtask.db.AppDatabase;
import de.agx.blazingtask.db.TaskRepository;
import de.agx.blazingtask.ui.adapter.AdapterTaskTime;
import de.agx.blazingtask.ui.dialogs.AddTaskToListDIalog;
import de.agx.blazingtask.ui.types.TaskTime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static de.agx.blazingtask.db.TaskRepository.getRepository;

public class TasksFragment extends Fragment {

    private FragmentTasksBinding binding;
    private static TaskViewModel taskViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTasksBinding.inflate(inflater, container, false);
        taskViewModel = TaskViewModel.getModel();



        getRepository(getContext()).getAllTaskTimesLiveData().observe(getViewLifecycleOwner(), new Observer<List<TaskTime>>() {
            @Override
            public void onChanged(List<TaskTime> taskTimes) {
                binding.recyclerView.swapAdapter(new AdapterTaskTime(taskTimes), false);
            }
        });






        binding.floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddTaskToListDIalog dialog = new AddTaskToListDIalog();
                dialog.show(getParentFragmentManager(), "AddTaskToListDialog");
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
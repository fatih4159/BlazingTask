package de.agx.blazingtask.ui.dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import de.agx.blazingtask.databinding.DialogTasktolistBinding;
import de.agx.blazingtask.ui.types.TaskTime;
import de.agx.blazingtask.ui.types.TaskType;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import static de.agx.blazingtask.db.TaskRepository.getRepository;

// The Dialog to create a new task
public class AddTaskToListDIalog extends DialogFragment {

    private DialogTasktolistBinding binding; // Declare binding variable
    Consumer<List<TaskType>> callback = new Consumer<List<TaskType>>() {
        @Override
        public void accept(List<TaskType> taskTypes) {
            updateSpinner(taskTypes);
        }

    };

    @SuppressLint("CheckResult")
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

        // Inflate the layout using databinding
        binding = DialogTasktolistBinding.inflate(requireActivity().getLayoutInflater());

        binding.fabExitTasktolist.setOnClickListener(view -> {
            dismiss();
        });


        binding.fabNewType.setOnClickListener(view -> {
            NewTypeDialog newTypeDialog = new NewTypeDialog(callback);

            newTypeDialog.show(getParentFragmentManager(), "NewTypeDialog");
        });

        binding.btnAddToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable(){

                    @Override
                    public void run() {




                        TaskTime tasktime = new TaskTime();
                        tasktime.setTaskDate(TaskTime.millisToDateTime(System.currentTimeMillis()));
                        tasktime.setTaskUser("TestUser");
                        tasktime.setTaskType(binding.spTypeSelect.getSelectedItem().toString());




                        getRepository(getContext()).insertTaskTime(tasktime);

                    }
                }).start();
            }
        });

        // TODO: Bind data to views using binding object


        builder.setView(binding.getRoot());

        return builder.create();
    }

    private void updateSpinner(List<TaskType> taskTypes) {

        if(taskTypes != null){
            String[] taskTypeNames = new String[taskTypes.size()];
            for (int i = 0; i < taskTypes.size(); i++) {
                taskTypeNames[i] = taskTypes.get(i).getTypeName();
            }

            // Create adapter and set it on the spinner
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, taskTypeNames);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            getActivity().runOnUiThread(() -> {
                binding.spTypeSelect.setAdapter(adapter);
                binding.spTypeSelect.setSelection(taskTypes.size() - 1);
            });
        }
    }

    @Override
    public void onStart() {


        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        super.onStart();
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        getRepository(getContext()).getAllTaskTypes().observe(getActivity(), new Observer<List<TaskType>>() {
            @Override
            public void onChanged(List<TaskType> taskTypes) {
                updateSpinner(taskTypes);
            }
        });
        super.onCreate(savedInstanceState);
    }
}
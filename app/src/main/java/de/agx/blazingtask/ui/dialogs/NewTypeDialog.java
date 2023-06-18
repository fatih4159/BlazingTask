package de.agx.blazingtask.ui.dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import de.agx.blazingtask.databinding.DialogNewtypeBinding;
import de.agx.blazingtask.databinding.DialogTasktolistBinding;
import de.agx.blazingtask.ui.types.TaskType;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import static de.agx.blazingtask.db.TaskRepository.getRepository;

// The Dialog to create a new task
public class NewTypeDialog extends DialogFragment {

    private Consumer<List<TaskType>> callback;

    public NewTypeDialog(Consumer<List<TaskType>> callback) {
        this.callback = callback;
    }



    private DialogNewtypeBinding binding; // Declare binding variable

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

        // Inflate the layout using databinding
        binding = DialogNewtypeBinding.inflate(requireActivity().getLayoutInflater());

        // TODO: Bind data to views using binding object
        binding.exitDialogCreateType.setOnClickListener(view -> {
            dismiss();
        });

        binding.btnCreateType.setOnClickListener(view -> {
            String typeName = binding.tilCreateType.getEditText().getText().toString();
            if (!typeName.isEmpty()) {
                createType(typeName);

                updateList();
                dismiss();
            }
        });

        builder.setView(binding.getRoot());

        return builder.create();
    }

    private void updateList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                    callback.accept(getRepository(getContext()).getAllTaskTypes().getValue());
            }
        }).start();
    }

    @Override
    public void onStart() {
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        super.onStart();
    }

    private void createType(String typeName) {
        Thread t = new Thread(() -> {
            if (!typeName.isEmpty()) {
                getRepository(getContext()).insertTaskType(new TaskType(typeName, "", 0));
            }
        });
        t.start();
    }

}
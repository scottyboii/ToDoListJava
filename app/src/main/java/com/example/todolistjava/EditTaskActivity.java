package com.example.todolistjava;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditTaskActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.todolistjava.REPLY";

    private EditText mEditNameView;
    private EditText mEditDateView;
    private EditText mEditPriorityView;
    private EditText mEditColourView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_task);

        mEditNameView = findViewById(R.id.edit_name);
        mEditDateView = findViewById(R.id.edit_dueDate);
        mEditPriorityView = findViewById(R.id.edit_priority);
        mEditColourView = findViewById(R.id.edit_colour);

        Bundle extrasIn = getIntent().getExtras();
        Integer taskID = extrasIn.getInt("EXTRA_ID");
        String taskNameStart = extrasIn.getString("EXTRA_NAME");
        String taskDateStart = extrasIn.getString("EXTRA_DATE");
        String taskPriorityStart = extrasIn.getString("EXTRA_PRIORITY");
        String taskColourStart = extrasIn.getString("EXTRA_COLOUR");

        mEditNameView.setText(taskNameStart);
        mEditDateView.setText(taskDateStart);
        mEditPriorityView.setText(taskPriorityStart);
        mEditColourView.setText(taskColourStart);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditNameView.getText()) || TextUtils.isEmpty(mEditDateView.getText()) || TextUtils.isEmpty(mEditPriorityView.getText()) || TextUtils.isEmpty(mEditColourView.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String taskName = mEditNameView.getText().toString();
                String taskDate = mEditDateView.getText().toString();
                String taskPriority = mEditPriorityView.getText().toString();
                String taskColour = mEditColourView.getText().toString();

                Bundle extras = new Bundle();
                extras.putInt("EXTRA_ID", taskID);
                extras.putString("EXTRA_NAME", taskName);
                extras.putString("EXTRA_DATE", taskDate);
                extras.putString("EXTRA_PRIORITY", taskPriority);
                extras.putString("EXTRA_COLOUR", taskColour);

                replyIntent.putExtras(extras);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });


    }

}

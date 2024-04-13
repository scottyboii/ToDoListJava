package com.example.todolistjava;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TaskCalendarActivity extends AppCompatActivity {

    CompactCalendarView calendarView;
    TaskViewModel mTaskViewModel;
    List<String> mDueDates;
    TextView mMonthView;
    FloatingActionButton mBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_view);

        calendarView = findViewById(R.id.calendarView);

        calendarView.removeAllEvents();
        mMonthView = findViewById(R.id.textViewMonth);

        Date firstDay = calendarView.getFirstDayOfCurrentMonth();
        SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy");

        mMonthView.setText(dateFormatForMonth.format(firstDay));

        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                return;
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                mMonthView.setText(dateFormatForMonth.format(firstDayOfNewMonth));
            }
        });

        mBackButton = findViewById(R.id.backButton);

        mBackButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivityForResult(intent, 4);
        });

        mTaskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        mDueDates = mTaskViewModel.getDueDates();
        for (int i = 0; i < mDueDates.size(); i++) {
            String dateStr = mDueDates.get(i);

            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date date = null;
            try {
                date = df.parse(dateStr);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            long time = date.getTime();

            Event newEvent = new Event(Color.GREEN, time, "Test");
            calendarView.addEvent(newEvent);
        }

    }


}

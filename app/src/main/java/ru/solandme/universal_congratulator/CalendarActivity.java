package ru.solandme.universal_congratulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        initViews();
    }

    private void initViews() {
        Calendar todayData = new GregorianCalendar();
        Calendar maxDate = new GregorianCalendar(todayData.get(Calendar.YEAR)+1, todayData.get(Calendar.MONTH), todayData.get(Calendar.DAY_OF_MONTH));
        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);

        calendarView.setMinDate(todayData.getTimeInMillis());
        calendarView.setMaxDate(maxDate.getTimeInMillis());
    }
}

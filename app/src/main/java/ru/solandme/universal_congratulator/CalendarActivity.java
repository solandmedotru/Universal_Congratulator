package ru.solandme.universal_congratulator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static android.provider.CalendarContract.*;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        initViews();
    }

    private void initViews() {
        Calendar todayData = new GregorianCalendar();
        Calendar maxDate = new GregorianCalendar((todayData.get(Calendar.YEAR) + 1), todayData.get(Calendar.MONTH), todayData.get(Calendar.DAY_OF_MONTH));
        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);

        calendarView.setFirstDayOfWeek(Calendar.MONDAY);


        calendarView.setMinDate(todayData.getTimeInMillis() - 1000);
        calendarView.setMaxDate(maxDate.getTimeInMillis());
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Calendar beginTime = Calendar.getInstance();
                beginTime.set(year, month, dayOfMonth);
                Intent intent = new Intent(Intent.ACTION_INSERT)
                        .setData(Events.CONTENT_URI)
                        .putExtra(EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                        .putExtra(Events.TITLE, getString(R.string.my_new_holiday))
                        .putExtra(Events.DESCRIPTION, getString(R.string.event_description_text) + " " + getResources().getString(R.string.app_name))
                        .putExtra(Events.AVAILABILITY, Events.AVAILABILITY_BUSY);
                startActivity(intent);
            }
        });
    }
}

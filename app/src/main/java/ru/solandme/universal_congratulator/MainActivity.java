package ru.solandme.universal_congratulator;

import android.content.Intent;

import android.content.pm.PackageManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "ru.solandme.universal_congratulator.MESSAGE";
    public List<Holiday> holidayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeData();
        initViews();
    }

    public void initializeData(){
        holidayList = new ArrayList<>();
        holidayList.add(new Holiday("NewYear", 31, 12, R.drawable.ic_december, 0, "Новый Год - праздник"));
        holidayList.add(new Holiday("Valentine", 14, 2, R.drawable.ic_february, 0, "День святого валентина - праздник"));
        holidayList.add(new Holiday("WomanDay", 8, 3, R.drawable.ic_8march, 0, "Международный женский день - праздник"));
        holidayList.add(new Holiday("MansDay", 23, 2, R.drawable.ic_23february, 0, "День защитника отечества - праздник"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.about_app:
                //TODO добавить описание  приложения

                String version = null;
                try {
                    version = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                Snackbar.make(findViewById(R.id.coordinatorLayout), "App version - " + version, Snackbar.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViews() {
        TextView textDaysForNewYear = (TextView) findViewById(R.id.textDaysForNewYear);
        TextView textDaysForMansDay = (TextView) findViewById(R.id.textDaysForMansDay);
        TextView textDaysForValentine = (TextView) findViewById(R.id.textDaysForValentine);
        TextView textDaysForWomanDay = (TextView) findViewById(R.id.textDaysForWomanDay);
        holidayList.get(0).getDay();

        textDaysForNewYear.setText(getDays(holidayList.get(0).getDay(), holidayList.get(0).getMonth()));
        textDaysForMansDay.setText(getDays(holidayList.get(3).getDay(), holidayList.get(3).getMonth()));
        textDaysForValentine.setText(getDays(holidayList.get(1).getDay(), holidayList.get(1).getMonth()));
        textDaysForWomanDay.setText(getDays(holidayList.get(2).getDay(), holidayList.get(2).getMonth()));
    }

    private String getDays(int day, int month) {
        Calendar todayCalendar = new GregorianCalendar();
        int year = todayCalendar.get(Calendar.YEAR);
        int daysInYear = todayCalendar.getActualMaximum(Calendar.DAY_OF_YEAR); //максимум дней в этом году
        Calendar calendar = new GregorianCalendar(year, month - 1, day);
        int days = (int) ((calendar.getTimeInMillis() - todayCalendar.getTimeInMillis()) / 1000) / 86400;

        if (days >= 1) {
            return getResources().getQuantityString(R.plurals.days, days, days);
        } else if (days < -2) {
            return getResources().getQuantityString(R.plurals.days, daysInYear + days, daysInYear + days);
        } else if (days == 0) {
            return " " + getString(R.string.textNow);
        } else {
            return " " + getString(R.string.textFinish);
        }
    }

    public void onClick(View view) {

        Intent intent = new Intent(getApplicationContext(), TextActivity.class);

        switch (view.getId()) {
            case R.id.birthday:
                intent.putExtra(EXTRA_MESSAGE, "Birthday");
                startActivity(intent);
                break;
            case R.id.newYear:
                intent.putExtra(EXTRA_MESSAGE, "NewYear");
                startActivity(intent);
                break;
            case R.id.valentine:
                intent.putExtra(EXTRA_MESSAGE, "Valentine");
                startActivity(intent);
                break;
            case R.id.womansDay:
                intent.putExtra(EXTRA_MESSAGE, "WomanDay");
                startActivity(intent);
                break;
            case R.id.mansDay:
                intent.putExtra(EXTRA_MESSAGE, "MansDay");
                startActivity(intent);
                break;
            case R.id.btnHolidaysCalendar:
                Intent intent2 = new Intent(this, CalendarActivity.class);
                startActivity(intent2);
                break;
        }
    }

}

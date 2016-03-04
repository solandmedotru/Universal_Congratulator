package ru.solandme.universal_congratulator;

import android.content.Intent;

import android.content.pm.PackageManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import ru.solandme.universal_congratulator.adapter.HolidayAdapter;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "ru.solandme.universal_congratulator.MESSAGE";
    public List<Holiday> holidayList;
    public ListView holidayListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.holiday_list_activity);
        initializeData();
        initViews();
    }

    public void initializeData() {
        holidayList = new ArrayList<>();
        holidayList.add(new Holiday("Birthday", "День рождения", R.drawable.ic_birthday,  0, "День рождения"));
        holidayList.add(new Holiday("NewYear", "Новый Год", R.drawable.ic_december, 0, "Новый Год", 31, 12, getDays(31, 12)));
        holidayList.add(new Holiday("Valentine", "День святого валентина", R.drawable.ic_february, 0, "День святого валентина", 14, 2, getDays(14, 2)));
        holidayList.add(new Holiday("WomanDay", "Международный женский день", R.drawable.ic_8march, 0, "Международный женский день", 8, 3, getDays(8, 3)));
        holidayList.add(new Holiday("MansDay", "День защитника отечества", R.drawable.ic_23february, 0, "День защитника отечества", 23, 2, getDays(23, 2)));
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

        holidayListView = (ListView) findViewById(R.id.holidayListView);

        HolidayAdapter adapter = new HolidayAdapter(this, holidayList);

        holidayListView.setAdapter(adapter);

        FloatingActionButton fb = (FloatingActionButton) findViewById(R.id.btnHolidaysCalendar);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), CalendarActivity.class);
                startActivity(intent2);
            }
        });

        holidayListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), TextActivity.class);

                switch (position) {
                    case 0:
                        intent.putExtra(EXTRA_MESSAGE, "Birthday");
                        startActivity(intent);
                        break;
                    case 1:
                        intent.putExtra(EXTRA_MESSAGE, "NewYear");
                        startActivity(intent);
                        break;
                    case 2:
                        intent.putExtra(EXTRA_MESSAGE, "Valentine");
                        startActivity(intent);
                        break;
                    case 3:
                        intent.putExtra(EXTRA_MESSAGE, "WomanDay");
                        startActivity(intent);
                        break;
                    case 4:
                        intent.putExtra(EXTRA_MESSAGE, "MansDay");
                        startActivity(intent);
                        break;
                }

            }
        });

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
}

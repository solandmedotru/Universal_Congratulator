package ru.solandme.universal_congratulator;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "ru.solandme.universal_congratulator.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                //TODO добавить настройки приложения
                Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.about_app:
                //TODO добавить описание  приложения
                Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViews() {
        TextView textDaysForNewYear = (TextView) findViewById(R.id.textDaysForNewYear);
        TextView textDaysForMansDay = (TextView) findViewById(R.id.textDaysForMansDay);
        TextView textDaysForValentine = (TextView) findViewById(R.id.textDaysForValentine);
        TextView textDaysForWomanDay = (TextView) findViewById(R.id.textDaysForWomanDay);

        textDaysForNewYear.setText(getDays(12, 31));
        textDaysForMansDay.setText(getDays(2, 23));
        textDaysForValentine.setText(getDays(2, 14));
        textDaysForWomanDay.setText(getDays(3, 8));
    }

    private String getDays(int month, int day) {
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

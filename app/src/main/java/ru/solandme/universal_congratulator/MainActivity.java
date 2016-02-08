package ru.solandme.universal_congratulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findHolidays();
    }

    private void findHolidays() {
        TextView textDaysForNewYear = (TextView) findViewById(R.id.textDaysForNewYear);
        TextView textDaysForMansDay = (TextView) findViewById(R.id.textDaysForMansDay);
        TextView textDaysForValentine = (TextView) findViewById(R.id.textDaysForValentine);
        TextView textDaysForWomanDay = (TextView) findViewById(R.id.textDaysForWomanDay);

        textDaysForNewYear.setText(getDays(12, 31));
        textDaysForMansDay.setText(getDays(2, 23));
        textDaysForValentine.setText(getDays(2, 17));
        textDaysForWomanDay.setText(getDays(3, 8));
//        long date = System.currentTimeMillis();
//        TextView holidayName = (TextView) findViewById(R.id.holidayName);
//        TextView dayFromHoliday = (TextView) findViewById(R.id.dayFromHoliday);
//
//        SimpleDateFormat dataDay = new SimpleDateFormat("dd");
//        SimpleDateFormat dataMonth = new SimpleDateFormat("MM");
//        int day = Integer.parseInt(dataDay.format(date));
//        int month = Integer.parseInt(dataMonth.format(date));
//
//        if (month == 12){
//            holidayName.setText(" Нового года");
//            dayFromHoliday.setText(Integer.toString(31 - day));
//        }
//        if (month <= 02 && day < 17){
//            holidayName.setText(" дня св.Валентина");
//            dayFromHoliday.setText(Integer.toString(17 - day));
//        }
//        if (month == 02 && (day < 23 && day > 17)){
//            holidayName.setText(" дня св.Валентина");
//            dayFromHoliday.setText(Integer.toString(23 - day));
//        }
//        if ((month >= 02 && day > 17) && (month <=3 && day < 8)){
//            holidayName.setText(" 8 Марта");
//            if((month >= 02 && day > 17)){
//                dayFromHoliday.setText(Integer.toString(28 - day + 8));
//            }
//            if((month <=3 && day < 8)){
//                dayFromHoliday.setText(Integer.toString(8 - day));
//            }
//        }
    }

    private String getDays(int month, int day) {
        Date today = new Date();

        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        int year = Integer.parseInt(yearFormat.format(today));

        Calendar calendar = new GregorianCalendar(year, month-1, day);
        long days = (((calendar.getTimeInMillis() - today.getTime())/1000))/86400;

        if (days > 1) {
            return days + " " + getString(R.string.textDays);
        } else {
            return " " + getString(R.string.textNow);
        }
    }

    public void onClick(View view){
        if (view.equals(findViewById(R.id.newYear))){
            Toast.makeText(this, "Click to New Year", Toast.LENGTH_SHORT).show();
        }
        if (view.equals(findViewById(R.id.valentine))){
            Toast.makeText(this, "Click to Valentine", Toast.LENGTH_SHORT).show();
        }
        if (view.equals(findViewById(R.id.womansDay))){
            Toast.makeText(this, "Click to 8 March", Toast.LENGTH_SHORT).show();
        }
        if (view.equals(findViewById(R.id.mansDay))){
            Toast.makeText(this, "Click to 23 February", Toast.LENGTH_SHORT).show();
        }
    }

}

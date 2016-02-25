package ru.solandme.universal_congratulator;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class TextActivity extends AppCompatActivity {

    TextView textCongratulate;
    Button btnNext;
    int currentCongratulateTextPosition;
    Holiday holiday;

    DatabaseHelper sqlHelper;
    Cursor userCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);



        sqlHelper = new DatabaseHelper(getApplicationContext());
        // создаем базу данных
        sqlHelper.setForcedUpgrade();
        sqlHelper.getReadableDatabase();
        initHoliday();
        initViews();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        sqlHelper.close();
        sqlHelper.database.close();
    }
    private void initHoliday() {
        currentCongratulateTextPosition = 0;
        holiday = new Holiday();
        holiday.setHolidayName(getHolidayNameFromIntent());
        holiday.setCongratulates(getCongratulateArray());
        holiday.setSex(Holiday.UNIVERSAL);
    }

    private void initViews() {
        btnNext = (Button) findViewById(R.id.btnNext);
        textCongratulate = (TextView) findViewById(R.id.textCongratulate);

        if (holiday.getHolidayName() != null) {
            textCongratulate.setText(getRndTextCongratulate());
        }
    }

    private String[] getCongratulateArray() {
        // TODO реализовать выборку из базы данных

        ArrayList<String> myArrayList = new ArrayList<>();
        try {
            sqlHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        userCursor = sqlHelper.database.rawQuery("select * from " + DatabaseHelper.TABLE + " where " +
                DatabaseHelper.HOLIDAY + "=?", new String[]{holiday.getHolidayName()});

        while (userCursor.moveToNext()) {
            String note = userCursor.getString(1);
            myArrayList.add(note);
        }
        String[] myArray = myArrayList.toArray(new String[myArrayList.size()]);
        userCursor.close();
        return myArray;


//        switch (holiday.getHolidayName()) {
//            case "Birthday":
//                return getResources().getStringArray(R.array.birthday);
//            case "NewYear":
//                return getResources().getStringArray(R.array.newYear);
//            case "Valentine":
//                return getResources().getStringArray(R.array.valentine);
//            case "WomanDay":
//                return getResources().getStringArray(R.array.womansDay);
//            case "MansDay":
//                return getResources().getStringArray(R.array.mansDay);
//            default:
//                return null;
//        }
    }

    private String getHolidayNameFromIntent() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            return extras.getString(MainActivity.EXTRA_MESSAGE);
        }
        return null;
    }

    private String getNextTextCongratulate() {
        return holiday.congratulates[getNextPosition()];
    }

    private String getPrevTextCongratulate() {
        return holiday.getCongratulates()[getPrevPosition()];
    }

    private int getNextPosition() {
        if (currentCongratulateTextPosition < holiday.getCongratulates().length - 1) {
            currentCongratulateTextPosition++;
        } else {
            currentCongratulateTextPosition = 0;
        }
        return currentCongratulateTextPosition;
    }

    private int getPrevPosition() {
        if (currentCongratulateTextPosition > 0) {
            currentCongratulateTextPosition--;
        } else {
            currentCongratulateTextPosition = holiday.getCongratulates().length - 1;
        }
        return currentCongratulateTextPosition;
    }

    private String getRndTextCongratulate() {
        int rndPosition = new Random().nextInt(holiday.getCongratulates().length);
        currentCongratulateTextPosition = rndPosition;
        return holiday.getCongratulates()[rndPosition];
    }

    private void sendSMS(String phoneNumber, String message) {
        String toSms = "smsto:" + phoneNumber;
        Intent sms = new Intent(Intent.ACTION_SENDTO, Uri.parse(toSms));
        sms.putExtra("sms_body", message);
        startActivity(sms);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNext:
                textCongratulate.setText(getNextTextCongratulate());
                break;
            case R.id.btnPrev:
                textCongratulate.setText(getPrevTextCongratulate());
                break;
            case R.id.btnSend:
                String message = textCongratulate.getText().toString();
                sendSMS("", message);
                break;
        }
    }
}

package ru.solandme.universal_congratulator;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        sqlHelper = new DatabaseHelper(getApplicationContext());
        // создаем базу данных
        sqlHelper.setForcedUpgrade();
        sqlHelper.getReadableDatabase();
        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sqlHelper.close();
        sqlHelper.database.close();
    }

    private void initViews() {

        btnNext = (Button) findViewById(R.id.btnNext);
        textCongratulate = (TextView) findViewById(R.id.textCongratulate);

        currentCongratulateTextPosition = 0;
        holiday = new Holiday();
        holiday.setHolidayName(getHolidayNameFromIntent());
        holiday.setCongratulates(getCongratulateArray(Holiday.UNIVERSAL));

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        RadioButton radioButtonForHer = (RadioButton) findViewById(R.id.radioButtonForHer);
        RadioButton radioButtonForHim = (RadioButton) findViewById(R.id.radioButtonForHim);

        if (((holiday.getHolidayName().equals("WomanDay")) || (holiday.getHolidayName().equals("NewYear")) || (holiday.getHolidayName().equals("MansDay")))) {
            radioButtonForHer.setEnabled(false);
            radioButtonForHim.setEnabled(false);
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                switch (checkedId) {
                    case R.id.radioButtonUniversal:
                        holiday.setCongratulates(getCongratulateArray(Holiday.UNIVERSAL));
                        break;
                    case R.id.radioButtonForHim:
                        holiday.setCongratulates(getCongratulateArray(Holiday.FOR_HIM));
                        break;
                    case R.id.radioButtonForHer:
                        holiday.setCongratulates(getCongratulateArray(Holiday.FOR_HER));
                        break;
                    default:
                        holiday.setCongratulates(getCongratulateArray(Holiday.UNIVERSAL));
                        break;
                }
                textCongratulate.setText(getRndTextCongratulate());
                progressBar.setMax(holiday.congratulates.length);
                progressBar.setProgress(currentCongratulateTextPosition);
            }
        });
        progressBar.setMax(holiday.congratulates.length);
        progressBar.setProgress(currentCongratulateTextPosition);
    }


    private String[] getCongratulateArray(int filter) {
        // TODO реализовать выборку из базы данных

        ArrayList<String> myArrayList = new ArrayList<>();
        try {
            sqlHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        userCursor = sqlHelper.database.rawQuery("select * from " + DatabaseHelper.TABLE + " where " +
                DatabaseHelper.HOLIDAY + "=? AND " + DatabaseHelper.SEX + "=?", new String[]{holiday.getHolidayName(), String.valueOf(filter)});


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
        progressBar.setProgress(currentCongratulateTextPosition);
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
                progressBar.setProgress(currentCongratulateTextPosition);
                break;
            case R.id.btnPrev:
                textCongratulate.setText(getPrevTextCongratulate());
                progressBar.setProgress(currentCongratulateTextPosition);
                break;
            case R.id.btnSend:
                String message = textCongratulate.getText().toString();
                sendSMS("", message);
                break;
        }
    }
}
